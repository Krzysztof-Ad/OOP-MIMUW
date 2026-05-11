package Zad_3;

public class Zad_3 {
    private static int findPersonality(boolean[][] knows_matrix) {
        int n = knows_matrix.length;
        if (n == 0) return -1;

        //Step 1: Elimination of candidates
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows_matrix[candidate][i]) {
                candidate = i;
            }
        }

        //Step 2: Verification of the remaining candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                //Candidate cannot know 'i' and 'i' must know candidate
                if (knows_matrix[candidate][i] || !knows_matrix[i][candidate]) {
                    return -1; //No personality
                }
            }
        }

        return candidate;
    }

    private static boolean parseBoolean(String input) {
        //Custom boolean parser made to simplify user input by entering
        //not only true or false but also 1 (meaning true) or 0 (meaning false)
        if (input.equals("true") || input.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main (String[] args) {
        int n = 0;

        //Check for at least first argument (n)
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);

            //Check for the correct number of true(1)/false(0) arguments (knows_matrix)
            if (n * n != (args.length - 1)) {
                System.out.println ("ERROR. Not enough arguments.");
                return;
            }
        } else {
            System.out.println ("ERROR. Not enough arguments.");
            return;
        }

        //knows_matrix is the same matrix as 'zna' from the exercise description
        boolean[][] knows_matrix = new boolean[n][n];
        int argIndex = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                knows_matrix[i][j] = parseBoolean(args[argIndex++]);
            }
        }

        //Searching for personality in knows_matrix
        int result = findPersonality(knows_matrix);

        if (result != -1) {
            System.out.println ("The personality of the given matrix is a candidate with a index: " + result);
        }
        else {
            System.out.println ("In a given matrix there is no personality (-1).");
        }
    }
}
