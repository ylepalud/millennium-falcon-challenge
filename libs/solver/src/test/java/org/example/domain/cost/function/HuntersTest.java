package org.example.domain.cost.function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.example.domain.model.BountyHunter;
import org.example.domain.model.Planet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HuntersTest {

  @ParameterizedTest
  @CsvSource({
    "A,1,false",
    "B,2,false",
    "A,2,true",
  })
  @DisplayName("Should predict if bounty hunter is present at a given time")
  void shouldPredictIfBountyHunterIsPresentAtAGivenTime(
      String planetName, int day, boolean expected) {
    // Given
    var hunters =
        new Hunters(
            List.of(new BountyHunter(new Planet("A"), 2), new BountyHunter(new Planet("B"), 4)));

    // When
    var actual = hunters.hasHunter(planetName, day);

    // Then
    assertThat(actual).isEqualTo(expected);
  }
}
