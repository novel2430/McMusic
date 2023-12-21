package com.novel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Precipitation;

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
  }

  public OneTickData() {}

  private void update() {
    RegistryEntry<Biome> biomeEntry = currentWorld.getBiome(player.getBlockPos());
    this.biome = biomeEntry.value();
    this.biomeKey = biomeEntry.getKey().get();
    this.time = currentWorld.getTimeOfDay() % 24000;

  }

  private void print(String str) {
    this.logger.info(str);
  }

  private void setPlayer(ClientPlayerEntity player) {
    this.player = player;
  }

  private void setWorld(ClientWorld world) {
    this.currentWorld = world;
  }

  private String buildTime(long time) {
    if (time >= 0 && time < 450) // sunrise
      return "Sunrise";
    if (time >= 450 && time < 5000) // day
      return "Day";
    if (time >= 5000 && time < 7000) // noon
      return "Noon";
    if (time >= 5000 && time < 11617) // afternoon
      return "Afternoon";
    if (time >= 11617 && time < 13702) // sunset
      return "Sunset";
    if (time >= 13702 && time < 18000) // night
      return "Night";
    if (time >= 18000 && time < 22200) // midnight
      return "Midnight";
    if (time >= 22200) // sunrise
      return "Sunrise";
    return "";
  }

  private String buildClimate() {
    Precipitation type = biome.getPrecipitation(player.getBlockPos());
    boolean isRain = currentWorld.isRaining();
    boolean isTunder = currentWorld.isThundering();
    switch (type) {
      case RAIN:
        if (isRain && isTunder)
          return "Thundering";
        else if (isRain)
          return "Raining";
        break;
      case SNOW:
        if (isRain)
          return "Snow";
        break;
      case NONE:
        if (isRain)
          return "Cloudy";
        break;
      default:
        break;
    }
    return "Sunny";
  }

  public void setPlayerWorld(ClientPlayerEntity player, ClientWorld world) {
    setPlayer(player);
    setWorld(world);
  }

  public void printInfo() {
    update();
    String BiomeStr = BiomeMap.getMap().get(biomeKey);
    String timeStr = buildTime(this.time);
    String climateStr = buildClimate();
    print("Biome : " + BiomeStr + ", Time : " + timeStr + ", Climate : " + climateStr);
  }
}
