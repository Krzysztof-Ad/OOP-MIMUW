package Zad_11;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int vertexCount;
    private final List<int[]>[] adjacency;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacency = new List[vertexCount];
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            adjacency[vertex] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int target, int weight) {
        adjacency[source].add(new int[] {target, weight});
    }

    public int vertexCount() {
        return vertexCount;
    }

    public List<int[]> neighbours(int vertex) {
        return adjacency[vertex];
    }
}