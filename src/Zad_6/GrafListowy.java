package Zad_6;

import java.util.ArrayList;
import java.util.List;

public class GrafListowy extends Graf{
    private List<List<Integer>> list;

    public GrafListowy(int n){
        super(n);
        this.list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.list.add(new ArrayList<>());
        }
    }

    @Override
    public void dodaj(int a, int b){
        list.get(a).add(b);
    }

    @Override
    public void usuń(int a, int b){
        assert list.get(a).contains(b) : "Krawędź z " + a + " do " + b + " nie istnieje!";
        list.get(a).remove(Integer.valueOf(b));
    }

    @Override
    public List<Integer> sąsiedzi(int a){
        list.get(a).sort(null); //According to project description
        return list.get(a);
    }
}
