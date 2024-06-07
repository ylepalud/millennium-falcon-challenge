package org.example.domain.pathfinder;

import org.example.domain.model.Route;
import org.example.utils.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GraphFactory {

    public static Graph createGraph(List<Route> routes) {
        Map<String, Node> nodes = createNodes(routes);

        routes.forEach(route -> {
            var origin = nodes.get(route.origin());
            var destination = nodes.get(route.destination());
            origin.addEdge(new Edge(origin, destination, route.travelTime()));
            origin.addEdge(new Edge(destination, origin, route.travelTime())); // Keep ?
        });

        return new Graph(new ArrayList<>(nodes.values()));
    }

    private static Map<String, Node> createNodes(List<Route> routes) {
        return routes.stream()
                .<String>mapMulti((current, consumer) -> {
                    consumer.accept(current.origin());
                    consumer.accept(current.destination());
                }).distinct()
                .map(Node::new)
                .map(node -> Map.entry(node.getName(), node))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
