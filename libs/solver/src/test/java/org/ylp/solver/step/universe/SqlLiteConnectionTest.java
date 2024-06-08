package org.ylp.solver.step.universe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ylp.solver.step.universe.db.Route;
import org.ylp.solver.step.universe.db.SqlLiteConnection;

class SqlLiteConnectionTest {

  @Test
  @DisplayName("Should retrieve routes from universe db")
  void shouldRetrieveRoutesFromUniverseDb() {
    // Given
    var sqlLite = new SqlLiteConnection();

    // When
    var routes = sqlLite.retrieveAllRoutes("universe.db");

    // Then
    assertThat(routes)
        .containsExactlyInAnyOrder(
            new Route("Tatooine", "Dagobah", 6),
            new Route("Dagobah", "Endor", 4),
            new Route("Dagobah", "Hoth", 1),
            new Route("Hoth", "Endor", 1),
            new Route("Tatooine", "Hoth", 6));
  }

  @Test
  @DisplayName("Should return empty list if db isn't found")
  void shouldReturnEmptyListIfDbIsnTFound() {
    // Given
    var sqlLite = new SqlLiteConnection();

    // When
    var routes = sqlLite.retrieveAllRoutes("not_a_path_to_a_db");

    // Then
    assertThat(routes).isEmpty();
  }
}
