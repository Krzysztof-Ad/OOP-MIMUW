package Zad_11;

public class IntegerElement implements Element<IntegerElement> {
    private int value;

    public IntegerElement(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    public int compareTo(IntegerElement other) {
        return Integer.compare(value, other.value);
    }
}