package org.example.domain.pathfinder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    @DisplayName("Should find edge if present in neighbor")
    void shouldFindEdgeIfPresentInNeighbor() {
        // Given
        var testNode = new Node("TestNode");
        var nodeA= new Node("A");

        Edge expected = new Edge(testNode, nodeA, 1);
        testNode.addEdge(expected);

        // When
        var actual = testNode.findEdge("A");

        // Then
        assertThat(actual).contains(expected);
    }

}