package kolokwium_2021;

import java.util.ArrayList;

public class BiuroSzczepien {
    private ArrayList<Pacjent> zaszczepieniPacjenci = new ArrayList<>();

    private int odleglosc (PunktSzczepien punktSzczepien, Pacjent pacjent) {
        int adresA = punktSzczepien.getKodPocztowy();
        int adresB = pacjent.getKodPocztowy();
        int odleglosc = Math.abs(adresA - adresB);
        return odleglosc;
    }

    private Region znajdzRegionPacjenta (Pacjent pacjent) {
        ArrayList<Region> listaRegionow = Kraj.getRegiony();
        for (Region region : listaRegionow) {
            if (region.getPierwszaCyfraKoduPocztowego() == pacjent.getPierwszaCyfraKoduPocztowego()) {
                return region;
            }
        }
        return null;
    }

    private PunktSzczepien porownajPunkty (PunktSzczepien punktA, PunktSzczepien punktB, Pacjent pacjent) {
        if (punktA.getNajblizszyTermin() == punktB.getNajblizszyTermin()) {
            if (odleglosc(punktA, pacjent) < odleglosc(punktB, pacjent)) {
                return punktA;
            } else  {
                return punktB;
            }
        }
        else if (punktA.getNajblizszyTermin() <  punktB.getNajblizszyTermin()) return punktA;
        else return punktB;
    }

    public void wpiszDoRejestru (Pacjent pacjent) {
        zaszczepieniPacjenci.add(pacjent);
    }

    public PunktSzczepien znajdzPunktSzczepien(Pacjent pacjent) {
        PunktSzczepien najlepszyPunkt = null;
        Region region = znajdzRegionPacjenta(pacjent);
        assert region != null : "Nie istniejacy region";
        ArrayList<PunktSzczepien> listaPunktSzczepien = region.getPunktySzczepien();
        for (PunktSzczepien punktSzczepien : listaPunktSzczepien) {
            if (punktSzczepien.getSzczepionka().equals(pacjent.getOczekiwanaSzczepionka())
                    && odleglosc(punktSzczepien, pacjent) <= pacjent.getMaksymalnaOdleglosc()) {
                if (najlepszyPunkt == null) {
                    najlepszyPunkt = punktSzczepien;
                } else {
                    najlepszyPunkt = porownajPunkty(najlepszyPunkt, punktSzczepien, pacjent);
                }
            }
        }

        return najlepszyPunkt;
    }
}