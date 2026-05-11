package kolokwium_2021;

public class PunktSzczepien {
    private int kodPocztowy; // 5 cyfr
    private Szczepionka szczepionka;
    private int maxPrzepustowosc;
    private int najblizszyTermin;
    private int zapisaniNaNajblizszyTermin;

    public int getKodPocztowy() {
        return kodPocztowy;
    }

    public Szczepionka getSzczepionka() {
        return szczepionka;
    }

    public int getNajblizszyTermin() {
        return najblizszyTermin;
    }

    public int umowWizyte () {
        int przypisanyTermin = najblizszyTermin;
        zapisaniNaNajblizszyTermin++;

        if (zapisaniNaNajblizszyTermin >= maxPrzepustowosc) {
            najblizszyTermin++;
            zapisaniNaNajblizszyTermin = 0;
        }

        return przypisanyTermin;
    }

    public void wykonajSzczepienie (Pacjent pacjent, BiuroSzczepien biuro) {
        biuro.wpiszDoRejestru(pacjent);
    }
}
