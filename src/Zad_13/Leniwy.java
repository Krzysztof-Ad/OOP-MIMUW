package Zad_13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Draws a random plot ; takes it when still free ; otherwise takes none
public class Leniwy extends Strategia {
    private final Random random = new Random();

    @Override
    public List<Poletko> wybierzPoletka(Pole pole, int dzienSezonu) {
        List<Poletko> wybrane = new ArrayList<>();
        Poletko poletko = pole.losowePoletko(random);
        if (!poletko.czyZarezerwowane()) {
            poletko.zarezerwuj();
            wybrane.add(poletko);
        }
        return wybrane;
    }
}