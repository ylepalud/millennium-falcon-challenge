package org.example.domain.pathfinder;

import java.util.List;
import java.util.Objects;

public record Path(List<Way> theWay) {

  public Path {
    Objects.requireNonNull(theWay, "theWay must not be null");
  }

  public int getTripCost() {
    return theWay.stream().map(Way::travelTime).reduce(0, Integer::sum);
  }

  public int remainingTripCost(Way startWay) {
      boolean hasHitStartWay = false;
      int tripCost = 0;

      for (var current : theWay) {
          if (hasHitStartWay) {
              tripCost += current.travelTime();
              break;
          }

          if (startWay.equals(current)) {
              tripCost += current.travelTime();
              hasHitStartWay = true;
          }

      }

      return tripCost;
  }
}
