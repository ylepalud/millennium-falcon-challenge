package org.ylp.solver.utils;

import static org.slf4j.LoggerFactory.getLogger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

public class Mapper {

  private static final Logger LOGGER = getLogger(Mapper.class);

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private Mapper() {}

  public static <T> T map(String fileName, Class<T> clazz) {
    try {
      return objectMapper.readValue(fileName, clazz);
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
