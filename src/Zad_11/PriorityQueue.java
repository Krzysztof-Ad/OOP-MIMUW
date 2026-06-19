package Zad_11;

public interface PriorityQueue<T extends Element<T>> {
    void insert(T element);

    T minimum();

    T extractMinimum();

    void decreaseValue(T element, int newValue);

    PriorityQueue<T> merge(PriorityQueue<T> other);

    boolean isEmpty();
}