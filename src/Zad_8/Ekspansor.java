package Zad_8;

public class Ekspansor extends Kwiat {
    private final int liczba_platkow;

    public Ekspansor() {
        this.liczba_platkow = 0;
    }

    public Ekspansor(int liczba_platkow) {
        this.liczba_platkow = liczba_platkow;
    }


    private void wstawEkspansor(int x, int y, Kwiat[][] rabatka) {
        int n = rabatka.length;
        int m = rabatka[0].length;
        if (x >= 0 && y >= 0 && x < n && y < m && rabatka[x][y] != null) {
            rabatka[x][y] = new Ekspansor(this.liczba_platkow);
        }
    }

    @Override
    public void podlej(Kwiat[][] rabatka, int x, int y) {
        int licznik = 0;
        int i = x - 1;
        while (licznik < 3) {
            wstawEkspansor(i, y - 1, rabatka);
            if (x != i) wstawEkspansor(i, y, rabatka);
            wstawEkspansor(i, y + 1, rabatka);
            licznik++;
            i++;
        }
    }

    @Override
    public char symbol() {
        return (char) ('0' + this.liczba_platkow);
    }
}
