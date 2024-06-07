package org.example.domain.usecase;

import java.util.List;
import org.example.domain.model.Route;

public interface RetrieveUniverse {

  List<Route> findUniverse(String path);
}
