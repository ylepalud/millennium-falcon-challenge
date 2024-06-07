package org.example.domain.model;

public class Falcon {

  private final int MAX_AUTONOMY;

  private int currentFuel;

  public Falcon(int maxAutonomy) {
    MAX_AUTONOMY = maxAutonomy;
    currentFuel = MAX_AUTONOMY;
  }

  public boolean hasEnoughGas(int nextBoundCost) {
    return nextBoundCost <= currentFuel;
  }

  public void jump(int nextBoundCost) {
    if (!hasEnoughGas(nextBoundCost)) {
      throw new IllegalStateException("Falcon jumped over enough gas");
    }
    currentFuel -= nextBoundCost;
  }

  public void refuel() {
    currentFuel = MAX_AUTONOMY;
  }

  public int getCurrentFuel() {
    return currentFuel;
  }
}
