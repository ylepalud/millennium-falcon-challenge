package org.example.universe.impl;

import java.util.List;
import org.example.domain.model.Route;
import org.example.universe.RetrieveUniverse;
import org.example.universe.db.SqlLiteConnection;

public class RetrieveUniverseImpl implements RetrieveUniverse {
  @Override
  public List<Route> findUniverse(String path) {
    var connection = new SqlLiteConnection();
    return connection.retrieveAllRoutes(path).stream()
        .map(a -> new Route(a.origin(), a.destination(), a.travelTime()))
        .toList();
  }
}
