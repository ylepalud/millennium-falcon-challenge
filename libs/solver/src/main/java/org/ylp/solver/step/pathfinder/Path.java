package org.ylp.solver.step.pathfinder;

import java.util.List;
import java.util.Objects;

public record Path(List<Way> theWay, String start, String end) {

  public Path {
    Objects.requireNonNull(theWay, "theWay must not be null");
    Objects.requireNonNull(start, "start must not be null");
    Objects.requireNonNull(end, "end must not be null");
  }

  public int getTripCost() {
    return theWay.stream().map(Way::travelTime).reduce(0, Integer::sum);
  }
}
