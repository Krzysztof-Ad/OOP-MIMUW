package Zad_5;

public class Main {
    public static void main(String[] args) {
        //CONSTRUCTORS

        //Default constructor (zero polynomial)
        Wielomian w0 = new Wielomian();
        System.out.println("Default: " + w0);

        //Int array constructor (x^2 + 2x + 1)
        Wielomian wInt = new Wielomian(new int[]{1, 2, 1});
        System.out.println("From int[]: " + wInt);

        //Float array constructor (0.5x^3 - 1.5)
        Wielomian wFloat = new Wielomian(new float[]{0.5f, 0, 0, -1.5f});
        System.out.println("From float[]: " + wFloat);


        //ADDITION AND SUBTRACTION
        System.out.println("\nARITHMETIC (ADD / SUBTRACT)");
        Wielomian a = new Wielomian(new int[]{1, 0, -1}); // x^2 - 1
        Wielomian b = new Wielomian(new int[]{2, 1});    // 2x + 1

        System.out.println("A: " + a);
        System.out.println("B: " + b);

        a.dodaj(b);
        System.out.println("A after adding B: " + a); // x^2 + 2x

        a.odejmij(new Wielomian(new int[]{2, 0})); //subtract 2x
        System.out.println("Previous result (A) after subtracting 2x: " + a);


        //MULTIPLICATION AND DIVISION
        System.out.println("\nMULTIPLICATION AND DIVISION");
        // (x + 1) * (x - 1) = x^2 - 1
        Wielomian p1 = new Wielomian(new int[]{1, 1});
        Wielomian p2 = new Wielomian(new int[]{1, -1});

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);

        p1.pomnoz(p2);
        System.out.println("P1 * P2: " + p1);

        // (x^2 - 1) / (x - 1) = x + 1
        p1.podziel(p2);
        System.out.println("Result of division (Previous result (P1) / P2): " + p1);


        //CALCULUS AND VALUE
        System.out.println("\nCALCULUS AND POINT VALUE");
        Wielomian f = new Wielomian(new int[]{4, 3, 2, 1}); // 4x^3 + 3x^2 + 2x + 1
        System.out.println("f(x) = " + f);

        //Point value
        float value = f.wartosc(1.0f);
        System.out.println("Value at x=1: " + value);

        //Derivative
        Wielomian derivative = f.pochodna();
        System.out.println("f'(x) = " + derivative);

        //Integral
        Wielomian integral = f.całkaNieoznaczona();
        System.out.println("Integral of f(x) = " + integral);


        //ASSERTIONS AND INVALID OPERATIONS
        System.out.println("\nASSERTION TESTS (COMMENTED OUT)");

        //Division by zero
        //f.podziel(new Wielomian());

        //Not divisible (remainder exists)
        //Wielomian h = new Wielomian(new int[]{1, 1}); // x+1
        //h.podziel(new Wielomian(new int[]{1, 0})); // x

        //Degree overflow (> 10)
        //Wielomian big = new Wielomian(new int[]{1, 0, 0, 0, 0, 0, 0}); // x^6
        //big.pomnoz(big); // Result x^12 triggers assertion

        //Integral degree overflow
        //Wielomian max = new Wielomian(new float[]{1,0,0,0,0,0,0,0,0,0,0}); // x^10
        //max.całkaNieoznaczona();
    }
}