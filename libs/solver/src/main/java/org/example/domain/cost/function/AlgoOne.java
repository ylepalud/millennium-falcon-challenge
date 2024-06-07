package org.example.domain.cost.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.example.domain.model.BountyHunter;
import org.example.domain.model.CountDown;
import org.example.domain.model.Falcon;
import org.example.domain.pathfinder.NoSolutionFound;
import org.example.domain.pathfinder.Path;
import org.example.domain.pathfinder.Way;

public class AlgoOne implements CostFunction {

  @Override
  public Optional<SafestPath> giveMeTheOdds(
      Path path, CountDown countDown, Falcon falcon, List<BountyHunter> bountyHunters) {
    var hunters = new Hunters(bountyHunters);

    return IntStream.range(0, path.steps())
        .mapToObj(
            allowedStop ->
                handleNoSolutionFind(
                    path,
                    new CountDown(countDown.getTotalPeriod()),
                    new Falcon(falcon.getMaxAutonomy()),
                    hunters,
                    allowedStop))
        .flatMap(Optional::stream)
        .min(Comparator.comparing(SafestPath::odds));
  }

  public Optional<SafestPath> handleNoSolutionFind(
      Path path,
      CountDown countDown,
      Falcon falcon,
      Hunters hunters,
      int isAllowedToAvoidBountyHunter) {
    try {
      return Optional.of(
          computeCost(path, countDown, falcon, hunters, isAllowedToAvoidBountyHunter));
    } catch (NoSolutionFound e) {
      return Optional.empty();
    }
  }

  public SafestPath computeCost(
      Path path,
      CountDown countDown,
      Falcon falcon,
      Hunters hunters,
      int isAllowedToAvoidBountyHunter) {
    int numberOfMeetingsWithHunter = 0;

    List<travel> travel = new ArrayList<>();

    for (var current : path.theWay()) {
      if (mustStopToRefuel(falcon, current)) {
        refuel(current, countDown, falcon, travel);
      }
      if (hasBountyHunterInNextJumpArrival(countDown, hunters, current)) {
        if (isAllowedToAvoidBountyHunter > 0) {
          refuel(current, countDown, falcon, travel);
          isAllowedToAvoidBountyHunter--;
        }

        if (hasBountyHunterInNextJumpArrival(countDown, hunters, current)) {
          numberOfMeetingsWithHunter++;
        }
      }

      jump(countDown, falcon, current, travel);
    }
    return new SafestPath(BountyHunterCapture.costFunction(numberOfMeetingsWithHunter), travel);
  }

  private static boolean hasBountyHunterInNextJumpArrival(
      CountDown countDown, Hunters hunters, Way next) {
    return hunters.hasHunter(next.start(), countDown.getCurrentDay() + next.travelTime());
  }

  private static void jump(CountDown countDown, Falcon falcon, Way next, List<travel> travel) {
    falcon.jump(next.travelTime());
    countDown.jump(next.travelTime());
    travel.add(new travel(next.end(), next.travelTime(), Action.JUMP));
  }

  private static void refuel(Way current, CountDown countDown, Falcon falcon, List<travel> travel) {
    falcon.refuel();
    countDown.waitForADay();
    travel.add(new travel(current.start(), 1, Action.WAIT));
  }

  private static boolean mustStopToRefuel(Falcon falcon, Way next) {
    return !falcon.hasEnoughGas(next.travelTime());
  }
}
