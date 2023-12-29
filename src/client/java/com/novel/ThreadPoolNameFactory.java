package com.novel;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolNameFactory
 */
public class ThreadPoolNameFactory implements ThreadFactory {
  private AtomicInteger threadCount = new AtomicInteger(1);
  private String moduleName;

  public ThreadPoolNameFactory(String moduleName) {
    this.moduleName = moduleName;
  }

  public Thread newThread(Runnable runnable) {
    Thread thread = new Thread(runnable);
    thread.setName(moduleName + "-" + threadCount.getAndIncrement() + "-Thread");
    return thread;
  }
}
