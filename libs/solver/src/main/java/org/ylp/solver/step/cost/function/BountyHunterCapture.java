package org.ylp.solver.step.cost.function;

public class BountyHunterCapture {

  public static double costFunction(int hit) {
    return 1.0 - costFunctionRec(hit);
  }

  private static double costFunctionRec(int hit) {
    if (hit == 0) {
      return 0.0;
    }

    if (hit == 1) {
      return 0.1;
    }

    var upperPart = Math.pow(9, hit - 1);
    var downPart = Math.pow(10, hit);

    var partialResult = upperPart / downPart;

    return costFunctionRec(hit - 1) + partialResult;
  }
}
