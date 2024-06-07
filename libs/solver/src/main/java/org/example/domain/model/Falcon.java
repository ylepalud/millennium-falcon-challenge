package org.example.domain.model;

public class Falcon {

  private final int maxAutonomy;

  private int currentFuel;

  public Falcon(int maxAutonomy) {
    this.maxAutonomy = maxAutonomy;
    currentFuel = maxAutonomy;
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
    currentFuel = maxAutonomy;
  }

  public int getCurrentFuel() {
    return currentFuel;
  }

  public int getMaxAutonomy() {
    return maxAutonomy;
  }
}
