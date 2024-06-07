package org.example.domain.pathfinder;

import java.util.List;
import java.util.Optional;

public class Graph {

  public List<Node> nodes;

  public Optional<Node> find(String nodeName) {
    return nodes.stream().filter(node -> node.getName().equalsIgnoreCase(nodeName)).findAny();
  }

  public Graph(List<Node> nodes) {
    this.nodes = nodes;
  }

  public List<Node> getNodes() {
    return nodes;
  }
}
