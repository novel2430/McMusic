package com.novel;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

class CaculateDataCmp implements Comparator<CaculateFrameData> {
  public int compare(CaculateFrameData o1, CaculateFrameData o2) {
    return o1.getIndex() - o2.getIndex();
  }
}


/**
 * WritingBlockingQueue
 */
public class WritingBlockingQueue {
  private static PriorityBlockingQueue<CaculateFrameData> queue;

  private static void init() {
    if (queue == null) {
      queue = new PriorityBlockingQueue<CaculateFrameData>(100, new CaculateDataCmp());
    }
  }

  public static void push(CaculateFrameData data) {
    init();
    queue.offer(data);
  }

  public static CaculateFrameData pop() {
    init();
    return queue.poll();
  }

  public static Boolean isEmpty() {
    init();
    return queue.isEmpty();
  }
}
