package org.ylp.solver.model;

import org.ylp.solver.step.pathfinder.NoSolutionFound;
import org.ylp.solver.step.pathfinder.Way;

public class CountDown {

  private final int totalPeriod;

  private int remainingTime;

  public CountDown(int remainingTime) {
    totalPeriod = remainingTime;
    this.remainingTime = remainingTime;
  }

  public void jump(int jumpCost) {
    if (jumpCost > remainingTime) {
      throw new NoSolutionFound("Not enough time to perform the jump. The empire has won");
    }
    remainingTime -= jumpCost;
  }

  public void jump(Way way) throws NoSolutionFound {
    int travelTime = way.travelTime();

    if (travelTime > remainingTime) {
      throw new NoSolutionFound("Not enough time to perform the jump. The empire has won");
    }

    remainingTime -= travelTime;
  }

  public void waitForADay() throws NoSolutionFound {
    if (remainingTime <= 0) {
      throw new NoSolutionFound("Not enough time to perform the jump. The empire has won");
    }
    remainingTime--;
  }

  public int getRemainingTime() {
    return remainingTime;
  }

  public int getCurrentDay() {
    return totalPeriod - remainingTime;
  }

  public int getTotalPeriod() {
    return totalPeriod;
  }
}
