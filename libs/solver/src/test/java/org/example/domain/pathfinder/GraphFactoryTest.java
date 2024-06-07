package org.example.domain.pathfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.example.domain.model.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GraphFactoryTest {

  @Test
  @DisplayName("Should create complete graph")
  void shouldCreateCompleteGraph() {
    // Given
    var routes = List.of(new Route("A", "B", 1), new Route("B", "C", 2), new Route("C", "A", 3));

    // When
    var actual = GraphFactory.createGraph(routes);

    // Then
    assertThat(actual.getNodes()).hasSize(3);
  }
}
