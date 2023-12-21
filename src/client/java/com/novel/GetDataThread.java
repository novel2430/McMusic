package com.novel;

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
    while (player != null) {
      oneTickData.printInfo();
      player = currentClient.player;
      try {
        Thread.sleep((int) (pauseSec * 1000));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    LoggerFactory.getLogger("outputdata").info("=== Get Data Thread End ===");
  }

}
