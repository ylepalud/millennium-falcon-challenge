package org.example.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.example.domain.pathfinder.NoSolutionFound;
import org.example.domain.pathfinder.Way;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountDownTest {

  @Test
  @DisplayName("Should abstract countdown")
  /*
   * Test sequence of changes on CountDown
   */
  void shouldAbstractCountdown() {
    // Given
    var countDown = new CountDown(5);

    assertThat(countDown.getCurrentDay()).isEqualTo(0);
    assertThat(countDown.getRemainingTime()).isEqualTo(5);

    countDown.waitForADay();

    assertThat(countDown.getCurrentDay()).isEqualTo(1);
    assertThat(countDown.getRemainingTime()).isEqualTo(4);

    countDown.jump(new Way("a", "B", 2));

    assertThat(countDown.getCurrentDay()).isEqualTo(3);
    assertThat(countDown.getRemainingTime()).isEqualTo(2);

    var exception = assertThrows(NoSolutionFound.class, () -> countDown.jump(new Way("b", "C", 5)));
    assertThat(exception.getMessage())
        .isEqualTo("Not enough time to perform the jump. The empire has won");
  }
}
