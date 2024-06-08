package org.ylp.solver.step.find.odd;

import org.ylp.solver.model.Direction;
import org.ylp.solver.model.MissionConstraints;
import org.ylp.solver.step.cost.function.SafestPath;

public interface FindTheOdd {

  SafestPath findTheOdd(
      Direction direction, String pathToUniverse, MissionConstraints missionConstraints);
}
