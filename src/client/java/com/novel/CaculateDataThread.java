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
  private FrameData data;

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
    if (Config.get().getCalculate())
      this.caculateRes.update(this.index, this.dataList);
    else
      this.caculateRes.updateNoCalculate(this.index, this.data);
    Util.printLog(this.caculateRes.toJSONString());
    writeFile();
    Util.printLog("=== Caculate Thread End ===");
  }

  public CaculateDataThread(int index, List<FrameData> list, String gameStartTime) {
    this.index = index;
    this.dataList = list;
    this.fileName = gameStartTime + "-" + Integer.toString(index) + ".json";
  }

  public CaculateDataThread(int index, FrameData data, String gameStartTime) {
    this.index = index;
    this.fileName = gameStartTime + "-" + Integer.toString(index) + ".json";
    this.data = data;
  }
}
