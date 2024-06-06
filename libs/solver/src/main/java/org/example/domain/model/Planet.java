package org.example.domain.model;

import java.util.Optional;
import java.util.function.Predicate;

public record Planet(String name) {
  public Planet {
    Optional.ofNullable(name)
        .filter(Predicate.not(String::isBlank))
        .orElseThrow(IllegalArgumentException::new);
  }
}
