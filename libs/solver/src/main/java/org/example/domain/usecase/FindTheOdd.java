package org.example.domain.usecase;

import org.example.domain.model.Route;

import java.util.List;

public interface FindTheOdd {

    List<Route> findTheOdd(int autonomy, String departure, String destination, String pathToUniverse);

}
