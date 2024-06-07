package org.example.domain.pathfinder;

import java.util.List;

public class Graph {

    public List<Node> nodes;

    public Graph(List<Node> nodes)  {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
