package org.example.domain.pathfinder;

import java.util.ArrayList;
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

  public Path solve(Node startNode, Node endNode) throws NoSolutionFound {
    Map<Node, Integer> distances = new HashMap<>();
    Map<Node, Node> predecessors = new HashMap<>();

    graph.getNodes().forEach(node -> distances.put(node, Integer.MAX_VALUE));

    distances.put(startNode, 0);

    PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(distances::get));
    minHeap.add(startNode);

    while (!minHeap.isEmpty()) {
      Node current = minHeap.poll();
      int currentDistance = distances.get(current);

      if (current.equals(endNode)) {
        return reconstructPath(startNode, endNode, predecessors, distances);
      }

      for (Edge edge : current.getEdges()) {
        Node neighbor = edge.destination();
        int newDistance = currentDistance + edge.travelTime();

        if (newDistance < distances.get(neighbor)) {
          distances.put(neighbor, newDistance);
          predecessors.put(neighbor, current);
          minHeap.add(neighbor);
        }
      }
    }

    throw new NoSolutionFound();
  }

  private Path reconstructPath(
      Node start, Node end, Map<Node, Node> predecessors, Map<Node, Integer> distances) {
    List<Node> path = new ArrayList<>();
    Node current = end;

    while (current != null && current != start) {
      path.add(current);
      current = predecessors.get(current);
    }

    if (current == start) {
      path.add(start);
    }

    return new Path(distances.get(end), path.reversed());
  }
}
