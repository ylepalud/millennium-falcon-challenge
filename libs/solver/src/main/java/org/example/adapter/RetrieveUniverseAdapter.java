package org.example.adapter;

import java.util.List;
import org.example.domain.model.Edge;
import org.example.domain.usecase.RetrieveUniverse;
import org.example.infrastructure.SqlLiteConnection;

public class RetrieveUniverseAdapter implements RetrieveUniverse {
  @Override
  public List<Edge> findUniverse(String path) {
    var connection = new SqlLiteConnection();
    return connection.retrieveAllRoutes(path).stream()
        .map(a -> new Edge(a.origin(), a.destination(), a.travelTime()))
        .toList();
  }
}
