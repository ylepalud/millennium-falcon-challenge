package org.ylp.solver.step;

import org.ylp.solver.model.Direction;
import org.ylp.solver.model.MissionConstraints;
import org.ylp.solver.step.cost.function.SafestPath;
import org.ylp.solver.step.find.odd.FindTheOdd;
import org.ylp.solver.step.find.odd.FindTheOddImpl;
import org.ylp.solver.step.universe.RetrieveUniverse;
import org.ylp.solver.step.universe.impl.RetrieveUniverseImpl;

public class Solver {

  public static SafestPath solve(
      Direction direction, String pathToUniverse, MissionConstraints missionConstraints) {
    RetrieveUniverse retrieveUniverse = new RetrieveUniverseImpl();
    FindTheOdd findTheOdd = new FindTheOddImpl(retrieveUniverse);

    return findTheOdd.findTheOdd(direction, pathToUniverse, missionConstraints);
  }
}
