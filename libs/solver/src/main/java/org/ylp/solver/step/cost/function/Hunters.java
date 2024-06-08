package org.ylp.solver.step.cost.function;

import java.util.List;
import java.util.function.Predicate;
import org.ylp.solver.model.BountyHunter;

public class Hunters {

  private final List<BountyHunter> bountyHunters;

  public Hunters(List<BountyHunter> bountyHunters) {
    this.bountyHunters = bountyHunters;
  }

  public boolean hasHunter(String planetName, int day) {
    var isPresentAtThisDay = isPlanet(planetName).and(isPresentAtThisDay(day));

    return bountyHunters.stream().anyMatch(isPresentAtThisDay);
  }

  private Predicate<BountyHunter> isPlanet(String planetName) {
    return bountyHunter -> bountyHunter.planet().name().equalsIgnoreCase(planetName);
  }

  private Predicate<BountyHunter> isPresentAtThisDay(int day) {
    return bountyHunter -> bountyHunter.day() == day;
  }
}
