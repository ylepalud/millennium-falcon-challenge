package org.example.domain.usecase;

import java.util.List;
import org.example.domain.model.Route;

public interface FindTheOdd {

  List<Route> findTheOdd(int autonomy, String departure, String destination, String pathToUniverse);
}
