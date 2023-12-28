package com.novel;

/**
 * CaculateDataThread
 */
public class CaculateDataThread implements Runnable {
  private int index = 0;

  public void setIndex(int index) {
    this.index = index;
  }

  public void run() {
    CaculateFrameData caculteRes =
        new CaculateFrameData(this.index, FrameDataQueueMap.getQueue(this.index));
  }

}
