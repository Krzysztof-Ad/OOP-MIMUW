package Zad_13;

// Fruits from its third year ; Starts each season at the maximum 5 kg/m² ;
// loses 1 kg per harvested day and gains 1 kg per idle day
public class Sorelon extends RoslinaWieloletnia {
    private static final double MAKSYMALNY_PLON = 5.0;
    private double aktualnyPlonNaMetr = MAKSYMALNY_PLON;

    @Override
    public void rozpocznijSezon() {
        aktualnyPlonNaMetr = MAKSYMALNY_PLON;
    }

    @Override
    public double plonNaMetr(Pracownik pracownik) {
        if (wiek < 3) {
            return 0;
        }
        return aktualnyPlonNaMetr;
    }

    @Override
    public void poZbiorze(Poletko poletko) {
        if (aktualnyPlonNaMetr > 0) {
            aktualnyPlonNaMetr -= 1;
        }
    }

    @Override
    public void poDniuBezZbioru() {
        if (aktualnyPlonNaMetr < MAKSYMALNY_PLON) {
            aktualnyPlonNaMetr += 1;
        }
    }

    @Override
    public char symbol() {
        return 'S';
    }
}