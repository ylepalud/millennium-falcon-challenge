package org.ylp.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OutSideFileReader {

  private OutSideFileReader() {}

  public static String read(String fileName) {
    StringBuilder result = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result.toString();
  }
}
