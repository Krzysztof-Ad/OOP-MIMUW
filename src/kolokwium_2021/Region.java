package kolokwium_2021;

import java.util.ArrayList;

public class Region {
    private int pierwszaCyfraKoduPocztowego;
    private ArrayList<PunktSzczepien> punktySzczepien =  new ArrayList<>();

    public int getPierwszaCyfraKoduPocztowego() {
        return pierwszaCyfraKoduPocztowego;
    }

    public ArrayList<PunktSzczepien> getPunktySzczepien() {
        return punktySzczepien;
    }
}
