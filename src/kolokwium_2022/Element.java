package kolokwium_2022;

public abstract class Element {
    protected Katalog katalogNadrzedny;

    private Dowiazanie[] wskazujeNaMnie = new Dowiazanie[0];

    public Element(Katalog katalogNadrzedny) {
        this.katalogNadrzedny = katalogNadrzedny;
        if (this.katalogNadrzedny != null) {
            this.katalogNadrzedny.dodajElement(this);
        }
    }

    protected abstract String getNazwa();

    public void zmienKatalogNadrzedny(Katalog nowyKatalogNadrzedny) {
        if (this.katalogNadrzedny != null) {
            this.katalogNadrzedny.usunElement(this);
        }
        this.katalogNadrzedny = nowyKatalogNadrzedny;
        nowyKatalogNadrzedny.dodajElement(this);
    }

    @Override
    public String toString() {
        if (katalogNadrzedny == null) {
            return "";
        }
        return katalogNadrzedny.toString() + "/" + getNazwa();
    }

    public void rejestrujDowiazanie(Dowiazanie d) {
        Dowiazanie[] nowa = new Dowiazanie[wskazujeNaMnie.length + 1];
        System.arraycopy(wskazujeNaMnie, 0, nowa, 0, wskazujeNaMnie.length);
        nowa[wskazujeNaMnie.length] = d;
        this.wskazujeNaMnie = nowa;
    }

    public void usunRejestrDowiazania(Dowiazanie d) {
        for (int i = 0; i < wskazujeNaMnie.length; i++) {
            if (wskazujeNaMnie[i] == d) {
                Dowiazanie[] nowa = new Dowiazanie[wskazujeNaMnie.length - 1];
                System.arraycopy(wskazujeNaMnie, 0, nowa, 0, i);
                System.arraycopy(wskazujeNaMnie, i + 1, nowa, i,  wskazujeNaMnie.length - i - 1);
                this.wskazujeNaMnie = nowa;
                break;
            }
        }
    }

    public void usun() {
        while(wskazujeNaMnie.length > 0) {
            wskazujeNaMnie[0].usun();
        }

        if (this.katalogNadrzedny != null) {
            this.katalogNadrzedny.usunElement(this);
            this.katalogNadrzedny = null;
        }
    }
}
