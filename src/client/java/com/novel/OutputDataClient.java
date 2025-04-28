package com.novel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class OutputDataClient implements ClientModInitializer {
  private MinecraftClient currentClient = MinecraftClient.getInstance();
  private CaculateDataThread caculateDataTarget = new CaculateDataThread();
  private Thread calculateThread;

  @Override
  public void onInitializeClient() {
    Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    ClientTickEvents.START_WORLD_TICK.register((world) -> {
      // Start Thread
      if(calculateThread == null || !calculateThread.isAlive()){
        calculateThread = new Thread(caculateDataTarget);
        calculateThread.start();
      }
    });
    ClientTickEvents.END_WORLD_TICK.register((world) -> {
      // System.out.println("END TICK");
      OneTickData oneTickData = new OneTickData(world, currentClient.player);
      FrameData frameData = oneTickData.getFrameData();
      FrameDataBuffer.push(frameData);
    });

    ClientTickEvents.START_CLIENT_TICK.register((world) -> {
      Util.updateGameStatus(currentClient); 
    });

    ClientTickEvents.END_CLIENT_TICK.register((world) -> {
      Util.updateGameStatus(currentClient); 
    });
  }
}

