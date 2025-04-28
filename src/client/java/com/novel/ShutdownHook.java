package com.novel;

/**
 * ShutdownHook
 */
public class ShutdownHook implements Runnable {
  public void run() {
    System.out.println("Doing Shutdown Process..");
    // Close Thread Pool
    // CaculateThreadPool.close();
    // Ensure Server know Player leave
    Util.httpRemovePlayer();
  }
}
