package org.ylp.find.the.odd;

import org.ylp.solver.utils.FileUtil;
import org.ylp.solver.utils.Mapper;

public class MilleniumFalconConfigFileReader {
  private static final String MILLENIUM_FALCON_RESSOURCE = "millenium-falcon.json";

  public static MilleniumFalcon readMilleniumFalconConfigFile() {
    var json = FileUtil.readResource(MILLENIUM_FALCON_RESSOURCE);
    return Mapper.map(json, MilleniumFalcon.class);
  }
}
