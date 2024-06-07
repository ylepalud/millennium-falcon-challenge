package org.example.domain.cost.function;

import java.util.List;
import java.util.Optional;
import org.example.domain.model.BountyHunter;
import org.example.domain.model.CountDown;
import org.example.domain.model.Falcon;
import org.example.domain.pathfinder.Path;

public interface CostFunction {

  Optional<SafestPath> giveMeTheOdds(
      Path path, CountDown countDown, Falcon falcon, List<BountyHunter> bountyHunters);
}
