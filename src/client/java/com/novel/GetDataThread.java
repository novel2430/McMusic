package com.novel;

import java.util.ArrayList;
import org.slf4j.LoggerFactory;
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
  private double pauseSec = 5.0;
  private long startTime = 0;
  private int currentMapIndex = 0;
  private ArrayList<FrameData> frameDataList = new ArrayList<FrameData>();

  public GetDataThread(ClientWorld world) {
    this.currentWorld = world;
  }

  public GetDataThread() {}

  public void setWorld(ClientWorld world) {
    this.currentWorld = world;
  }

  public void setPause(double sec) {
    this.pauseSec = sec;
  }

  public void run() {
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    LoggerFactory.getLogger("outputdata").info("=== Get Data Thread Start ===");
    ClientPlayerEntity player = currentClient.player;
    oneTickData.setPlayerWorld(player, currentWorld);
    startTime = System.currentTimeMillis();
    while (player != null) {
      if (System.currentTimeMillis() - startTime > pauseSec * 1000) {
        // Create Caculate Thread
        currentMapIndex++;
        // frameDataList.clear();
        startTime = System.currentTimeMillis();
      }
      // frameDataList.add(oneTickData.getFrameData());
      FrameDataQueueMap.addData(currentMapIndex, oneTickData.getFrameData());
      // oneTickData.printInfo();
      // try {
      // Thread.sleep((int) (pauseSec * 1000));
      // } catch (Exception e) {
      // e.printStackTrace();
      // }
      player = currentClient.player;
    }
    LoggerFactory.getLogger("outputdata").info("=== Get Data Thread End ===");
  }

}
