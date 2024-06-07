package org.example.domain.usecase;

import java.util.List;
import org.example.domain.model.BountyHunter;
import org.example.domain.model.CountDown;
import org.example.domain.model.Falcon;

public record MissionConstraints(
    CountDown countDown, Falcon falcon, List<BountyHunter> bountyHunters) {}
