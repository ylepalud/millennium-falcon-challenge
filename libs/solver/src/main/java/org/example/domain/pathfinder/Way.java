package org.example.domain.pathfinder;

import java.util.Objects;

public record Way(String start, String end, int travelTime) {
  public Way {
    Objects.requireNonNull(start, "start must not be null");
    Objects.requireNonNull(end, "end must not be null");
    if (travelTime < 0) {
      throw new IllegalArgumentException("travelTime must not be negative");
    }
  }

  public boolean isDestination() {
    return start.equals(end);
  }
}
