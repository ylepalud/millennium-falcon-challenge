package org.ylp.solver.step;

import java.util.List;
import org.ylp.solver.model.BountyHunter;
import org.ylp.solver.model.CountDown;
import org.ylp.solver.model.Direction;
import org.ylp.solver.model.Falcon;
import org.ylp.solver.model.MilleniumFalcon;
import org.ylp.solver.model.MissionConstraints;
import org.ylp.solver.step.cost.function.SafestPath;
import org.ylp.solver.step.find.odd.FindTheOdd;
import org.ylp.solver.step.find.odd.FindTheOddImpl;
import org.ylp.solver.step.universe.RetrieveUniverse;
import org.ylp.solver.step.universe.impl.RetrieveUniverseImpl;
import org.ylp.solver.utils.FileUtil;
import org.ylp.solver.utils.Mapper;

public class Solver {
  private static final String MILLENIUM_FALCON_RESSOURCE = "millenium-falcon.json";

  public static SafestPath solve(CountDown countDown, List<BountyHunter> bountyHunters) {
    var json = FileUtil.readResource(MILLENIUM_FALCON_RESSOURCE);
    var milleniumFalcon = Mapper.map(json, MilleniumFalcon.class);

    var direction = new Direction(milleniumFalcon.departure(), milleniumFalcon.arrival());

    var constrains =
        new MissionConstraints(countDown, new Falcon(milleniumFalcon.autonomy()), bountyHunters);

    return solve(direction, milleniumFalcon.routesDb(), constrains);
  }

  private static SafestPath solve(
      Direction direction, String pathToUniverse, MissionConstraints missionConstraints) {
    RetrieveUniverse retrieveUniverse = new RetrieveUniverseImpl();
    FindTheOdd findTheOdd = new FindTheOddImpl(retrieveUniverse);

    return findTheOdd.findTheOdd(direction, pathToUniverse, missionConstraints);
  }
}
