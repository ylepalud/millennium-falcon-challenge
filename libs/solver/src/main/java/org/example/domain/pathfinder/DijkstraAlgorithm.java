package org.example.domain.pathfinder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    final Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> solve(Node startNode) {
        Map<Node, Integer> distances = new HashMap<>();

        graph.getNodes().forEach(node -> distances.put(node, Integer.MAX_VALUE));

        distances.put(startNode, 0);

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        minHeap.add(startNode);

        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            int currentDistance = distances.get(current);

            // Visit all neighboring nodes
            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.destination();
                int newDistance = currentDistance + edge.travelTime();

                // If new distance is shorter, update it and add neighbor to queue
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    minHeap.add(neighbor);
                }
            }
        }

        for (Node node : distances.keySet()) {
            System.out.println("Distance from " + startNode.getName() + " to " + node.getName() + ": " + distances.get(node));
        }

        return List.of();
    }
}
