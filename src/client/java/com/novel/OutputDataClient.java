package com.novel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class OutputDataClient implements ClientModInitializer {
  private GetDataThread getData = new GetDataThread();
  private Thread getDataThread = new Thread();
  private MonsterEntityMap monsterMap = new MonsterEntityMap();

  @Override
  public void onInitializeClient() {
    Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    ClientTickEvents.START_WORLD_TICK.register((world) -> {
      if (!getDataThread.isAlive()) {
        getData.setWorld(world);
        monsterMap.clearRecord();
        monsterMap.setWorld(world);
        getData.setMonsterMap(monsterMap);
        getDataThread = new Thread(getData, "Get Data Thread");
        getDataThread.start();
      }
    });
    ClientTickEvents.END_WORLD_TICK.register((world) -> {
      // System.out.println("END TICK");
      monsterMap.setWorld(world);
      monsterMap.update();
    });
  }
}

