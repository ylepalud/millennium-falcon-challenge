package org.ylp.solver.step.cost.function;

import java.util.List;
import java.util.Optional;
import org.ylp.solver.model.BountyHunter;
import org.ylp.solver.model.CountDown;
import org.ylp.solver.model.Falcon;
import org.ylp.solver.step.pathfinder.Path;

public interface CostFunction {

  Optional<SafestPath> giveMeTheOdds(
      List<Path> path, CountDown countDown, Falcon falcon, List<BountyHunter> bountyHunters);
}
