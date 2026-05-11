package kolokwium_2024;

public class KomisjaWyborcza {
    private static int prog; // prog wejscia dla komitetu w procentach
    private static int minimalnyProgWejscia;
    private static Kandydat[] rejestrKandydatow;
    private static KomitetWyborczy[] rejestrKomitetowWyborczych;
    private static LokalWyborczy[] rejestrLokaliWyborczych;

    private static int[] oddaneGlosyNaKomitety;
    private static int[] oddaneGlosyNaKandydatow;

    public static void zarejestrujKomitet(KomitetWyborczy komitet) {
        if (rejestrKomitetowWyborczych == null) {
            rejestrKomitetowWyborczych = new KomitetWyborczy[1];
            rejestrKomitetowWyborczych[0] = komitet;
        } else {
            KomitetWyborczy[] nowyRejestrKomitetowWyborczych = new KomitetWyborczy[rejestrKomitetowWyborczych.length + 1];
            System.arraycopy(rejestrKomitetowWyborczych, 0, nowyRejestrKomitetowWyborczych, 0, rejestrKomitetowWyborczych.length);
            nowyRejestrKomitetowWyborczych[0] = komitet;
            rejestrKomitetowWyborczych = nowyRejestrKomitetowWyborczych;
        }
    }

    public static void zarejestrujKandydata(KomitetWyborczy komitet, Kandydat kandydat) {
        if (rejestrKandydatow == null) {
            rejestrKandydatow = new Kandydat[1];
            rejestrKandydatow[0] = kandydat;
        } else {
            Kandydat[] nowyRejestrKandydatow = new Kandydat[rejestrKandydatow.length + 1];
            System.arraycopy(rejestrKandydatow, 0, nowyRejestrKandydatow, 0, rejestrKandydatow.length);
            nowyRejestrKandydatow[rejestrKandydatow.length] = kandydat;
            rejestrKandydatow = nowyRejestrKandydatow;
        }
        komitet.dodajKandydata(kandydat);
    }

    public static void zarejestrujLokalWyborczy(LokalWyborczy lokalWyborczy) {
        if  (rejestrLokaliWyborczych == null) {
            rejestrLokaliWyborczych = new LokalWyborczy[1];
            rejestrLokaliWyborczych[0] = lokalWyborczy;
        } else {
            LokalWyborczy[] nowyRejestrLokaliWyborczych = new LokalWyborczy[rejestrLokaliWyborczych.length + 1];
            System.arraycopy(rejestrLokaliWyborczych, 0, nowyRejestrLokaliWyborczych, 0, rejestrLokaliWyborczych.length);
            nowyRejestrLokaliWyborczych[rejestrLokaliWyborczych.length] = lokalWyborczy;
            rejestrLokaliWyborczych = nowyRejestrLokaliWyborczych;
        }
    }

    public static void zliczGlosy() {
        int wszystkieGlosy = 0;
        for (LokalWyborczy lokaliWyborczych : rejestrLokaliWyborczych) {
            int[] zebraneGlosyKomitetowWLokalu = lokaliWyborczych.getZebraneGlosyNaKomitety();
            for (int j = 0; j < rejestrKomitetowWyborczych.length; j++) {
                oddaneGlosyNaKomitety[j] += zebraneGlosyKomitetowWLokalu[j];
            }
            int[] zebraneGlosyKandydatowWLokalu = lokaliWyborczych.getZebraneGlosyNaKandydatow();
            for (int j = 0; j < rejestrKandydatow.length; j++) {
                oddaneGlosyNaKandydatow[j] += zebraneGlosyKandydatowWLokalu[j];
            }
            wszystkieGlosy += lokaliWyborczych.getZebraneGlosy();
        }

        minimalnyProgWejscia = wszystkieGlosy * (prog / 100);
    }

    public static void obliczWynik() {
        int nowaIloscKandydatow = rejestrKandydatow.length;
        int nowaIloscKomitetow = rejestrKomitetowWyborczych.length;
        zliczGlosy();
        for (int i = 0; i < rejestrKomitetowWyborczych.length; i++) {
            if (oddaneGlosyNaKomitety[i] < minimalnyProgWejscia) {
                oddaneGlosyNaKomitety[i] = -1; // -1 oznacza ze odrzucilismy komitet
                nowaIloscKomitetow--;
                for (int j = 0; j < rejestrKandydatow.length; j++) {
                    if (rejestrKandydatow[j].getKomitetWyborczy().equals(rejestrKomitetowWyborczych[i])) {
                        oddaneGlosyNaKandydatow[j] = -1;
                        nowaIloscKandydatow--;
                    }
                }
            }
        }
        KomitetWyborczy[] nowaListaKomitetow = new KomitetWyborczy[nowaIloscKomitetow];
        int[] noweWynikiKomitetow = new int[nowaIloscKomitetow];
        Kandydat[] nowaListaKandydatow = new Kandydat[nowaIloscKandydatow];
        int[] noweWynikiKandydatow = new int[nowaIloscKandydatow];

        int iterator = 0;
        for (int i = 0; i < rejestrKomitetowWyborczych.length; i++) {
            if (oddaneGlosyNaKomitety[i] != -1) {
                noweWynikiKomitetow[iterator] = oddaneGlosyNaKomitety[i];
                nowaListaKomitetow[iterator] = rejestrKomitetowWyborczych[i];
                iterator++;
            }
        }
        iterator = 0;
        for (int i = 0; i < rejestrKandydatow.length; i++) {
            if (oddaneGlosyNaKandydatow[i] != -1) {
                noweWynikiKandydatow[iterator] = oddaneGlosyNaKandydatow[i];
                nowaListaKandydatow[iterator] = rejestrKandydatow[i];
            }
        }

        //Teraz mamy nowe tablice ktore zostawiaja nam tylko te komitety ktore biora udzial w fazie 3

        for (int i = 0; i < nowaListaKomitetow.length; i++) {
            nowaListaKomitetow[i].policzIlorazy(noweWynikiKomitetow[i]);
        }


    }

    public static Kandydat[] getRejestrKandydatow() {
        return rejestrKandydatow;
    }

    public static KomitetWyborczy[] getRejestrKomitetowWyborczych() {
        return rejestrKomitetowWyborczych;
    }
}
