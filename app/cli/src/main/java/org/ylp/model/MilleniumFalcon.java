package org.ylp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MilleniumFalcon(
    @JsonProperty("autonomy") int autonomy,
    @JsonProperty("departure") String departure,
    @JsonProperty("arrival") String arrival,
    @JsonProperty("routes_db") String routesDb) {}
