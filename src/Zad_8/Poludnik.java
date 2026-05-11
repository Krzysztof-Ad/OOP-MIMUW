package Zad_8;

public class Poludnik extends Kwiat{
    @Override
    public void podlej(Kwiat[][] rabatka, int x, int y) {
        for (int i = x - 1; i >= 0 && rabatka[i][y] == null; i--) {
            rabatka[i][y] = new Poludnik();
        }
        for (int i = x + 1; i < rabatka.length && rabatka[i][y] == null; i++) {
            rabatka[i][y] = new Poludnik();
        }
    }

    @Override
    public char symbol() {
        return 'P';
    }
}
