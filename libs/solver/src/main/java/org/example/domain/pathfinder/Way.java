package org.example.domain.pathfinder;

import java.util.Objects;

public record Way(String planetName, int travelTime) {
  public Way {
    Objects.requireNonNull(planetName, "planetName must not be null");
    if (travelTime < 0) {
      throw new IllegalArgumentException("travelTime must not be negative");
    }
  }
}
