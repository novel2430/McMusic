package com.novel;

import java.util.List;
import org.slf4j.LoggerFactory;

/**
 * CaculateDataThread
 */
public class CaculateDataThread implements Runnable {
  private int index = 0;
  private List<FrameData> dataList;
  private CaculateFrameData caculateRes = new CaculateFrameData();
  private String fileName;

  public void run() {
    LoggerFactory.getLogger("outputdata").info("=== Caculate Thread Start ===");
    this.caculateRes.update(this.index, this.dataList);
    LoggerFactory.getLogger("outputdata").info(this.caculateRes.toJSONString());
    // create Writing Thread
    WriteThreadPool
        .addTarget(new WriteFileThread(this.fileName, ".", this.caculateRes.toJSONBeautyString()));
    LoggerFactory.getLogger("outputdata").info("=== Caculate Thread End ===");
  }

  public CaculateDataThread(int index, List<FrameData> list, String gameStartTime) {
    this.index = index;
    this.dataList = list;
    this.fileName = gameStartTime + "-" + Integer.toString(index) + ".json";
  }
}
