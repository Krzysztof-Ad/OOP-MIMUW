package Zad_13;

// Annual ; 1 kg/m² per day ; half if the harvester does not know French
public class Likopersikon extends RoslinaJednoroczna {
    @Override
    public double plonNaMetr(Pracownik pracownik) {
        return pracownik.czyZnaFrancuski() ? 1.0 : 0.5;
    }

    @Override
    public char symbol() {
        return 'L';
    }
}