package org.ylp.solver.step.cost.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.ylp.solver.model.BountyHunter;
import org.ylp.solver.model.CountDown;
import org.ylp.solver.model.Falcon;
import org.ylp.solver.step.pathfinder.NoSolutionFound;
import org.ylp.solver.step.pathfinder.Path;
import org.ylp.solver.step.pathfinder.Way;

public class CostFunctionImpl implements CostFunction {

  @Override
  public Optional<SafestPath> giveMeTheOdds(
      List<Path> paths, CountDown countDown, Falcon falcon, List<BountyHunter> bountyHunters) {
    var hunters = new Hunters(bountyHunters);

    return paths.stream()
        .flatMap(path -> computeCostForGivenPath(path, countDown, falcon, hunters))
        .max(Comparator.comparing(SafestPath::odds));
  }

  public Stream<SafestPath> computeCostForGivenPath(
      Path path, CountDown countDown, Falcon falcon, Hunters hunters) {
    return IntStream.range(0, 10)
        .mapToObj(
            allowedStop ->
                handleNoSolutionFind(
                    path,
                    new CountDown(countDown.getTotalPeriod()),
                    new Falcon(falcon.getMaxAutonomy()),
                    hunters,
                    allowedStop))
        .flatMap(Optional::stream);
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
      int allowedNumberOfEncounterWithBountyHunter) {
    int numberOfMeetingsWithHunter = 0;

    List<travel> travel = new ArrayList<>();

    for (var current : path.theWay()) {
      if (isBountyHunterInCurrentNode(countDown, hunters, current)) {
        numberOfMeetingsWithHunter++;
      }

      if (mustStopToRefuel(falcon, current)) {
        numberOfMeetingsWithHunter += refuel(current, countDown, falcon, travel, hunters);
      }

      while (hasBountyHunterInNextJumpArrival(countDown, hunters, current)
          && allowedNumberOfEncounterWithBountyHunter > 0) {
        numberOfMeetingsWithHunter += refuel(current, countDown, falcon, travel, hunters);
        allowedNumberOfEncounterWithBountyHunter--;
      }

      jump(countDown, falcon, current, travel);
    }

    return new SafestPath(BountyHunterCapture.costFunction(numberOfMeetingsWithHunter), travel);
  }

  private static boolean hasBountyHunterInNextJumpArrival(
      CountDown countDown, Hunters hunters, Way next) {
    return hunters.hasHunter(next.end(), countDown.getCurrentDay() + next.travelTime());
  }

  private static boolean isBountyHunterInCurrentNode(
      CountDown countDown, Hunters hunters, Way current) {
    return hunters.hasHunter(current.start(), countDown.getCurrentDay());
  }

  private static void jump(CountDown countDown, Falcon falcon, Way next, List<travel> travel) {
    falcon.jump(next.travelTime());
    countDown.jump(next.travelTime());
    travel.add(new travel(next.end(), next.travelTime(), Action.JUMP));
  }

  private static int refuel(
      Way current, CountDown countDown, Falcon falcon, List<travel> travel, Hunters hunters) {
    falcon.refuel();
    countDown.waitForADay();
    travel.add(new travel(current.start(), 1, Action.WAIT));

    return isBountyHunterInCurrentNode(countDown, hunters, current) ? 1 : 0;
  }

  private static boolean mustStopToRefuel(Falcon falcon, Way next) {
    return !falcon.hasEnoughGas(next.travelTime());
  }
}
