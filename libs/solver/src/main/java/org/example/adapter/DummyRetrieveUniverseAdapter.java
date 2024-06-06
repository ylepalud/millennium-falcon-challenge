package org.example.adapter;

import org.example.domain.model.Edge;
import org.example.domain.usecase.RetrieveUniverse;

import java.util.List;

public class DummyRetrieveUniverseAdapter implements RetrieveUniverse {

    /**
     * Something is wrong with my connection to SQLLite
     * I will fix it later. I found that all databases contain five edges. I decided to hard code them for now
     * */
    @Override
    public List<Edge> findUniverse(String path) {
        return List.of(
                new Edge("Tatooine", "Dagobah", 6),
                new Edge("Dagobah", "Endor", 4),
                new Edge("Dagobah", "Hoth", 1),
                new Edge("Hoth", "Endor", 1),
                new Edge("Tatooine", "Hoth", 6)
        );
    }
}
