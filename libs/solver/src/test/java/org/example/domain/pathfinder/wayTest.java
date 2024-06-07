package org.example.domain.pathfinder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class wayTest {

  @Test
  @DisplayName("Should be able to instance invalid way")
  void shouldBeAbleToInstanceInvalidWay() {
    // Given

    // When
    assertThrows(NullPointerException.class, () -> new Way(null, 1));
    assertThrows(IllegalArgumentException.class, () -> new Way("name", -1));

    // Then
  }
}
