package org.example.domain.pathfinder.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.example.domain.pathfinder.Path;
import org.example.domain.pathfinder.Way;

public class DFSGraph {

  private final Map<Node, List<Node>> adjacencyList;

  private final Map<Trip, Integer> destinationPerTravelTime;

  public DFSGraph() {
    this.adjacencyList = new HashMap<>();
    this.destinationPerTravelTime = new HashMap<>();
  }

  public void addNode(Node node) {
    adjacencyList.putIfAbsent(node, new ArrayList<>());
  }

  public void addEdge(Node source, Node destination, int travelTime) {
    adjacencyList.get(source).add(destination);
    destinationPerTravelTime.put(new Trip(source, destination), travelTime);
    destinationPerTravelTime.put(new Trip(destination, source), travelTime);
  }

  public List<Path> thisIsTheWay(Node start, Node end) {
    List<List<Node>> allPaths = new ArrayList<>();
    Stack<Node> currentPath = new Stack<>();
    Set<Node> visited = new HashSet<>();
    findAllPaths(start, end, visited, currentPath, allPaths);

    return allPaths.stream().map(this::rebuildPaths).toList();
  }

  private void findAllPaths(
      Node current,
      Node end,
      Set<Node> visited,
      Stack<Node> currentPath,
      List<List<Node>> allPaths) {
    visited.add(current);
    currentPath.push(current);

    if (current.equals(end)) {
      allPaths.add(new ArrayList<>(currentPath));
    } else {
      for (Node neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
          findAllPaths(neighbor, end, visited, currentPath, allPaths);
        }
      }
    }

    currentPath.pop();
    visited.remove(current);
  }

  private Path rebuildPaths(List<Node> rowPath) {
    List<Way> ways = new ArrayList<>();

    for (int i = 0; i < rowPath.size() - 1; i++) {
      Node first = rowPath.get(i);
      Node second = rowPath.get(i + 1);

      var travelTime = destinationPerTravelTime.get(new Trip(first, second));

      ways.add(new Way(first.name(), second.name(), travelTime));
    }

    return new Path(ways, rowPath.getFirst().name(), rowPath.getLast().name());
  }

  record Trip(Node first, Node second) {}
}
