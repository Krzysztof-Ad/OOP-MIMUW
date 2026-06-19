package Zad_13;

// Like Likopersikon ; but dead after one year
public class LikopersikonNietrwaly extends Likopersikon {
    @Override
    public void poZbiorze(Poletko poletko) {
        poletko.zostanUgorem();
    }

    @Override
    public char symbol() {
        return 'l';
    }
}
