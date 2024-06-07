package org.example.domain.usecase.impl;

import java.util.List;
import org.example.adapter.DummyRetrieveUniverseAdapter;
import org.example.domain.cost.function.AlgoOne;
import org.example.domain.cost.function.CostFunction;
import org.example.domain.cost.function.SafestPath;
import org.example.domain.pathfinder.dfs.DFSGraphFactory;
import org.example.domain.pathfinder.dfs.Node;
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
    var graph = DFSGraphFactory.createGraph(retrieveUniverse.findUniverse(pathToUniverse));

    var path =
        graph.findAllPaths(new Node(direction.departure()), new Node(direction.destination()));

    return costFunction
        .giveMeTheOdds(
            path,
            missionConstraints.countDown(),
            missionConstraints.falcon(),
            missionConstraints.bountyHunters())
        .orElse(new SafestPath(1.0, List.of()));
  }
}
