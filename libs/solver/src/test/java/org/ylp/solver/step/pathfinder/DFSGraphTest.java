package org.ylp.solver.step.pathfinder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ylp.solver.model.Route;

class DFSGraphTest {

  @Test
  @DisplayName("Should find all path")
  void shouldFindAllPath() {
    // Given
    var graph =
        DFSGraphFactory.createGraph(
            List.of(
                new Route("A", "B", 1),
                new Route("A", "C", 2),
                new Route("B", "D", 3),
                new Route("C", "D", 4)));

    // When
    var result = graph.thisIsTheWay(new Node("A"), new Node("D"));

    // Then
    assertThat(result)
        .containsExactlyInAnyOrder(
            new Path(List.of(new Way("A", "B", 1), new Way("B", "D", 3)), "A", "D"),
            new Path(List.of(new Way("A", "C", 2), new Way("C", "D", 4)), "A", "D"));
  }
}
