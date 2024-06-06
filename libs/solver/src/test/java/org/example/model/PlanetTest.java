package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.domain.model.Planet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlanetTest {

  @Test
  @DisplayName("Should not me be able to instance invalid Planet")
  void shouldNotMeBeAbleToInstanceInvalidPlanet() {
    // Given

    // When
    assertThrows(IllegalArgumentException.class, () -> new Planet(null));

    // Then
  }
}
