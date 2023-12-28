package com.novel;

/**
 * FrameData
 */
public record FrameData(String biome, String time, String climate, String temp, String hleath,
    String hunger, String wet, String fire, String sprint, String sneak, String ground, String lava,
    String rail) {
  public FrameData(String biome, String time, String climate, String temp, String hleath,
      String hunger, String wet, String fire, String sprint, String sneak, String ground,
      String lava, String rail) {
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
  }
}
