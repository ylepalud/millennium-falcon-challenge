package org.example.domain.pathfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

  @Test
  @DisplayName("Should not be able to instance invalid Path")
  void shouldNotBeAbleToInstanceInvalidPath() {
    // Given

    // When
    assertThrows(NullPointerException.class, () -> new Path(null));

    // Then

  }

  @Test
  @DisplayName("Should be able to compute Path cost")
  void shouldBeAbleToComputePathCost() {
    // Given
    var theWay = List.of(new Way("A", 1), new Way("B", 2), new Way("B", 3));

    var path = new Path(theWay);

    // When
    var actual = path.getTripCost();

    // Then
    assertThat(actual).isEqualTo(6);
  }
}
