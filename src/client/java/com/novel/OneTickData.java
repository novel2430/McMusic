package com.novel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

/**
 * OneTickData
 */
public class OneTickData {
  private ClientWorld currentWorld = null;
  private ClientPlayerEntity player = null;
  private Logger logger = LoggerFactory.getLogger("outputdata");
  private Biome biome = null;
  private RegistryKey<Biome> biomeKey = null;
  private long time = 0;

  public OneTickData(ClientWorld world, ClientPlayerEntity player) {
    this.currentWorld = world;
    this.player = player;
    update();
  }

  public OneTickData() {}

  private void update() {
    RegistryEntry<Biome> biomeEntry = currentWorld.getBiome(player.getBlockPos());
    this.biome = biomeEntry.value();
    this.biomeKey = biomeEntry.getKey().get();
    this.time = currentWorld.getTimeOfDay();

  }

  private void setPlayer(ClientPlayerEntity player) {
    this.player = player;
  }

  private void setWorld(ClientWorld world) {
    this.currentWorld = world;
  }

  public void setPlayerWorld(ClientPlayerEntity player, ClientWorld world) {
    setPlayer(player);
    setWorld(world);
    update();
  }

  public void printInfo() {
    String BiomeStr = BiomeMap.getMap().get(biomeKey);
    String time = Long.toString(this.time);
    // logger.info("Biome : " + BiomeStr + "\nTime : " + time);
  }
}
