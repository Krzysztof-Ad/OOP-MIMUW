package Zad_13;

// Annual plant ; lives one year
public abstract class RoslinaJednoroczna extends Roslina {
    @Override
    public boolean czyZyje() {
        return wiek <= 1;
    }
}