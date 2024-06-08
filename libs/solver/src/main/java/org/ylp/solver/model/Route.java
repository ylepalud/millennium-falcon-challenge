package org.ylp.solver.model;

import java.util.Objects;

public record Route(String origin, String destination, int travelTime) {
  public Route {
    Objects.requireNonNull(origin, "origin must not be null");
    Objects.requireNonNull(destination, "destination must not be null");
    if (travelTime < 0) {
      throw new IllegalArgumentException("travelTime must not be negative");
    }
  }
}
