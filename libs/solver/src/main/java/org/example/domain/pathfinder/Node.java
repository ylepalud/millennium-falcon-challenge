package org.example.domain.pathfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

  private final String name;

  private final List<Edge> edges;

  public Node(String name) {
    this.name = name;
    this.edges = new ArrayList<>();
  }

  public void addEdge(Edge edge) {
    edges.add(edge);
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return Objects.equals(name, node.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
