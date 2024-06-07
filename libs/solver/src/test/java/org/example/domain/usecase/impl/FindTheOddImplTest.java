package org.example.domain.usecase.impl;

import org.example.domain.cost.function.Action;
import org.example.domain.cost.function.SafestPath;
import org.example.domain.cost.function.travel;
import org.example.domain.model.BountyHunter;
import org.example.domain.model.CountDown;
import org.example.domain.model.Falcon;
import org.example.domain.model.Planet;
import org.example.domain.usecase.Direction;
import org.example.domain.usecase.FindTheOdd;
import org.example.domain.usecase.MissionConstraints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FindTheOddImplTest {

    @Test
    @DisplayName("Should compute the odd: exemple 1")
    void shouldComputeTheOddExemple1() {
        // Given
        FindTheOdd findTheOdd = new FindTheOddImpl();

        var direction = new Direction("Tatooine", "Endor");

        var missionConstraints = new MissionConstraints(
                new CountDown(7),
                new Falcon(6),
                List.of(
                        new BountyHunter(new Planet("Hoth"), 6),
                        new BountyHunter(new Planet("Hoth"), 7),
                        new BountyHunter(new Planet("Hoth"), 8)
                )
        );


        // When
        var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

        // Then
        assertThat(actual)
                .isEqualTo(new SafestPath(1.0, List.of()));
    }

    @Test
    @DisplayName("Should compute the odd: exemple 2")
    void shouldComputeTheOddExemple2() {
        // Given
        FindTheOdd findTheOdd = new FindTheOddImpl();

        var direction = new Direction("Tatooine", "Endor");

        var missionConstraints = new MissionConstraints(
                new CountDown(8),
                new Falcon(6),
                List.of(
                        new BountyHunter(new Planet("Hoth"), 6),
                        new BountyHunter(new Planet("Hoth"), 7),
                        new BountyHunter(new Planet("Hoth"), 8)
                )
        );


        // When
        var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

        // Then
        assertThat(actual)
                .isEqualTo(new SafestPath(0.19, List.of(
                        new travel("Hoth", 6, Action.JUMP),
                        new travel("Hoth", 1, Action.WAIT),
                        new travel("Endor", 1, Action.JUMP)
                )));
    }

    @Test
    @DisplayName("Should compute the odd: exemple 3")
    void shouldComputeTheOddExemple3() {
        // Given
        FindTheOdd findTheOdd = new FindTheOddImpl();

        var direction = new Direction("Tatooine", "Endor");

        var missionConstraints = new MissionConstraints(
                new CountDown(9),
                new Falcon(6),
                List.of(
                        new BountyHunter(new Planet("Hoth"), 6),
                        new BountyHunter(new Planet("Hoth"), 7),
                        new BountyHunter(new Planet("Hoth"), 8)
                )
        );


        // When
        var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

        // Then
        assertThat(actual)
                .isEqualTo(new SafestPath(0.1, List.of(
                        new travel("Hoth", 6, Action.JUMP),
                        new travel("Hoth", 1, Action.WAIT),
                        new travel("Endor", 1, Action.JUMP)
                )));
    }

    @Test
    @DisplayName("Should compute the odd: exemple 4")
    void shouldComputeTheOddExemple4() {
        // Given
        FindTheOdd findTheOdd = new FindTheOddImpl();

        var direction = new Direction("Tatooine", "Endor");

        var missionConstraints = new MissionConstraints(
                new CountDown(10),
                new Falcon(6),
                List.of(
                        new BountyHunter(new Planet("Hoth"), 6),
                        new BountyHunter(new Planet("Hoth"), 7),
                        new BountyHunter(new Planet("Hoth"), 8)
                )
        );

        // When
        var actual = findTheOdd.findTheOdd(direction, "", missionConstraints);

        // Then
        assertThat(actual)
                .isEqualTo(new SafestPath(0.1, List.of(
                        new travel("Hoth", 6, Action.JUMP),
                        new travel("Hoth", 1, Action.WAIT),
                        new travel("Endor", 1, Action.JUMP)
                )));
    }

}