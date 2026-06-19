package Zad_13;

import java.util.List;

// Morning plot-reservation strategy for a given day of the season
public abstract class Strategia {
    // Picks and reserves plots, returning the reserved ones
    public abstract List<Poletko> wybierzPoletka(Pole pole, int dzienSezonu);
}