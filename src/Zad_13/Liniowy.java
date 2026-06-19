package Zad_13;

import java.util.ArrayList;
import java.util.List;

// Takes the longest row fragment not dead and already-reserved plots
public class Liniowy extends Strategia {
    @Override
    public List<Poletko> wybierzPoletka(Pole pole, int dzienSezonu) {
        List<Poletko> najdluzszy = new ArrayList<>();

        for (int w = 0; w < pole.liczbaWierszy(); w++) {
            List<Poletko> biezacy = new ArrayList<>();
            for (int k = 0; k < pole.liczbaKolumn(); k++) {
                Poletko poletko = pole.poletko(w, k);
                if (akceptowalne(poletko)) {
                    biezacy.add(poletko);
                } else {
                    if (biezacy.size() > najdluzszy.size()) {
                        najdluzszy = biezacy;
                    }
                    biezacy = new ArrayList<>();
                }
            }
            if (biezacy.size() > najdluzszy.size()) {
                najdluzszy = biezacy;
            }
        }

        for (Poletko poletko : najdluzszy) {
            poletko.zarezerwuj();
        }
        return najdluzszy;
    }

    // Whether a plot can join a fragment
    protected boolean akceptowalne(Poletko poletko) {
        return !poletko.czyUgor() && !poletko.czyZarezerwowane();
    }
}