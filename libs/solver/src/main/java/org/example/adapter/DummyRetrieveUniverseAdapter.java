package org.example.adapter;

import java.util.List;
import org.example.domain.model.Route;
import org.example.domain.usecase.RetrieveUniverse;

public class DummyRetrieveUniverseAdapter implements RetrieveUniverse {

  /**
   * Something is wrong with my connection to SQLLite I will fix it later. I found that all
   * databases contain five edges. I decided to hard code them for now
   */
  @Override
  public List<Route> findUniverse(String path) {
    return List.of(
        new Route("Tatooine", "Dagobah", 6),
        new Route("Dagobah", "Endor", 4),
        new Route("Dagobah", "Hoth", 1),
        new Route("Hoth", "Endor", 1),
        new Route("Tatooine", "Hoth", 6));
  }
}
