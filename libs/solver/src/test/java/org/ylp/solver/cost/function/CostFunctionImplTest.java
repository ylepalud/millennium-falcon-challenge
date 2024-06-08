package org.ylp.solver.cost.function;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ylp.solver.model.BountyHunter;
import org.ylp.solver.model.CountDown;
import org.ylp.solver.model.Falcon;
import org.ylp.solver.model.Planet;
import org.ylp.solver.step.cost.function.Action;
import org.ylp.solver.step.cost.function.CostFunction;
import org.ylp.solver.step.cost.function.CostFunctionImpl;
import org.ylp.solver.step.cost.function.SafestPath;
import org.ylp.solver.step.cost.function.travel;
import org.ylp.solver.step.pathfinder.Path;
import org.ylp.solver.step.pathfinder.Way;

class CostFunctionImplTest {

  List<Path> paths;
  Falcon falcon;
  List<BountyHunter> bountyHunters;
  CostFunction costFunction;

  @BeforeEach
  void setUp() {
    paths =
        List.of(
            new Path(
                List.of(new Way("Tatooine", "Hoth", 6), new Way("Hoth", "Endor", 1)),
                "Tatooine",
                "Endor"));
    falcon = new Falcon(6);
    bountyHunters = List.of(new BountyHunter(new Planet("Hoth"), 6));
    costFunction = new CostFunctionImpl();
  }

  @Test
  @DisplayName("Should compute odd with out bounty hunter")
  void shouldComputeOddWithOutBountyHunter() {
    // Given
    CountDown countDown = new CountDown(10);

    // When
    Optional<SafestPath> actual =
        costFunction.giveMeTheOdds(paths, countDown, falcon, bountyHunters);

    // Then
    assertThat(actual).isPresent();
    assertThat(actual.get().odds()).isCloseTo(0.1, Assertions.offset(0.01));

    assertThat(actual.get().travels())
        .containsExactly(
            new travel("Hoth", 6, Action.JUMP),
            new travel("Hoth", 1, Action.WAIT),
            new travel("Endor", 1, Action.JUMP));
  }

  @Test
  @DisplayName("Should not compute odd for unreachable path in time")
  void shouldNotComputeOddForUnreachablePathInTime() {
    // Given
    CountDown countDown = new CountDown(5);

    // When
    Optional<SafestPath> actual =
        costFunction.giveMeTheOdds(paths, countDown, falcon, bountyHunters);

    // Then
    assertThat(actual).isEmpty();
  }
}
