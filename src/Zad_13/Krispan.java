package Zad_13;

// Two year ; fruits only in its second year ; 3 kg/m² to worker with non-zero
// balance ; at least two languages and not named "Kowalski" ; otherwise 0
public class Krispan extends RoslinaDwuletnia {
    @Override
    public double plonNaMetr(Pracownik pracownik) {
        if (wiek < 2) {
            return 0;
        }
        boolean warunki = pracownik.maNiezeroweKonto()
                && pracownik.liczbaJezykow() >= 2
                && !pracownik.getNazwisko().equals("Kowalski");
        return warunki ? 3.0 : 0.0;
    }

    @Override
    public char symbol() {
        return 'K';
    }
}