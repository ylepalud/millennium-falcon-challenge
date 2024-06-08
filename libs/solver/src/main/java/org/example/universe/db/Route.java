package org.example.universe.db;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Route(String origin, String destination, @JsonAlias("travel_time") int travelTime) {}
