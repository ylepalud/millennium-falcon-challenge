package org.ylp.solver.step.pathfinder;

import java.util.List;

public interface Pathfinder {

  List<Path> thisIsTheWay(Node startNode, Node endNode) throws NoSolutionFound;
}
