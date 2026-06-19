package Zad_13;

import java.util.List;

// Alternates between lazy and linear on consecutive days ; lazy on day one
public class Naprzemienny extends Strategia {
    private final Leniwy leniwy = new Leniwy();
    private final Liniowy liniowy = new Liniowy();

    @Override
    public List<Poletko> wybierzPoletka(Pole pole, int dzienSezonu) {
        if (dzienSezonu % 2 == 1) {
            return leniwy.wybierzPoletka(pole, dzienSezonu);
        } else {
            return liniowy.wybierzPoletka(pole, dzienSezonu);
        }
    }
}