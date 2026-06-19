package Zad_13;

// Like Liniowy ; but also rejects plots growing sorelon
public class Antysorelonista extends Liniowy {
    @Override
    protected boolean akceptowalne(Poletko poletko) {
        return super.akceptowalne(poletko) && !(poletko.getRoslina() instanceof Sorelon);
    }
}