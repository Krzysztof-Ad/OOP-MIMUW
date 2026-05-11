package runtime_comparison;

public class Runtime_Comparison_Fibonacci {
    private static int fibonacci(int x) {
        if (x < 2) return x;
        return fibonacci(x - 1) + fibonacci(x - 2);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println ("One argument is required.");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);

        long start = System.nanoTime();
        long result = fibonacci(n);
        long end = System.nanoTime();

        System.out.println("Fibonacci number for n = " + n + " is: " + result);
        System.out.println("Java runtime: " + (end - start) / 1_000_000 + " ms");
    }
}
