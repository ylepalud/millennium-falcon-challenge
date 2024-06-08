package org.ylp.solver.step.universe.impl;

import java.util.List;
import org.ylp.solver.model.Route;
import org.ylp.solver.step.universe.RetrieveUniverse;
import org.ylp.solver.step.universe.db.SqlLiteConnection;

public class RetrieveUniverseImpl implements RetrieveUniverse {
  @Override
  public List<Route> findUniverse(String path) {
    var connection = new SqlLiteConnection();
    return connection.retrieveAllRoutes(path).stream()
        .map(a -> new Route(a.origin(), a.destination(), a.travelTime()))
        .toList();
  }
}
