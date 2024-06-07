package org.example.domain.usecase;

import org.example.domain.cost.function.SafestPath;

public interface FindTheOdd {

  SafestPath findTheOdd(
      Direction direction, String pathToUniverse, MissionConstraints missionConstraints);
}
