package com.novel;

import org.slf4j.LoggerFactory;

/**
 * Util
 */
public class Util {
  public static void printLog(String text) {
    if (Config.get().getDebug())
      LoggerFactory.getLogger("ouputdata").info(text);
  }

  public static void printWarnLog(String text) {
    LoggerFactory.getLogger("ouputdata").warn(text);
  }

}
