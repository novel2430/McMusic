package com.novel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadsPool
 */
public class CaculateThreadPool {
  private static ExecutorService pool =
      Executors.newFixedThreadPool(10, new ThreadPoolNameFactory("Caculate"));

  private CaculateThreadPool() {}

  public static void addTarget(CaculateDataThread run) {
    try {
      pool.execute(run);
    } catch (Exception e) {

    }
  }

  public static Boolean isDone() {
    return pool.isTerminated();
  }

  public static void close() {
    pool.shutdown();
  }

  public static void reset() {
    if (pool.isShutdown())
      pool = Executors.newFixedThreadPool(10, new ThreadPoolNameFactory("Caculate"));
  }

}
