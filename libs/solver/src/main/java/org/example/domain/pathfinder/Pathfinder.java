package org.example.domain.pathfinder;

import java.util.List;

public interface Pathfinder {

  List<Path> findTheWay(Node startNode, Node endNode) throws NoSolutionFound;
}
