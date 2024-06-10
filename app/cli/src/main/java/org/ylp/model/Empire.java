package org.ylp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Empire(int countdown, @JsonProperty("bounty_hunters") List<Hunter> bountyHunters) {}
