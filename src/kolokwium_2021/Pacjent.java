package kolokwium_2021;

public class Pacjent {
    private String imie;
    private String nazwisko;
    private int kodPocztowy; // 5 cyfr
    private Szczepionka oczekiwanaSzczepionka;
    private int maksymalnaOdleglosc;

    public int getKodPocztowy() {
        return kodPocztowy;
    }

    public int getPierwszaCyfraKoduPocztowego() {
        return kodPocztowy / 10000;
    }

    public Szczepionka getOczekiwanaSzczepionka() {
        return oczekiwanaSzczepionka;
    }

    public int getMaksymalnaOdleglosc() {
        return maksymalnaOdleglosc;
    }
}
