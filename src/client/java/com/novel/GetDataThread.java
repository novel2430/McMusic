package com.novel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

/**
 * GetDataThread
 */
public class GetDataThread implements Runnable {
  private ClientWorld currentWorld = null;
  private MinecraftClient currentClient = MinecraftClient.getInstance();
  private OneTickData oneTickData = new OneTickData();
  private long startTime = 0;
  private SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMMdd-HHmmss");

  public GetDataThread(ClientWorld world) {
    this.currentWorld = world;
  }

  public GetDataThread() {}

  public void setWorld(ClientWorld world) {
    this.currentWorld = world;
  }

  public void run() {
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Util.printLog("=== Get Data Thread Start ===");
    // game start time
    String nowDateStr = dateFormate.format(new Date());
    // update player, world
    ClientPlayerEntity player = currentClient.player;
    oneTickData.setPlayerWorld(player, currentWorld);
    // first frame data list
    ArrayList<FrameData> frameDataList = new ArrayList<FrameData>();
    // frame calculation index
    int currentIndex = 0;
    // Reset pool
    CaculateThreadPool.reset();
    startTime = System.currentTimeMillis();
    // do Calculate
    while (player != null && Config.get().getCalculate()) {
      if (System.currentTimeMillis() - startTime > Config.get().getPauseSec() * 1000) {
        // Create Caculate Thread
        CaculateThreadPool
            .addTarget(new CaculateDataThread(currentIndex, frameDataList, nowDateStr));
        frameDataList = new ArrayList<FrameData>();
        currentIndex++;
        startTime = System.currentTimeMillis();
      }
      frameDataList.add(oneTickData.getFrameData());
      player = currentClient.player;
    }
    // do no Calculate
    while (player != null && !Config.get().getCalculate()) {
      if (System.currentTimeMillis() - startTime > Config.get().getPauseSec() * 1000) {
        // Create Write Thread
        CaculateThreadPool.addTarget(
            new CaculateDataThread(currentIndex, oneTickData.getFrameData(), nowDateStr));
        currentIndex++;
        startTime = System.currentTimeMillis();
      }
      player = currentClient.player;
    }
    // Close pool
    CaculateThreadPool.close();
    // wait all threads done
    while (!CaculateThreadPool.isDone()) {
    }
    Util.printLog("=== Get Data Thread End ===");
  }

}
