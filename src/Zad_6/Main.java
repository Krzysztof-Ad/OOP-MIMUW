package Zad_6;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void obejdźGraf(Graf g, Kolejka q, int k) {
        boolean[] visited = new boolean[g.n];
        q.dodaj(k);

        while (!q.isEmpty()) {
            int v = q.usuń();

            if  (!visited[v]) {
                visited[v] = true;
                System.out.print(v + " ");

                List<Integer> sąsiedzi = g.sąsiedzi(v);

                if (q instanceof LIFO) {
                    for (int i = sąsiedzi.size() - 1; i >= 0; i--) {
                        int s = sąsiedzi.get(i);
                        if (!visited[s]) q.dodaj(s);
                    }
                } else {
                    for (int s : sąsiedzi) {
                        if (!visited[s]) q.dodaj(s);
                    }
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m =  scanner.nextInt();

        GrafMacierzowy gm = new GrafMacierzowy(n);
        GrafListowy gl =  new GrafListowy(n);

        for  (int i = 0; i < m; i++) {
            int a  = scanner.nextInt();
            int b = scanner.nextInt();
            gm.dodaj(a, b);
            gl.dodaj(a, b);
        }

        System.out.print("Graf Macierzowy - DFS: ");
        obejdźGraf(gm, new LIFO(), 0);

        System.out.print("Graf Macierzowy - BFS: ");
        obejdźGraf(gm, new FIFO(), 0);

        System.out.print("Graf Listowy - DFS: ");
        obejdźGraf(gl, new LIFO(), 0);

        System.out.print("Graf Listowy - BFS: ");
        obejdźGraf(gl, new FIFO(), 0);
    }
}
