package org.ylp.solver.model;

import java.util.List;

public record MissionConstraints(
    CountDown countDown, Falcon falcon, List<BountyHunter> bountyHunters) {}
