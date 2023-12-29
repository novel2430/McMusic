package com.novel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * CaculateDataThread
 */
public class CaculateDataThread implements Runnable {
  private int index = 0;
  private List<FrameData> dataList;
  private CaculateFrameData caculateRes = new CaculateFrameData();
  private String fileName;

  private void writeFile() {
    try {
      BufferedWriter write =
          new BufferedWriter(new FileWriter(Config.get().getSavePath() + "/" + this.fileName));
      write.write(this.caculateRes.toJSONBeautyString());
      write.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void run() {
    Util.printLog("=== Caculate Thread Start ===");
    this.caculateRes.update(this.index, this.dataList);
    Util.printLog(this.caculateRes.toJSONString());
    writeFile();
    Util.printLog("=== Caculate Thread End ===");
  }

  public CaculateDataThread(int index, List<FrameData> list, String gameStartTime) {
    this.index = index;
    this.dataList = list;
    this.fileName = gameStartTime + "-" + Integer.toString(index) + ".json";
  }
}
