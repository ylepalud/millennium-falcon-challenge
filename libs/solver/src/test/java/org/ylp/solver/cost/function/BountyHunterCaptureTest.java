package org.ylp.solver.cost.function;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.ylp.solver.step.cost.function.BountyHunterCapture;

class BountyHunterCaptureTest {

  @ParameterizedTest
  @CsvSource({
    "0,1.0", "1,0.9", "2,0.81", "3,0.729", "4,0.656", "5,0.590",
  })
  @DisplayName("Should compute bounty hunter capture probability")
  void shouldComputeBountyHunterCaptureProbability(int hit, double expected) {
    // Given

    // When
    var actual = BountyHunterCapture.costFunction(hit);

    // Then
    assertThat(actual).isCloseTo(expected, Assertions.offset(0.01));
  }
}
