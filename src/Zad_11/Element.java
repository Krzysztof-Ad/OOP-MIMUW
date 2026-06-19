package Zad_11;

public interface Element<T extends Element<T>> extends Comparable<T> {
    int value();

    void setValue(int newValue);
}
