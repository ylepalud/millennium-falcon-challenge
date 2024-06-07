package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.domain.model.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RouteTest {

  @Test
  @DisplayName("Should not be able to instance an invalid Edge")
  void shouldNotBeAbleToInstanceAnInvalidEdge() {
    // Given

    // When
    assertThrows(NullPointerException.class, () -> new Route(null, "destination", 1));
    assertThrows(NullPointerException.class, () -> new Route("origin", null, 1));
    assertThrows(IllegalArgumentException.class, () -> new Route("origin", "destination", -1));

    // Then
  }
}
