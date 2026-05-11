package Zad_5;

import java.util.Arrays;

public class Wielomian {
    //Array for coefficients of max 10th degree
    //index 0 is x^10 coefficient and 10 is x^0 coefficient
    float[] coefficients = new float[11];

    //Constructor functions
    public Wielomian() {
        Arrays.fill(coefficients, 0);
    }

    public Wielomian(float[] coefficients) {
        assert coefficients.length <= 11 : "Degree needs to be max 10";
        for  (int i = 0; i < coefficients.length; i++) {
            this.coefficients[11 - coefficients.length + i] = coefficients[i];
        }
    }

    public Wielomian(int[] coefficients) {
        assert coefficients.length <= 11 : "Degree needs to be max 10";
        for  (int i = 0; i < coefficients.length; i++) {
            this.coefficients[11 - coefficients.length + i] = (float) coefficients[i];
        }
    }


    //Helper private functions
    private int degree(Wielomian w) {
        for (int i = 0; i < w.coefficients.length; i++) {
            if (w.coefficients[i] != 0)
                return (10 - i);
        }
        return -1;
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        boolean is_first = true;

        for (int i = 0; i < coefficients.length; i++) {
            float coefficient = coefficients[i];
            if (coefficient == 0) continue;

            int degree = 10 - i;

            if (!is_first && coefficient > 0) output.append("+ ");
            if (coefficient < 0) output.append("- ");

            float absolute_coefficient = Math.abs(coefficient);

            if (degree == 0) output.append(absolute_coefficient);
            else if (degree == 1) output.append(absolute_coefficient).append("x");
            else output.append(absolute_coefficient).append("x^").append(degree);

            output.append(" ");
            is_first = false;
        }

        if (output.isEmpty()) {
            return "0.0";
        }

        return output.toString().trim();
    }

    public void dodaj(Wielomian w) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] += w.coefficients[i];
        }
    }

    public void odejmij(Wielomian w) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] -= w.coefficients[i];
        }
    }

    public void pomnoz(Wielomian w) {
        Wielomian result = new Wielomian();
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < coefficients.length; j++) {
                float multiplier = coefficients[i] * w.coefficients[j];
                if (multiplier == 0) continue;

                if (i + j >= 10) {
                    result.coefficients[i + j - 10] += coefficients[i] * w.coefficients[j];
                } else {
                    assert false : "Degree exceeds 10";
                }
            }
        }
        System.arraycopy(result.coefficients, 0, coefficients, 0, coefficients.length);
    }

    public void podziel(Wielomian w) {
        Wielomian remainder = new Wielomian();
        System.arraycopy(coefficients, 0, remainder.coefficients, 0, coefficients.length);

        Wielomian result = new Wielomian();

        int degree = degree(remainder);
        int degree_w = degree(w);

        assert degree_w != -1 : "Dividing by zero";

        while (degree >= degree_w) {
            int i = 10 - degree;
            int i_w = 10 - degree_w;

            float coefficient = remainder.coefficients[i] / w.coefficients[i_w];

            int degree_diff = degree - degree_w;

            result.coefficients[10 - degree_diff] = coefficient;

            for (int k = 0; k <= degree_w; k++) {
                remainder.coefficients[i + k] -= coefficient * w.coefficients[i_w + k];
            }

            degree = degree(remainder);
        }

        for (int i = 0; i < coefficients.length; i++) {
            assert remainder.coefficients[i] == 0 : "Cannot divide without remainder";
        }

        assert degree(remainder) == -1 : "Cannot divide without remainder";

        System.arraycopy(result.coefficients, 0, this.coefficients, 0, coefficients.length);
    }

    float wartosc(float x) {
        float result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * ((float) Math.pow(x, (10 - i)));
        }
        return result;
    }

    Wielomian całkaNieoznaczona() {
        assert coefficients[0] == 0 : "Degree exceeds 10";

        Wielomian result = new Wielomian();

        for (int i = 1; i <= coefficients.length - 1; i++) {
            result.coefficients[i - 1] = coefficients[i] / (11 - i);
        }

        result.coefficients[10] = 0;

        return result;
    }

    Wielomian pochodna () {
        Wielomian result = new Wielomian();
        result.coefficients[0] = 0;
        for (int i = 0; i < coefficients.length - 1; i++) {
            result.coefficients[i + 1] = coefficients[i] * (10 - i);
        }
        return result;
    }
}
