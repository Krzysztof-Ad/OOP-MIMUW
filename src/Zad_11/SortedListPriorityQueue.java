package Zad_11;

import java.util.Arrays;

public class SortedListPriorityQueue<T extends Element<T>> implements PriorityQueue<T> {
    private static class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<T> head;
    private int size;

    // Time complexity - pessimistic: O(1) ; average: O(1)
    public SortedListPriorityQueue() {
        this.head = null;
        this.size = 0;
    }

    // Time complexity - pessimistic: O(n log n) ; average: O(n log n)
    public SortedListPriorityQueue(T[] elements) {
        T[] sorted = elements.clone();
        Arrays.sort(sorted);
        Node<T> tail = null;
        for (T element : sorted) {
            Node<T> node = new Node<>(element, null);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            size++;
        }
    }

    // Time complexity - pessimistic: O(n) ; average: O(n)
    public void insert(T element) {
        if (head == null || element.compareTo(head.element) <= 0) {
            head = new Node<>(element, head);
        } else {
            Node<T> current = head;
            while (current.next != null && current.next.element.compareTo(element) < 0) {
                current = current.next;
            }
            current.next = new Node<>(element, current.next);
        }
        size++;
    }

    // Time complexity - pessimistic: O(1) ; average: O(1)
    public T minimum() {
        return head == null ? null : head.element;
    }

    // Time complexity - pessimistic: O(1) ; average: O(1)
    public T extractMinimum() {
        if (head == null) {
            return null;
        }
        T element = head.element;
        head = head.next;
        size--;
        return element;
    }

    // Time complexity - pessimistic: O(n) ; average: O(n)
    public void decreaseValue(T element, int newValue) {
        remove(element);
        element.setValue(newValue);
        insert(element);
    }

    // Time complexity - pessimistic: O(n) ; average: O(n)
    private void remove(T element) {
        if (head == null) {
            return;
        }
        if (head.element == element) {
            head = head.next;
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next != null && current.next.element != element) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }

    // Time complexity - pessimistic: O(n + m) ; average: O(n + m)
    public PriorityQueue<T> merge(PriorityQueue<T> other) {
        SortedListPriorityQueue<T> source = (SortedListPriorityQueue<T>) other;
        SortedListPriorityQueue<T> result = new SortedListPriorityQueue<>();
        Node<T> left = head;
        Node<T> right = source.head;
        Node<T> tail = null;
        while (left != null && right != null) {
            if (left.element.compareTo(right.element) <= 0) {
                tail = result.append(tail, new Node<>(left.element, null));
                left = left.next;
            } else {
                tail = result.append(tail, new Node<>(right.element, null));
                right = right.next;
            }
        }
        while (left != null) {
            tail = result.append(tail, new Node<>(left.element, null));
            left = left.next;
        }
        while (right != null) {
            tail = result.append(tail, new Node<>(right.element, null));
            right = right.next;
        }
        return result;
    }

    // Time complexity - pessimistic: O(1) ; average: O(1)
    private Node<T> append(Node<T> tail, Node<T> node) {
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        size++;
        return node;
    }

    // Time complexity - pessimistic: O(1) ; average: O(1)
    public boolean isEmpty() {
        return size == 0;
    }
}