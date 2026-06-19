package Zad_13;

// Two year plant ; lives two years
public abstract class RoslinaDwuletnia extends Roslina {
    @Override
    public boolean czyZyje() {
        return wiek <= 2;
    }
}