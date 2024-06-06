package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BountyHunterTest {

  @Test
  @DisplayName("Should not be able to instance invalid bounty hunter")
  void shouldNotBeAbleToInstanceInvalidBountyHunter() {
    // Given

    // When
    assertThrows(NullPointerException.class, () -> new BountyHunter(null, 1));
    assertThrows(IllegalArgumentException.class, () -> new BountyHunter(new Planet("aPlanet"), -1));

    // Then
  }
}
