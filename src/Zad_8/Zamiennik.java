package Zad_8;

public class Zamiennik extends Kwiat{
    @Override
    public void podlej(Kwiat[][] rabatka, int x, int y) {
        int j = y - 1;
        if (j >= 0 && rabatka[x][j] != null) {
            Kwiat tmp_kwiat = rabatka[x][y];

            rabatka[x][y] = rabatka[x][j];
            rabatka[x][j] = tmp_kwiat;
        }
    }

    @Override
    public char symbol() {
        return 'Z';
    }
}
