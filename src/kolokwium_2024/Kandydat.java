package kolokwium_2024;

public class Kandydat {
    private String nazwisko;
    private boolean czyZdobytyMandat;
    private KomitetWyborczy komitetWyborczy;

    public void setCzyZdobytyMandat(boolean czyZdobytyMandat) {
        this.czyZdobytyMandat = czyZdobytyMandat;
    }

    public KomitetWyborczy getKomitetWyborczy() {
        return komitetWyborczy;
    }
}
