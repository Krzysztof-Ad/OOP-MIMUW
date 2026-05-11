package kolokwium_2024_new;

public class KomisjaWyborcza {

    private KomitetWyborczy[] dopiszKomitet(KomitetWyborczy komitet, KomitetWyborczy[] listaKomitetow) {
        if (listaKomitetow == null) {
            KomitetWyborczy[] nowaLista = new KomitetWyborczy[1];
            nowaLista[0] = komitet;
            return nowaLista;
        }
        KomitetWyborczy[] nowaLista = new KomitetWyborczy[listaKomitetow.length + 1];
        System.arraycopy(listaKomitetow, 0, nowaLista, 0, listaKomitetow.length);
        nowaLista[listaKomitetow.length] = komitet;
        return nowaLista;
    }

    public void policzWynik(int n, int p, KomitetWyborczy[] komitety) {
        int sumaWszystkichGlosow = 0;
        for (KomitetWyborczy komitet : komitety) {
            sumaWszystkichGlosow += komitet.sumaGlosow;
        }

        double progPunktowy = sumaWszystkichGlosow * (p / 100.0);

        KomitetWyborczy[] listaWaznychKomitetow = new KomitetWyborczy[0];

        for (KomitetWyborczy komitet : komitety) {
            if (komitet.sumaGlosow >= progPunktowy) {
                komitet.aktualnyIloraz = komitet.sumaGlosow;
                listaWaznychKomitetow = dopiszKomitet(komitet, listaWaznychKomitetow);
            }
        }

        if (listaWaznychKomitetow.length == 0) {
            return;
        }

        int wybraneMandaty = 0;
        while (wybraneMandaty < n) {
            java.util.Arrays.sort(listaWaznychKomitetow);
            listaWaznychKomitetow[0].zdobyteMandaty++;
            listaWaznychKomitetow[0].dzielnik++;
            listaWaznychKomitetow[0].aktualnyIloraz = (double) listaWaznychKomitetow[0].sumaGlosow / listaWaznychKomitetow[0].dzielnik;
            wybraneMandaty++;
        }

        for (KomitetWyborczy komitet : komitety) {
            int przydzielone = komitet.zdobyteMandaty;
            komitet.kandydaciZMandatem = new Kandydat[przydzielone];

            Kandydat[] kopiaKandydatow = new Kandydat[komitet.kandydaci.length];
            System.arraycopy(komitet.kandydaci, 0, kopiaKandydatow, 0, komitet.kandydaci.length);

            java.util.Arrays.sort(kopiaKandydatow);

            for (int i = 0; i < kopiaKandydatow.length; i++) {
                if (i < przydzielone) {
                    kopiaKandydatow[i].powiadom(true);
                    komitet.kandydaciZMandatem[i] = kopiaKandydatow[i];
                } else {
                    kopiaKandydatow[i].powiadom(false);
                }
            }
        }
    }
}