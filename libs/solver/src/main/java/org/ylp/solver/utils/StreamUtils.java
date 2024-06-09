package org.ylp.solver.utils;

import java.util.Collection;
import java.util.stream.Stream;

public final class StreamUtils {
  private StreamUtils() {}

  public static <T> Stream<T> toStream(Collection<T> collection) {
    return collection == null ? Stream.empty() : collection.stream();
  }
}
