package org.example.domain.usecase;

import java.util.List;
import org.example.domain.model.Edge;

public interface RetrieveUniverse {

  List<Edge> findUniverse(String path);
}
