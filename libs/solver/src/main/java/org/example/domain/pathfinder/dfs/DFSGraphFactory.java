package org.example.domain.pathfinder.dfs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.domain.model.Route;

public class DFSGraphFactory {

  public static DFSGraph createGraph(List<Route> routes) {

    var graph = new DFSGraph();

    var nodes =
        routes.stream()
            .<String>mapMulti(
                (route, consumer) -> {
                  consumer.accept(route.origin());
                  consumer.accept(route.destination());
                })
            .distinct()
            .map(Node::new)
            .map(node -> Map.entry(node.name(), node))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    nodes.values().forEach(graph::addNode);

    routes.forEach(
        route ->
            graph.addEdge(
                nodes.get(route.origin()),
                nodes.get(route.destination()),
                route.travelTime())); // Add travel time

    return graph;
  }
}
