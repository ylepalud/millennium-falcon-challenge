package org.ylp;

import static org.ylp.Cli.COMMAND_NAME;

import java.io.IOException;
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
import org.ylp.solver.utils.Mapper;
import org.ylp.utils.OutSideFileReader;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = COMMAND_NAME,
    mixinStandardHelpOptions = true,
    version = "1.0.0",
    description = "Print chance of succes of mission Tatooine to Endor.")
public class Cli implements Callable<Double> {

  public static final String COMMAND_NAME = "give-me-the-odds";

  @Parameters(index = "0", description = "Path to the millenium falcon config file.")
  private String pathToFalcon;

  @Parameters(index = "1", description = "Path to the empire file.")
  private String pathToEmpireFile;

  @Override
  public Double call() throws Exception {

    var falcon = readInPutFile(pathToFalcon, MilleniumFalcon.class);
    var empire = readInPutFile(pathToEmpireFile, Empire.class);

    Direction direction = new Direction(falcon.departure(), falcon.arrival());

    var result = Solver.solve(direction, falcon.routesDb(), mapMissionCounstrants(empire, falcon));

    double odds = result.odds();

    System.out.println(
        "Bip bip.... Your chances of success in this mission are " + odds * 100 + "%");
    return odds;
  }

  private static <T> T readInPutFile(String fileName, Class<T> type) throws IOException {
    var jsonFile = OutSideFileReader.read(fileName);
    return Mapper.map(jsonFile, type);
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

  public static void main(String[] args) {
    int exitCode = new CommandLine(new Cli()).setCommandName(COMMAND_NAME).execute(args);
    System.exit(exitCode);
  }
}
