package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EdgeTest {

  @Test
  @DisplayName("Should not be able to instance an invalid Edge")
  void shouldNotBeAbleToInstanceAnInvalidEdge() {
    // Given

    // When
    assertThrows(NullPointerException.class, () -> new Edge(null, "destination", 1));
    assertThrows(NullPointerException.class, () -> new Edge("origin", null, 1));
    assertThrows(
        IllegalArgumentException.class,
        () -> new Edge("origin", "destination", -1));

    // Then
  }
}
