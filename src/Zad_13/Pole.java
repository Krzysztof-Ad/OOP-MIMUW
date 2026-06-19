package Zad_13;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// Rectangular field split into equal square plots arranged in rows and columns.
// A single purchase point serves the field.
public class Pole {
    private static final int DNI_SEZONU = 60;

    private final Poletko[][] poletka;
    private final int wiersze;
    private final int kolumny;
    private final double powierzchniaPoletka; // in square meters
    private final PunktSkupu punktSkupu;

    public Pole(int wiersze, int kolumny, double powierzchniaPoletka, PunktSkupu punktSkupu) {
        this.wiersze = wiersze;
        this.kolumny = kolumny;
        this.powierzchniaPoletka = powierzchniaPoletka;
        this.punktSkupu = punktSkupu;
        this.poletka = new Poletko[wiersze][kolumny];
        for (int w = 0; w < wiersze; w++) {
            for (int k = 0; k < kolumny; k++) {
                poletka[w][k] = new Poletko(this, w, k);
            }
        }
    }

    public void posadz(Roslina roslina, int wiersz, int kolumna) {
        poletka[wiersz][kolumna].posadz(roslina);
    }

    // Aging run at the end of the year.
    public void zestarzejRosliny() {
        for (Poletko[] wiersz : poletka) {
            for (Poletko poletko : wiersz) {
                poletko.zestarzej();
            }
        }
    }

    // Runs one harvest season (60 days) of the field.
    public void realizujSezon(Collection<Pracownik> pracownicy) {
        przygotujRoslinyNaSezon();
        for (Pracownik pracownik : pracownicy) {
            pracownik.ustawWynikPoprzedniegoDnia(0);
        }

        for (int dzien = 1; dzien <= DNI_SEZONU; dzien++) {
            wyczyscRezerwacje();

            // morning reservation, in priority order
            Map<Pracownik, List<Poletko>> rezerwacje = new HashMap<>();
            for (Pracownik pracownik : ustalKolejnosc(pracownicy)) {
                rezerwacje.put(pracownik, pracownik.getStrategia().wybierzPoletka(this, dzien));
            }

            // snapshot of plots actually harvested today (for the neighbor rule)
            oznaczZbieraneDzis();

            // harvest and hand the crop to the purchase point
            Map<Pracownik, Double> wynikiDnia = new HashMap<>();
            for (Pracownik pracownik : pracownicy) {
                double wynik = 0;
                for (Poletko poletko : rezerwacje.get(pracownik)) {
                    if (!poletko.czyUgor()) {
                        double plon = poletko.zbierz(pracownik);
                        punktSkupu.przyjmijPlon(poletko.getRoslina(), plon);
                        wynik += plon;
                    }
                }
                wynikiDnia.put(pracownik, wynik);
            }

            // end-of-day effects on plants (after all yields are computed)
            zakonczDzien();

            // today's result decides tomorrow's priority
            for (Pracownik pracownik : pracownicy) {
                pracownik.ustawWynikPoprzedniegoDnia(wynikiDnia.get(pracownik));
            }
        }
    }

    private void przygotujRoslinyNaSezon() {
        for (Poletko[] wiersz : poletka) {
            for (Poletko poletko : wiersz) {
                if (!poletko.czyUgor()) {
                    poletko.getRoslina().rozpocznijSezon();
                }
            }
        }
    }

    // Priority goes to workers whose previous-day result exceeded the average of all workers.
    private List<Pracownik> ustalKolejnosc(Collection<Pracownik> pracownicy) {
        double srednia = pracownicy.stream()
                .mapToDouble(Pracownik::getWynikPoprzedniegoDnia)
                .average()
                .orElse(0);

        List<Pracownik> zPierwszenstwem = new ArrayList<>();
        List<Pracownik> pozostali = new ArrayList<>();
        for (Pracownik pracownik : pracownicy) {
            if (pracownik.getWynikPoprzedniegoDnia() > srednia) {
                zPierwszenstwem.add(pracownik);
            } else {
                pozostali.add(pracownik);
            }
        }
        zPierwszenstwem.addAll(pozostali);
        return zPierwszenstwem;
    }

    private void wyczyscRezerwacje() {
        for (Poletko[] wiersz : poletka) {
            for (Poletko poletko : wiersz) {
                poletko.zwolnij();
            }
        }
    }

    private void oznaczZbieraneDzis() {
        for (Poletko[] wiersz : poletka) {
            for (Poletko poletko : wiersz) {
                poletko.ustawZbieraneDzis(poletko.czyZarezerwowane() && !poletko.czyUgor());
            }
        }
    }

    private void zakonczDzien() {
        for (Poletko[] wiersz : poletka) {
            for (Poletko poletko : wiersz) {
                poletko.zakonczDzien();
            }
        }
    }

    // Whether an adjacent plot in the same row or column is harvested today.
    public boolean czySasiadZbiera(int wiersz, int kolumna) {
        return sasiadZbiera(wiersz - 1, kolumna)
                || sasiadZbiera(wiersz + 1, kolumna)
                || sasiadZbiera(wiersz, kolumna - 1)
                || sasiadZbiera(wiersz, kolumna + 1);
    }

    private boolean sasiadZbiera(int wiersz, int kolumna) {
        if (wiersz < 0 || kolumna < 0 || wiersz >= wiersze || kolumna >= kolumny) {
            return false;
        }
        return poletka[wiersz][kolumna].czyZbieraneDzis();
    }

    public int liczbaWierszy() {
        return wiersze;
    }

    public int liczbaKolumn() {
        return kolumny;
    }

    public Poletko poletko(int wiersz, int kolumna) {
        return poletka[wiersz][kolumna];
    }

    public Poletko losowePoletko(Random random) {
        return poletka[random.nextInt(wiersze)][random.nextInt(kolumny)];
    }

    public double getPowierzchniaPoletka() {
        return powierzchniaPoletka;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int w = 0; w < wiersze; w++) {
            for (int k = 0; k < kolumny; k++) {
                sb.append(poletka[w][k].symbol());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}