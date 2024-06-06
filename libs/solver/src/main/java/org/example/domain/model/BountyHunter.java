package org.example.domain.model;

import java.util.Objects;

public record BountyHunter(Planet planet, int day) {
  public BountyHunter {
    Objects.requireNonNull(planet, "planet cannot be null");
    if (day < 0) {
      throw new IllegalArgumentException("day cannot be negative");
    }
  }
}
