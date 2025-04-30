package com.novel;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Precipitation;

/**
 * OneTickData
 */
public class OneTickData {
  private ClientWorld currentWorld = null;
  private ClientPlayerEntity player = null;
  private Biome biome = null;
  private RegistryKey<Biome> biomeKey = null;
  private long time = 0;
  private BlockPos playerBlockPos = null;
  private MonsterEntityMap monsterMap = new MonsterEntityMap();

  public OneTickData(ClientWorld world, ClientPlayerEntity player) {
    this.currentWorld = world;
    this.player = player;
    this.playerBlockPos = player.getBlockPos();
    this.monsterMap.clearRecord();
    this.monsterMap.setWorld(world);
  }

  public OneTickData() {}

  private void update() {
    this.playerBlockPos = player.getBlockPos();
    RegistryEntry<Biome> biomeEntry = currentWorld.getBiome(this.playerBlockPos);
    this.biome = biomeEntry.value();
    this.biomeKey = biomeEntry.getKey().get();
    this.time = currentWorld.getTimeOfDay() % 24000;
  }

  public void setPlayer(ClientPlayerEntity player) {
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
    if (time >= 7000 && time < 11617) // afternoon
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

  private String buildAttacker() {
    if (player.getRecentDamageSource() != null
        && player.getRecentDamageSource().getAttacker() != null) {
      return "Attacked";
    } else
      return "Not Attacked";
  }

  private String buildClimate() {
    Precipitation type = biome.getPrecipitation(this.playerBlockPos);
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
    return "Clear";
  }

  private String buildTemperature() {
    boolean isCold = biome.isCold(this.playerBlockPos);
    if (isCold)
      return "Cold";
    else
      return "Not Cold";
  }

  private String buildFoodLevel() {
    int foodLevel = player.getHungerManager().getFoodLevel();
    if (foodLevel <= 0) {
      return "Starve";
    }
    if (foodLevel < 10) {
      return "Hungry";
    }
    if (foodLevel < 20) {
      return "Normal";
    }
    if (foodLevel >= 20) {
      return "Full";
    }
    return "Full";
  }

  private String buildHealth() {
    float hleath = player.getHealth();
    if (hleath <= 0) {
      return "Die";
    }
    if (hleath < 5) {
      return "Dying";
    }
    if (hleath < 15) {
      return "Normal";
    }
    if (hleath < 20) {
      return "Safe";
    }
    if (hleath >= 20) {
      return "Full";
    }
    return "Full";
  }

  private String buildWet() {
    boolean isTochingWaterOrRain = player.isTouchingWaterOrRain();
    boolean isTochingWater = player.isTouchingWater();
    if (isTochingWaterOrRain && !isTochingWater) {
      return "Being Rained";
    }
    if (isTochingWater) {
      return "Toching Water";
    }
    return "Not Touching Water";
  }

  private String buildSprinting() {
    if (player.isSprinting()) {
      return "Sprinting";
    }
    return "Not Sprinting";
  }

  private String buildSneaking() {
    if (player.isSneaking()) {
      return "Sneaking";
    }
    return "Not Sneaking";
  }

  private String buildLava() {
    if (player.isInLava()) {
      return "In Lava";
    }
    return "Not In Lava";
  }

  private String buildFire() {
    if (player.isOnFire()) {
      return "On Fire";
    }
    return "Not On Fire";
  }

  private String buildGround() {
    if (player.isOnGround()) {
      return "On Ground";
    }
    return "Not On Ground";
  }

  private String buildRail() {
    if (player.isOnRail()) {
      return "On Rail";
    }
    return "Not On Rail";
  }

  public void setPlayerWorld(ClientPlayerEntity player, ClientWorld world) {
    setPlayer(player);
    setWorld(world);
  }

  public FrameData getFrameData() {
    update();
    String attacker = buildAttacker();
    String biome = BiomeMap.getMap().get(biomeKey);
    String time = buildTime(this.time);
    String climate = buildClimate();
    String temperature = buildTemperature();
    String hleath = buildHealth();
    String food = buildFoodLevel();
    String wet = buildWet();
    String sprint = buildSprinting();
    String sneak = buildSneaking();
    String lava = buildLava();
    String fire = buildFire();
    String ground = buildGround();
    String rail = buildRail();
    monsterMap.update();
    return new FrameData(biome, time, climate, temperature, hleath, food, wet, fire, sprint, sneak,
        ground, lava, rail, monsterMap.getMonsterRecord(), attacker, BiomePredict
            .getBiomePredict(this.player, this.playerBlockPos, this.currentWorld, this.biomeKey));
  }
}
