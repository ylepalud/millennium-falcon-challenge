package org.example.domain.pathfinder;

public class NoSolutionFound extends RuntimeException {

  public NoSolutionFound(String message) {
    super(message);
  }
}
