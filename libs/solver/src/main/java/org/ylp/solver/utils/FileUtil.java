package org.ylp.solver.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class FileUtil {
  public static String readResource(String resourcePath) {
    try (var inputStream = readResourceInputStream(resourcePath)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("inputStream is null for resourcePath=" + resourcePath);
      }
      byte[] data = inputStream.readAllBytes();
      return new String(data, Charset.defaultCharset());
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private static InputStream readResourceInputStream(String resourcePath) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
  }
}
