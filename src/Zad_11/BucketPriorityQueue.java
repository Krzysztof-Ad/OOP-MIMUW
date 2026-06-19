package Zad_11;

import java.util.ArrayList;
import java.util.List;

public class BucketPriorityQueue<T extends Element<T>> implements PriorityQueue<T> {
    private final List<T>[] buckets;
    private final int maxValue;
    private int size;
    private int currentMin;

    // Time complexity - pessimistic: O(maxValue) ; average: O(maxValue)
    public BucketPriorityQueue(int maxValue) {
        this.maxValue = maxValue;
        this.buckets = new List[maxValue + 1];
        this.size = 0;
        this.currentMin = 0;
    }

    // Time complexity - pessimistic: O(maxValue + n) ; average: O(maxValue + n)
    public BucketPriorityQueue(int maxValue, T[] elements) {
        this(maxValue);
        for (T element : elements) {
            insert(element);
        }
    }

    // Time complexity - pessimistic: O(1) ; average: O(1)
    public void insert(T element) {
        int index = element.value();
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        buckets[index].add(element);
        if (index < currentMin) {
            currentMin = index;
        }
        size++;
    }

    // Time complexity - pessimistic: O(maxValue) ; average: O(1)
    public T minimum() {
        advanceCurrentMin();
        if (currentMin > maxValue) {
            return null;
        }
        return buckets[currentMin].get(buckets[currentMin].size() - 1);
    }

    // Time complexity - pessimistic: O(maxValue) ; average: O(1)
    public T extractMinimum() {
        advanceCurrentMin();
        if (currentMin > maxValue) {
            return null;
        }
        size--;
        return buckets[currentMin].remove(buckets[currentMin].size() - 1);
    }

    // Time complexity - pessimistic: O(maxValue) ; average: O(1)
    private void advanceCurrentMin() {
        while (currentMin <= maxValue && (buckets[currentMin] == null || buckets[currentMin].isEmpty())) {
            currentMin++;
        }
    }

    // Time complexity - pessimistic: O(n) ; average: O(1)
    public void decreaseValue(T element, int newValue) {
        int oldValue = element.value();
        buckets[oldValue].remove(element);
        element.setValue(newValue);
        if (buckets[newValue] == null) {
            buckets[newValue] = new ArrayList<>();
        }
        buckets[newValue].add(element);
        if (newValue < currentMin) {
            currentMin = newValue;
        }
    }

    // Time complexity - pessimistic: O(maxValue + n + m) ; average: O(maxValue + n + m)
    public PriorityQueue<T> merge(PriorityQueue<T> other) {
        BucketPriorityQueue<T> source = (BucketPriorityQueue<T>) other;
        int mergedMaxValue = Math.max(maxValue, source.maxValue);
        BucketPriorityQueue<T> result = new BucketPriorityQueue<>(mergedMaxValue);
        copyInto(result, this);
        copyInto(result, source);
        return result;
    }

    // Time complexity - pessimistic: O(maxValue + k) ; average: O(maxValue + k)
    private static <T extends Element<T>> void copyInto(BucketPriorityQueue<T> target, BucketPriorityQueue<T> source) {
        for (int index = 0; index <= source.maxValue; index++) {
            if (source.buckets[index] != null) {
                for (T element : source.buckets[index]) {
                    target.insert(element);
                }
            }
        }
    }

    // Time complexity - pessimistic: O(1) ; average: O(1)
    public boolean isEmpty() {
        return size == 0;
    }
}