package org.ylp.solver.step.universe;

import java.util.List;
import org.ylp.solver.model.Route;

public interface RetrieveUniverse {

  List<Route> findUniverse(String path);
}
