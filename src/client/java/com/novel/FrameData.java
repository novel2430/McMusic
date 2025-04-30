package com.novel;

import java.util.Map;

/**
 * FrameData
 */
public record FrameData(String biome, String time, String climate, String temp, String hleath,
    String hunger, String wet, String fire, String sprint, String sneak, String ground, String lava,
    String rail, MonsterRecord mosterRec, String attacker, Map<String, Double> biomePredict) {
  public FrameData(String biome, String time, String climate, String temp, String hleath,
      String hunger, String wet, String fire, String sprint, String sneak, String ground,
      String lava, String rail, MonsterRecord mosterRec, String attacker, Map<String, Double> biomePredict) {
    this.biome = biome;
    this.time = time;
    this.climate = climate;
    this.temp = temp;
    this.hleath = hleath;
    this.hunger = hunger;
    this.wet = wet;
    this.fire = fire;
    this.sprint = sprint;
    this.sneak = sneak;
    this.ground = ground;
    this.lava = lava;
    this.rail = rail;
    this.mosterRec = mosterRec;
    this.attacker = attacker;
    this.biomePredict = biomePredict;
  }
}
