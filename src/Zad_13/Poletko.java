package Zad_13;

// A single square plot ; Tracks its reservation
// state and whether it is harvested today (needed for the neighbor rule)
public class Poletko {
    private final Pole pole;
    private final int wiersz;
    private final int kolumna;

    private Roslina roslina;
    private boolean zarezerwowane;
    private boolean zbieraneDzis;

    public Poletko(Pole pole, int wiersz, int kolumna) {
        this.pole = pole;
        this.wiersz = wiersz;
        this.kolumna = kolumna;
    }

    public void posadz(Roslina roslina) {
        this.roslina = roslina;
    }

    public void zostanUgorem() {
        this.roslina = null;
    }

    public boolean czyUgor() {
        return roslina == null;
    }

    public Roslina getRoslina() {
        return roslina;
    }

    public void zarezerwuj() {
        this.zarezerwowane = true;
    }

    public boolean czyZarezerwowane() {
        return zarezerwowane;
    }

    public void zwolnij() {
        this.zarezerwowane = false;
        this.zbieraneDzis = false;
    }

    public void ustawZbieraneDzis(boolean wartosc) {
        this.zbieraneDzis = wartosc;
    }

    public boolean czyZbieraneDzis() {
        return zbieraneDzis;
    }

    public double zbierz(Pracownik pracownik) {
        if (roslina == null) {
            return 0;
        }
        double plon = roslina.plonNaMetr(pracownik) * pole.getPowierzchniaPoletka();
        if (pole.czySasiadZbiera(wiersz, kolumna)) {
            plon *= 0.9;
        }
        return plon;
    }

    public void zakonczDzien() {
        if (roslina == null) {
            return;
        }
        if (zbieraneDzis) {
            roslina.poZbiorze(this);
        } else {
            roslina.poDniuBezZbioru();
        }
    }

    // End-of-year aging ; the plant disappears after its last year of life
    public void zestarzej() {
        if (roslina == null) {
            return;
        }
        roslina.postarzejORok();
        if (!roslina.czyZyje()) {
            roslina = null;
        }
    }

    public char symbol() {
        return roslina == null ? '.' : roslina.symbol();
    }
}