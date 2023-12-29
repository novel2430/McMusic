package com.novel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadsPool
 */
public class WriteThreadPool {
  private static ExecutorService pool;

  private static void init() {
    if (pool == null) {
      int poolSize = 10;
      pool = Executors.newFixedThreadPool(poolSize, new ThreadPoolNameFactory("WriteFile"));
    }
  }

  private WriteThreadPool() {}

  public static void addTarget(WriteFileThread run) {
    init();
    pool.execute(run);
  }

}
