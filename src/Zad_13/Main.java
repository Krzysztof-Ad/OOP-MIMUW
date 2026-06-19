package Zad_13;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PunktSkupu punktSkupu = new PunktSkupu();
        Pole pole = new Pole(4, 6, 10.0, punktSkupu); // 4 x 6 plots of 10 m² each

        // plant the crops
        pole.posadz(new Likopersikon(), 0, 0);
        pole.posadz(new Likopersikon(), 0, 1);
        pole.posadz(new LikopersikonNietrwaly(), 0, 2);
        pole.posadz(new LikopersikonNietrwaly(), 0, 3);
        pole.posadz(new Krispan(), 1, 0);
        pole.posadz(new Krispan(), 1, 1);
        pole.posadz(new Sorelon(), 2, 0);
        pole.posadz(new Sorelon(), 2, 1);
        pole.posadz(new Sorelon(), 2, 2);

        // workers with different strategies
        Pracownik anna = new Pracownik("Nowak", 1000, Set.of("polski", "francuski"), new Liniowy());
        Pracownik jan = new Pracownik("Kowalski", 0, Set.of("polski"), new Leniwy());
        Pracownik ewa = new Pracownik("Wisniewska", 500, Set.of("polski", "angielski", "francuski"), new Naprzemienny());
        Pracownik piotr = new Pracownik("Lewandowski", 200, Set.of("polski", "niemiecki"), new Antysorelonista());

        List<Pracownik> pracownicy = List.of(anna, jan, ewa, piotr);

        System.out.println("Field at start (year 1):");
        System.out.print(pole);

        // three years: harvest season + aging at the end of each year
        for (int rok = 1; rok <= 3; rok++) {
            pole.realizujSezon(pracownicy);
            pole.zestarzejRosliny();
            System.out.println("Field after season and aging (end of year " + rok + "):");
            System.out.print(pole);
        }
    }
}