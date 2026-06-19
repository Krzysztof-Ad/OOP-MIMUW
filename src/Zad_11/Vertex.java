package Zad_11;

public class Vertex implements Element<Vertex> {
    private final int id;
    private int distance;

    public Vertex(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    public int id() {
        return id;
    }

    public int value() {
        return distance;
    }

    public void setValue(int newValue) {
        this.distance = newValue;
    }

    public int compareTo(Vertex other) {
        return Integer.compare(distance, other.distance);
    }
}