package Zad_9_FULL;

public class Main {
    public static void main(String[] args) {
        Kolejka kolejka = new Kolejka();

        System.out.println("--- 1. Umieszczanie elementów 0, 1, 2... 9 ---");
        for (int i = 0; i <= 9; i++) {
            bezpieczneDodawanie(kolejka, i);
        }

        System.out.println("\n--- 2. Próba umieszczenia elementów 0, 2, 4... 18 ---");
        for (int i = 0; i <= 18; i += 2) {
            bezpieczneDodawanie(kolejka, i);
        }

        System.out.println("\n--- 3. Zmiana elementów 3i na 4i (dla i=0..9) ---");
        for (int i = 0; i <= 9; i++) {
            bezpiecznaZmiana(kolejka, 3 * i, 4 * i);
        }

        System.out.println("\n--- 4. Opróżnianie kolejki ---");
        bezpieczneOproznianie(kolejka);
    }

    private static void bezpieczneDodawanie(Kolejka kolejka, int wartosc) {
        while (true) {
            try {
                kolejka.add(wartosc);
                System.out.println("Dodano: " + wartosc);
                break;
            } catch (UnexpectedBehavior e) {
                System.err.println("Niedozwolona operacja (add " + wartosc + "): " + e.getMessage());
                break;
            } catch (RandomException e) {
                System.err.println("Awaria pojemnika przy dodawaniu " + wartosc + ". Ponawiam próbę...");
            }
        }
    }

    private static void bezpiecznaZmiana(Kolejka kolejka, int val1, int val2) {
        while (true) {
            try {
                kolejka.change(val1, val2);
                System.out.println("Zmieniono: " + val1 + " -> " + val2);
                break;
            } catch (UnexpectedBehavior e) {
                System.err.println("Niedozwolona operacja (change " + val1 + "->" + val2 + "): " + e.getMessage());
                break;
            } catch (RandomException e) {
                System.err.println("Awaria pojemnika przy zmianie " + val1 + "->" + val2 + ". Ponawiam próbę...");
            }
        }
    }

    private static void bezpieczneOproznianie(Kolejka kolejka) {
        while (!kolejka.isEmpty()) {
            while (true) {
                try {
                    int usuniety = kolejka.remove();
                    System.out.println("Usunięto z kolejki: " + usuniety);
                    break;
                } catch (UnexpectedBehavior e) {
                    System.err.println("Niedozwolona operacja (remove): " + e.getMessage());
                    return;
                } catch (RandomException e) {
                    System.err.println("Awaria pojemnika przy usuwaniu. Ponawiam próbę...");
                }
            }
        }
    }
}