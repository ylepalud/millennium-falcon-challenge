package org.example.domain.usecase.impl;

import java.util.List;
import org.example.adapter.DummyRetrieveUniverseAdapter;
import org.example.domain.cost.function.AlgoOne;
import org.example.domain.cost.function.CostFunction;
import org.example.domain.cost.function.SafestPath;
import org.example.domain.pathfinder.DijkstraAlgorithm;
import org.example.domain.pathfinder.Graph;
import org.example.domain.pathfinder.GraphFactory;
import org.example.domain.pathfinder.Node;
import org.example.domain.pathfinder.Pathfinder;
import org.example.domain.usecase.Direction;
import org.example.domain.usecase.FindTheOdd;
import org.example.domain.usecase.MissionConstraints;
import org.example.domain.usecase.RetrieveUniverse;

public class FindTheOddImpl implements FindTheOdd {

  private final RetrieveUniverse retrieveUniverse;

  private final CostFunction costFunction;

  public FindTheOddImpl() {
    this.retrieveUniverse = new DummyRetrieveUniverseAdapter();

    costFunction = new AlgoOne();
  }

  @Override
  public SafestPath findTheOdd(
      Direction direction, String pathToUniverse, MissionConstraints missionConstraints) {
    var graph = GraphFactory.createGraph(retrieveUniverse.findUniverse(pathToUniverse));

    Pathfinder pathfinder = new DijkstraAlgorithm(graph);

    var start = retrieveNode(graph, direction.departure(), "Departure not found: ");
    var end = retrieveNode(graph, direction.destination(), "Destination not found: ");

    var path = pathfinder.findTheWay(start, end);

    return costFunction
        .giveMeTheOdds(
            path,
            missionConstraints.countDown(),
            missionConstraints.falcon(),
            missionConstraints.bountyHunters())
        .orElse(new SafestPath(1.0, List.of()));
  }

  private static Node retrieveNode(Graph graph, String direction, String errorMessage) {
    return graph
        .find(direction)
        .orElseThrow(() -> new IllegalArgumentException(errorMessage + direction));
  }
}
