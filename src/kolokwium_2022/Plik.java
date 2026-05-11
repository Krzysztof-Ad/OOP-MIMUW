package kolokwium_2022;

public class Plik extends Element{
    String nazwa;
    long lokalizacja;
    char prawa; //moze byc 'b' = brak ; 'o' = odczytywanie ; 'm' = modyfikacja (z automatu tez czytanie)

    protected String getNazwa() {
        return this.nazwa;
    }

    public Plik(String nazwa, Katalog katalogNadrzedny, char prawa, long lokalizacja) {
        super(katalogNadrzedny);
        this.prawa = prawa;
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
    }

    public Plik(String nazwa, Katalog katalogNadrzedny, long lokalizacja) {
        super(katalogNadrzedny);
        this.prawa = 'o';
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setPrawa(char prawa) {
        this.prawa = prawa;
    }
}
