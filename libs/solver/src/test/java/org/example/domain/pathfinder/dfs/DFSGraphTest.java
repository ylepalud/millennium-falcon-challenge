package org.example.domain.pathfinder.dfs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.example.domain.model.Route;
import org.example.domain.pathfinder.Path;
import org.example.domain.pathfinder.Way;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    var result = graph.findAllPaths(new Node("A"), new Node("D"));

    // Then
    assertThat(result).containsExactlyInAnyOrder(
            new Path(List.of(new Way("A", "B", 1), new Way("B", "D", 3)), "A", "D"),
            new Path(List.of(new Way("A", "C", 2), new Way("C", "D", 4)), "A", "D")
    );
  }
}
