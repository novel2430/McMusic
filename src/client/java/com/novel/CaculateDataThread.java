package com.novel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CaculateDataThread
 */
public class CaculateDataThread implements Runnable {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  private void writeFile(String fileName, CaculateFrameData writeData) {
    try {
      BufferedWriter write =
          new BufferedWriter(new FileWriter(Config.get().getSavePath() + "/" + fileName));
      write.write(writeData.toJSONBeautyString());
      write.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    Util.printLog("=== Analyzer Thread Start ===");
    // Init
    LocalDateTime now = LocalDateTime.now();
    String startDateTime = now.format(formatter);
    Util.httpAddPlayer();

    int currentIndex = 0;
    // List<PlayerSnapshot> buffer = new ArrayList<>();
    long startTime = System.currentTimeMillis();

    while (Config.get().getCalculate() && Util.isGameRunning()) {
      if (System.currentTimeMillis() - startTime > Config.get().getPauseSec() * 1000) {
        if(FrameDataBuffer.getPushCount() > 0){
          Util.printLog("=== Calculate ! ===");
          // Do Calculate
          CaculateFrameData calculateData = new CaculateFrameData(currentIndex);
          calculateData.buildDataFromBuffer();
          // Print Log
          Util.printLog(calculateData.toJSONString());
          // Write to Disk
          // writeFile(startDateTime + "-" + Integer.toString(currentIndex) + ".json", calculateData);
          // Http Update
          Util.httpAddPlayerData(startDateTime, calculateData);
          // Clean
          FrameDataBuffer.clear();
          currentIndex++;
        }
        startTime = System.currentTimeMillis();
      }
      // Slow down little bit!
      try {
          Thread.sleep(50);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }

    // flush Stuff
    FrameDataBuffer.clear();

    Util.httpRemovePlayer();

    Util.printLog("=== Analyzer Thread End ===");
  }

  public CaculateDataThread() {}

}
