package Zad_8;

public class Rabatka {
    private final Kwiat[][] rabatka;
    private final int n;
    private final int m;
    public Rabatka(int n, int m) {
        this.n = n;
        this.m = m;
        this.rabatka = new Kwiat[n][m];
    }

    public void posadz(Kwiat kwiat, int x, int y) {
        rabatka[x][y] = kwiat;
    }

    public void podlej() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if  (rabatka[i][j] != null) {
                    rabatka[i][j].podlej(rabatka, i, j);
                }
            }
        }
    }

    private void reverse(int start, int koniec, int wiersz) {
        int i = start;
        int j = koniec;
        while (i < j) {
            Kwiat tmp = rabatka[wiersz][i];
            rabatka[wiersz][i] = rabatka[wiersz][j];
            rabatka[wiersz][j] = tmp;
            i++;
            j--;
        }
    }

    private void wykonajCykl(int ile, int wiersz) {
        ile = ile % m;
        reverse(0, m - 1, wiersz);
        reverse(0, ile - 1, wiersz);
        reverse(ile, m - 1, wiersz);
    }

    public void taniec() {
        for (int i = 0; i < n; i++) {
            wykonajCykl(i + 1, i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(rabatka[i][j] == null ? "." : rabatka[i][j].symbol());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
