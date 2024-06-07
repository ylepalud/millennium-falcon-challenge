package org.example.domain.pathfinder;

import java.util.List;

public record Path(
    int tripCost,
    List<Node> reversed) { // We need node name plus cost in order to compute cost function
}
