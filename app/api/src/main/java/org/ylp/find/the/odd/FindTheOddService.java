package org.ylp.find.the.odd;

import static org.ylp.solver.utils.StreamUtils.toStream;

import com.ylp.model.SolvePost200Response;
import com.ylp.model.SolvePost200ResponsePathInner;
import com.ylp.model.SolvePostRequest;
import java.util.List;
import org.springframework.stereotype.Service;
import org.ylp.solver.model.BountyHunter;
import org.ylp.solver.model.CountDown;
import org.ylp.solver.model.Direction;
import org.ylp.solver.model.Falcon;
import org.ylp.solver.model.MissionConstraints;
import org.ylp.solver.model.Planet;
import org.ylp.solver.step.Solver;
import org.ylp.solver.step.cost.function.SafestPath;
import org.ylp.solver.step.cost.function.travel;
import org.ylp.solver.utils.FileUtil;

@Service
public class FindTheOddService {
  private static final String MILLENIUM_FALCON_RESSOURCE = "millenium-falcon.json";

  public SolvePost200Response findSolution(SolvePostRequest request) {
    var bountyHunter = mapHunter(request);

    CountDown countDown = mapCountDown(request);

    var milleniumFalcon = FileUtil.readFile(MILLENIUM_FALCON_RESSOURCE, MilleniumFalcon.class);

    Direction direction = new Direction(milleniumFalcon.departure(), milleniumFalcon.arrival());
    MissionConstraints missionConstraints =
        new MissionConstraints(countDown, new Falcon(milleniumFalcon.autonomy()), bountyHunter);

    return mapResponse(Solver.solve(direction, milleniumFalcon.routesDb(), missionConstraints));
  }

  private SolvePost200Response mapResponse(SafestPath solve) {
    var response = new SolvePost200Response();
    response.setOdd(solve.odds());
    response.setPath(toStream(solve.travels()).map(FindTheOddService::mapPath).toList());
    return response;
  }

  private static SolvePost200ResponsePathInner mapPath(travel travel) {
    var path = new SolvePost200ResponsePathInner();
    path.action(travel.action().name());
    path.setPlanet(travel.planet());
    path.setTimeTravel(travel.time());
    return path;
  }

  private static CountDown mapCountDown(SolvePostRequest request) {
    return new CountDown(request.getCountDown());
  }

  private static List<BountyHunter> mapHunter(SolvePostRequest request) {
    return toStream(request.getBountyHunters())
        .map(hunter -> new BountyHunter(new Planet(hunter.getPlanet()), hunter.getDay()))
        .toList();
  }
}
