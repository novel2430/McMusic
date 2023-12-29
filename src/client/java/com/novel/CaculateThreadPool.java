package com.novel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadsPool
 */
public class CaculateThreadPool {
  private static ExecutorService pool;

  private static void init() {
    if (pool == null) {
      int poolSize = 10;
      pool = Executors.newFixedThreadPool(poolSize, new ThreadPoolNameFactory("Caculate"));
    }
  }

  private CaculateThreadPool() {}

  public static void addTarget(CaculateDataThread run) {
    init();
    pool.execute(run);
  }

}
