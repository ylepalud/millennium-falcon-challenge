package org.ylp;

import java.util.List;
import java.util.concurrent.Callable;
import org.ylp.model.Empire;
import org.ylp.model.MilleniumFalcon;
import org.ylp.solver.model.BountyHunter;
import org.ylp.solver.model.CountDown;
import org.ylp.solver.model.Direction;
import org.ylp.solver.model.Falcon;
import org.ylp.solver.model.MissionConstraints;
import org.ylp.solver.model.Planet;
import org.ylp.solver.step.Solver;
import org.ylp.solver.utils.FileUtil;
import org.ylp.solver.utils.Mapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "give-me-the-odds",
    mixinStandardHelpOptions = true,
    version = "checksum 4.0",
    description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
public class Cli implements Callable<Double> {

  @Parameters(index = "0", description = "Path to the millenium falcon config file.")
  private String pathToFalcon;

  @Parameters(index = "1", description = "Path to the empire file.")
  private String pathToEmpireFile;

  @Override
  public Double call() throws Exception {

    var falcon = readFile(pathToFalcon, MilleniumFalcon.class);
    var empire = readFile(pathToEmpireFile, Empire.class);

    Direction direction = new Direction(falcon.departure(), falcon.arrival());

    var result = Solver.solve(direction, falcon.routesDb(), mapMissionCounstrants(empire, falcon));
    return result.odds();
  }

  private static MissionConstraints mapMissionCounstrants(Empire empire, MilleniumFalcon falcon) {
    return new MissionConstraints(
        new CountDown(empire.countdown()), new Falcon(falcon.autonomy()), maphunter(empire));
  }

  private static List<BountyHunter> maphunter(Empire empire) {
    return empire.bountyHunters().stream()
        .map(a -> new BountyHunter(new Planet(a.planet()), a.day()))
        .toList();
  }

  private <T> T readFile(String fileName, Class<T> clazz) {
    var falconJson = FileUtil.readResource(fileName);
    return Mapper.map(falconJson, clazz);
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new Cli()).execute(args);
    System.exit(exitCode);
  }
}
