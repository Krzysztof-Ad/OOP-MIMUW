package Zad_11;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Time complexity - pessimistic: O(n^2) ; average: O(n^2)
    public static int[] sortNumbers(Scanner scanner) {
        int count = scanner.nextInt();
        SortedListPriorityQueue<IntegerElement> queue = new SortedListPriorityQueue<>();
        for (int i = 0; i < count; i++) {
            queue.insert(new IntegerElement(scanner.nextInt()));
        }
        int[] sorted = new int[count];
        int index = 0;
        while (!queue.isEmpty()) {
            sorted[index] = queue.extractMinimum().value();
            index++;
        }
        return sorted;
    }

    // Time complexity - pessimistic: O(maxDistance + E * V) ; average: O(maxDistance + V + E)
    public static int dijkstra(Graph graph) {
        int vertexCount = graph.vertexCount();
        int maxWeight = 0;
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            for (int[] edge : graph.neighbours(vertex)) {
                if (edge[1] > maxWeight) {
                    maxWeight = edge[1];
                }
            }
        }
        int maxDistance = (vertexCount - 1) * maxWeight;
        int[] distance = new int[vertexCount];
        boolean[] settled = new boolean[vertexCount];
        Vertex[] node = new Vertex[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        BucketPriorityQueue<Vertex> queue = new BucketPriorityQueue<>(maxDistance);
        distance[0] = 0;
        node[0] = new Vertex(0, 0);
        queue.insert(node[0]);
        while (!queue.isEmpty()) {
            Vertex current = queue.extractMinimum();
            int currentId = current.id();
            if (settled[currentId]) {
                continue;
            }
            settled[currentId] = true;
            for (int[] edge : graph.neighbours(currentId)) {
                int target = edge[0];
                int weight = edge[1];
                if (settled[target]) {
                    continue;
                }
                int candidate = distance[currentId] + weight;
                if (candidate < distance[target]) {
                    distance[target] = candidate;
                    if (node[target] == null) {
                        node[target] = new Vertex(target, candidate);
                        queue.insert(node[target]);
                    } else {
                        queue.decreaseValue(node[target], candidate);
                    }
                }
            }
        }
        return distance[1] == Integer.MAX_VALUE ? -1 : distance[1];
    }

    public static void main(String[] arguments) {
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode == 1) {
            int[] sorted = sortNumbers(scanner);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < sorted.length; i++) {
                if (i > 0) {
                    builder.append(' ');
                }
                builder.append(sorted[i]);
            }
            System.out.println(builder.toString());
        } else {
            int vertexCount = scanner.nextInt();
            int edgeCount = scanner.nextInt();
            Graph graph = new Graph(vertexCount);
            for (int i = 0; i < edgeCount; i++) {
                int source = scanner.nextInt();
                int target = scanner.nextInt();
                int weight = scanner.nextInt();
                graph.addEdge(source, target, weight);
            }
            System.out.println(dijkstra(graph));
        }
        scanner.close();
    }
}