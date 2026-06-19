package Zad_13;

// Base class for all plants grown in Bajtocja
public abstract class Roslina {
    protected int wiek = 1; // year of life counted from 1

    // Daily yield for square meter for a given worker (0 if it does not fruit this year)
    public abstract double plonNaMetr(Pracownik pracownik);
    public abstract char symbol();

    // Whether the plant survives into the next year checked after aging
    public abstract boolean czyZyje();

    // Per-season or per-day hooks
    public void rozpocznijSezon() {}
    public void poZbiorze(Poletko poletko) {}
    public void poDniuBezZbioru() {}

    public void postarzejORok() {
        wiek++;
    }
}