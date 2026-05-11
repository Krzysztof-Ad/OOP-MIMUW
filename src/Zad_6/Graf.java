package Zad_6;

import java.util.List;

public abstract class Graf {
    protected int n; //Number of nodes

    public Graf(int n) {
        this.n = n;
    }

    public abstract void dodaj(int a, int b);
    public abstract void usuń(int a, int b);
    public abstract List<Integer> sąsiedzi(int a);
}
