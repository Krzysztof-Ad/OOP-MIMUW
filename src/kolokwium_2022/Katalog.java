package kolokwium_2022;

public class Katalog extends Element {
    protected String nazwa;
    private Element[] zawartosc = new Element[0];

    public Katalog(String nazwa, Katalog katalogNadrzedny) {
        super(katalogNadrzedny);
        this.nazwa = nazwa;
    }

    public Katalog() {
        super(null);
        this.nazwa = "";
    }

    protected String getNazwa() {
        return this.nazwa;
    }

    public void dodajElement(Element nowyElement) {
        Element[] nowaTablica = new Element[zawartosc.length + 1];
        System.arraycopy(zawartosc, 0, nowaTablica, 0, zawartosc.length);
        //for (int i = 0; i < zawartosc.length; i++) {
        //    nowaTablica[i] = zawartosc[i];
        //}
        nowaTablica[zawartosc.length] = nowyElement;
        zawartosc = nowaTablica;
    }

    public void usunElement(Element usuwanyElement) {
        for (int i = 0; i < zawartosc.length; i++) {
            if (zawartosc[i] == usuwanyElement) {
                Element[] nowaTablica = new Element[zawartosc.length - 1];
                System.arraycopy(zawartosc, 0, nowaTablica, 0, i);
                System.arraycopy(zawartosc, i + 1, nowaTablica, i, zawartosc.length - i - 1);
                zawartosc = nowaTablica;
                break;
            }
        }
    }

    public void wypiszZawartosc() {
        System.out.println(this.toString());

        for (int i = 0; i < zawartosc.length; i++) {
            if (zawartosc[i] instanceof Katalog) {
                ((Katalog) zawartosc[i]).wypiszZawartosc();
            } else {
                System.out.println(zawartosc[i].toString());
            }
        }
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public void usun() {
        if (this.zawartosc.length == 0) {
            super.usun();
        }
    }
}
