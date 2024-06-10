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
                List.of(
                    new Way("Tatouine", "Dagobah", 6),
                    new Way("Dagobah", "Hoth", 1),
                    new Way("Hoth", "Endor", 1)),
                "Tatooine",
                "Endor"));
    falcon = new Falcon(6);
    bountyHunters =
        List.of(
            new BountyHunter(new Planet("Hoth"), 6),
            new BountyHunter(new Planet("Hoth"), 7),
            new BountyHunter(new Planet("Hoth"), 8));
    costFunction = new CostFunctionImpl();
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

  @Test
  @DisplayName("Should find a perfect solution")
  void shouldFindAPerfectSolution() {
    // Given
    var countdown = new CountDown(10);

    // When

    Optional<SafestPath> actual =
        costFunction.giveMeTheOdds(paths, countdown, falcon, bountyHunters);

    // Then
    assertThat(actual).isPresent();
    assertThat(actual.get().odds()).isCloseTo(1.0, Assertions.offset(0.01));

    assertThat(actual.get().travels())
        .containsExactly(
            new travel("Dagobah", 6, Action.JUMP),
            new travel("Dagobah", 1, Action.WAIT),
            new travel("Dagobah", 1, Action.WAIT),
            new travel("Hoth", 1, Action.JUMP),
            new travel("Endor", 1, Action.JUMP));
  }
}
