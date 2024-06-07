package org.example.domain.pathfinder;

public interface Pathfinder {

    Path findTheWay(Node startNode, Node endNode) throws NoSolutionFound;

}
