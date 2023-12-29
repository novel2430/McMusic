package com.novel;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * CaculateDataThread
 */
public class CaculateDataThread implements Runnable {
  private int index = 0;
  private CaculateFrameData caculateRes = new CaculateFrameData();
  private String fileName;
  private FrameData data;
  private int dataSize = 0;

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

  private void init(int index, String gameStartTime) {
    this.index = index;
    this.fileName = gameStartTime + "-" + Integer.toString(index) + ".json";
  }

  public void run() {
    Util.printLog("=== Caculate Thread Start ===");
    if (Config.get().getCalculate())
      // this.caculateRes.update(this.index, this.dataList);
      this.caculateRes.caculateAllMap(this.dataSize);
    else
      this.caculateRes.updateNoCalculate(this.index, this.data);
    Util.printLog(this.caculateRes.toJSONString());
    writeFile();
    Util.printLog("=== Caculate Thread End ===");
  }

  public CaculateDataThread(int index, FrameData data, String gameStartTime) {
    init(index, gameStartTime);
    this.data = data;
  }

  public CaculateDataThread(int index, int dataSize, CaculateFrameData caculateData,
      String gameStartTime) {
    init(index, gameStartTime);
    this.dataSize = dataSize;
    this.caculateRes = caculateData;
  }
}
