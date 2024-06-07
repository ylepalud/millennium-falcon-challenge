package org.example.domain.pathfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.example.domain.model.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DijkstraAlgorithmTest {

  @Test
  @DisplayName("Should compute shortest path")
  void shouldComputeShortestPath() throws NoSolutionFound {
    // Given
    var graph =
        GraphFactory.createGraph(
            List.of(
                new Route("Tatooine", "Dagobah", 6),
                new Route("Dagobah", "Endor", 4),
                new Route("Dagobah", "Hoth", 1),
                new Route("Hoth", "Endor", 1),
                new Route("Tatooine", "Hoth", 6)));

    var tatooine = graph.find("Tatooine").orElseThrow();
    var endor = graph.find("Endor").orElseThrow();

    Pathfinder dijkstraAlgorithm = new DijkstraAlgorithm(graph);

    // When
    var path = dijkstraAlgorithm.findTheWay(tatooine, endor);

    // Then
    assertThat(path.theWay())
        .containsExactly(new Way("Tatooine", "Hoth", 6), new Way("Hoth", "Endor", 1));
    assertThat(path.getTripCost()).isEqualTo(7);
  }

  @Test
  @DisplayName("Should throw exception if no path are found")
  void shouldThrowExceptionIfNoPathAreFound() {
    // Given
    var graph =
        GraphFactory.createGraph(
            List.of(
                new Route("Tatooine", "Dagobah", 6),
                new Route("Dagobah", "Hoth", 1),
                new Route("Endor", "SomewhereElse", 1),
                new Route("Tatooine", "Hoth", 6)));

    var tatooine = graph.find("Tatooine").orElseThrow();
    var endor = graph.find("Endor").orElseThrow();

    Pathfinder dijkstraAlgorithm = new DijkstraAlgorithm(graph);

    // When
    var exception =
        assertThrows(NoSolutionFound.class, () -> dijkstraAlgorithm.findTheWay(tatooine, endor));

    // Then
    assertThat(exception.getMessage()).isEqualTo("Endor isn't reachable from Tatooine");
  }
}
