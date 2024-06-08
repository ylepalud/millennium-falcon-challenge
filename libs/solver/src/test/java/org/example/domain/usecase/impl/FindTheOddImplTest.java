package org.example.domain.usecase.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import org.example.domain.cost.function.Action;
import org.example.domain.cost.function.SafestPath;
import org.example.domain.cost.function.travel;
import org.example.domain.model.BountyHunter;
import org.example.domain.model.CountDown;
import org.example.domain.model.Falcon;
import org.example.domain.model.Planet;
import org.example.domain.model.Route;
import org.example.domain.usecase.Direction;
import org.example.domain.usecase.FindTheOdd;
import org.example.domain.usecase.MissionConstraints;
import org.example.universe.RetrieveUniverse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindTheOddImplTest {

  @Mock RetrieveUniverse retrieveUniverse;

  private FindTheOdd findTheOdd;
  private Falcon falcon;

  @BeforeEach
  void setUp() {
    falcon = new Falcon(6);
    when(retrieveUniverse.findUniverse(any()))
        .thenReturn(
            List.of(
                new Route("Tatooine", "Dagobah", 6),
                new Route("Dagobah", "Endor", 4),
                new Route("Dagobah", "Hoth", 1),
                new Route("Hoth", "Endor", 1),
                new Route("Tatooine", "Hoth", 6)));

    findTheOdd = new FindTheOddImpl(retrieveUniverse);
  }

  @Test
  @DisplayName("Should compute the odd: exemple 1")
  void shouldComputeTheOddExemple1() {
    // Given
    var direction = new Direction("Tatooine", "Endor");

    var missionConstraints = new MissionConstraints(new CountDown(7), falcon, bountyHunters);

    // When
    var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

    // Then
    assertThat(actual).isEqualTo(new SafestPath(1.0, List.of()));
  }

  private static List<BountyHunter> bountyHunters =
      List.of(
          new BountyHunter(new Planet("Hoth"), 6),
          new BountyHunter(new Planet("Hoth"), 7),
          new BountyHunter(new Planet("Hoth"), 8));

  @Test
  @DisplayName("Should compute the odd: exemple 2")
  void shouldComputeTheOddExemple2() {
    // Given
    var direction = new Direction("Tatooine", "Endor");

    var missionConstraints = new MissionConstraints(new CountDown(8), falcon, bountyHunters);

    // When
    var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

    // Then
    assertThat(actual)
        .isEqualTo(
            new SafestPath(
                0.19,
                List.of(
                    new travel("Hoth", 6, Action.JUMP),
                    new travel("Hoth", 1, Action.WAIT),
                    new travel("Endor", 1, Action.JUMP))));
  }

  @Test
  @DisplayName("Should compute the odd: exemple 3")
  void shouldComputeTheOddExemple3() {
    // Given
    var direction = new Direction("Tatooine", "Endor");

    var missionConstraints = new MissionConstraints(new CountDown(9), falcon, bountyHunters);

    // When
    var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

    // Then
    assertThat(actual)
        .isEqualTo(
            new SafestPath(
                0.1,
                List.of(
                    new travel("Dagobah", 6, Action.JUMP),
                    new travel("Dagobah", 1, Action.WAIT),
                    new travel("Hoth", 1, Action.JUMP),
                    new travel("Endor", 1, Action.JUMP))));
  }

  @Test
  @DisplayName("Should compute the odd: exemple 4")
  void shouldComputeTheOddExemple4() {
    // Given
    var direction = new Direction("Tatooine", "Endor");

    var missionConstraints = new MissionConstraints(new CountDown(10), falcon, bountyHunters);

    // When
    var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

    // Then
    assertThat(actual)
        .isEqualTo(
            new SafestPath(
                0.1,
                List.of(
                    new travel("Dagobah", 6, Action.JUMP),
                    new travel("Dagobah", 1, Action.WAIT),
                    new travel("Hoth", 1, Action.JUMP),
                    new travel("Endor", 1, Action.JUMP))));
  }
}
