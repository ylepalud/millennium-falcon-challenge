package org.example.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StreamUtilsTest {

    @Test
    @DisplayName("Should return empty stream on null")
    void shouldReturnEmptyToStreamOnNull() {
        // Given

        // When
        var actual = StreamUtils.toStream(null).toList();

        // Then
        assertThat(actual).isEmpty();
    }


    @Test
    @DisplayName("Should return stream on full collection")
    void shouldReturnToStreamOnFullCollection() {
        // Given

        // When
        var actual = StreamUtils.toStream(List.of(1, 2, 3)).toList();

        // Then
        assertThat(actual).containsExactly(1, 2, 3);
    }
}