package Zad_13;

import java.util.Set;

// Worker harvesting crops ; Holds a reservation strategy and the traits some plants yields depend on
public class Pracownik {
    private final String nazwisko;
    private final double stanKonta;
    private final Set<String> jezyki;
    private final Strategia strategia;

    private double wynikPoprzedniegoDnia = 0;

    public Pracownik(String nazwisko, double stanKonta, Set<String> jezyki, Strategia strategia) {
        this.nazwisko = nazwisko;
        this.stanKonta = stanKonta;
        this.jezyki = jezyki;
        this.strategia = strategia;
    }

    public boolean czyZnaFrancuski() {
        return jezyki.contains("francuski");
    }

    public boolean maNiezeroweKonto() {
        return stanKonta != 0;
    }

    public int liczbaJezykow() {
        return jezyki.size();
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Strategia getStrategia() {
        return strategia;
    }

    public double getWynikPoprzedniegoDnia() {
        return wynikPoprzedniegoDnia;
    }

    public void ustawWynikPoprzedniegoDnia(double wynik) {
        this.wynikPoprzedniegoDnia = wynik;
    }
}