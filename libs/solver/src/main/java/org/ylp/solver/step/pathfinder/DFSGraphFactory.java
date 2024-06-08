package org.ylp.solver.step.pathfinder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ylp.solver.model.Route;

public class DFSGraphFactory {

  public static Pathfinder createGraph(List<Route> routes) {

    var graph = new DFSGraph();

    var nodes = createNodesAndGroupThemByName(routes);

    nodes.values().forEach(graph::addNode);

    routes.forEach(
        route ->
            graph.addEdge(
                nodes.get(route.origin()), nodes.get(route.destination()), route.travelTime()));

    return graph;
  }

  private static Map<String, Node> createNodesAndGroupThemByName(List<Route> routes) {
    return routes.stream()
        .<String>mapMulti(
            (route, consumer) -> {
              consumer.accept(route.origin());
              consumer.accept(route.destination());
            })
        .distinct()
        .map(Node::new)
        .map(node -> Map.entry(node.name(), node))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
