package kolokwium_2024;

public class LokalWyborczy {
    private int zebraneGlosy;
    private static final Kandydat[] rejestrKandydatow = KomisjaWyborcza.getRejestrKandydatow();
    private static final KomitetWyborczy[] rejestrKomitetowWyborczych = KomisjaWyborcza.getRejestrKomitetowWyborczych();
    private int[] zebraneGlosyNaKomitety = new int[rejestrKomitetowWyborczych.length];
    private int[] zebraneGlosyNaKandydatow = new int[rejestrKandydatow.length];

    public void zaglosuj(Kandydat kandydat) {
        for (int i = 0; i < rejestrKandydatow.length; i++) {
            if (rejestrKandydatow[i].equals(kandydat)) {
                zebraneGlosyNaKandydatow[i]++;

                KomitetWyborczy komitetKandydata = kandydat.getKomitetWyborczy();
                for (int j = 0; j < rejestrKomitetowWyborczych.length; j++) {
                    if (rejestrKomitetowWyborczych[j].equals(komitetKandydata)) {
                        zebraneGlosyNaKomitety[j]++;
                        break;
                    }
                }

                break;
            }
        }
        zebraneGlosy++;
    }

    public int getZebraneGlosy() {
        return zebraneGlosy;
    }

    public int[] getZebraneGlosyNaKomitety() {
        return zebraneGlosyNaKomitety;
    }
    public int[] getZebraneGlosyNaKandydatow() {
        return zebraneGlosyNaKandydatow;
    }
}
