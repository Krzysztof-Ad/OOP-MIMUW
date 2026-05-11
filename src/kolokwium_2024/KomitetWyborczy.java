package kolokwium_2024;

public class KomitetWyborczy {
    private Kandydat[] listaKandydatow;
    private Kandydat[] listaKandydatowZMandatami;

    private float[] ilorazy;

    private void poinformujKandydata(Kandydat kandydat) {
        for (int i = 0; i < listaKandydatowZMandatami.length; i++) {
            if (kandydat.equals(listaKandydatowZMandatami[i])) {
                kandydat.setCzyZdobytyMandat(true);
            }
        }
    }

    public void dodajKandydata(Kandydat kandydat) {
        if (listaKandydatow == null) {
            listaKandydatow = new Kandydat[1];
            listaKandydatow[0] = kandydat;
        } else {
            Kandydat[] nowaListaKandydatow = new Kandydat[listaKandydatow.length + 1];
            System.arraycopy(listaKandydatow,  0, nowaListaKandydatow, 0, listaKandydatow.length);
            nowaListaKandydatow[listaKandydatow.length] = kandydat;
            listaKandydatow = nowaListaKandydatow;
        }
    }

    public void policzIlorazy(int glosy) {
        int k = 20;
        ilorazy = new float[k];
        for (int i = 0; i < k; i++) {
            ilorazy[i] = (float) glosy / (i + 1);
        }
    }

    public float[] getIlorazy() {
        return ilorazy;
    }
}
