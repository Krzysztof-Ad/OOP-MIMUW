package Zad_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrafMacierzowy extends Graf{
    private boolean [][] matrix;

    public GrafMacierzowy(int n){
        super(n);
        this.matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(matrix[i], false);
    }

    @Override
    public void dodaj(int a, int b) {
        matrix[a][b] = true;
    }

    @Override
    public void usuń(int a, int b) {
        assert matrix[a][b] : "Krawędź z " + a + " do " + b + " nie istnieje!";
        matrix[a][b] = false;
    }

    @Override
    public List<Integer> sąsiedzi(int a) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (matrix[a][i]) {
                result.add(i);
            }
        }
        return result;
    }
}
