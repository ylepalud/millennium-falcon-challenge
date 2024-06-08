package org.ylp.solver.step.find.odd;

import java.util.List;
import org.ylp.solver.model.Direction;
import org.ylp.solver.model.MissionConstraints;
import org.ylp.solver.model.Route;
import org.ylp.solver.step.cost.function.CostFunction;
import org.ylp.solver.step.cost.function.CostFunctionImpl;
import org.ylp.solver.step.cost.function.SafestPath;
import org.ylp.solver.step.pathfinder.DFSGraphFactory;
import org.ylp.solver.step.pathfinder.Node;
import org.ylp.solver.step.pathfinder.Pathfinder;
import org.ylp.solver.step.universe.RetrieveUniverse;

public class FindTheOddImpl implements FindTheOdd {

  private final RetrieveUniverse retrieveUniverse;

  private final CostFunction costFunction;

  public FindTheOddImpl(RetrieveUniverse retrieveUniverse) {
    this.retrieveUniverse = retrieveUniverse;

    costFunction = new CostFunctionImpl();
  }

  @Override
  public SafestPath findTheOdd(
      Direction direction, String pathToUniverse, MissionConstraints missionConstraints) {
    List<Route> universe = retrieveUniverse.findUniverse(pathToUniverse);

    Pathfinder graph = DFSGraphFactory.createGraph(universe);

    var path =
        graph.thisIsTheWay(new Node(direction.departure()), new Node(direction.destination()));

    return costFunction
        .giveMeTheOdds(
            path,
            missionConstraints.countDown(),
            missionConstraints.falcon(),
            missionConstraints.bountyHunters())
        .orElse(new SafestPath(1.0, List.of()));
  }
}
