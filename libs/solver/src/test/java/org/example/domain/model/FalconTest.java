package org.example.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FalconTest {

    Falcon falcon;

    @BeforeEach
    void init() {
        falcon = new Falcon(5);
    }

    @Test
    @DisplayName("Should be able to consume and refuel gas")
    void shouldBeAbleToConsumeAndRefuelGas() {
        // Given

        // When
        falcon.jump(3);

        // Then
        assertThat(falcon.getCurrentFuel()).isEqualTo(2);
        falcon.refuel();
        assertThat(falcon.getCurrentFuel()).isEqualTo(5);
    }

    @Test
    @DisplayName("Should not be able to jump without enough gas")
    void shouldNotBeAbleToJumpWithoutEnoughGas() {
        // Given

        // When
        assertThrows(IllegalStateException.class, () -> falcon.jump(6));

        // Then
    }

}