package com.novel;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.biome.Biome;

public class BiomePredict {
  private static Map<String, Double> cacheMap = null;
  private static int tickCount = 0;

  private static Map<String, Vec3i> getNearBiomePosMap(BlockPos playerBlockPos,
      ClientWorld currentWorld, RegistryKey<Biome> biomeKey) {
    int size = Config.get().getBiomePredictRange();
    int step = Config.get().getBiomePredictSampleRate();
    Map<String, Vec3i> result = new HashMap<String, Vec3i>();
    for (int dx = -size; dx <= size; dx += step) {
      for (int dy = -size; dy <= size; dy += step) {
        BlockPos check = playerBlockPos.add(dx, 0, dy);
        RegistryKey<Biome> currentBiomeKey = currentWorld.getBiome(check).getKey().get();
        if (currentBiomeKey == biomeKey)
          continue;
        Vec3i vec = new Vec3i(check.getX(), check.getY(), check.getZ());
        String biomeName = BiomeMap.getMap().get(currentBiomeKey);
        result.compute(biomeName, (key, oldVal) -> {
          if (oldVal == null || playerBlockPos.getManhattanDistance(vec) < playerBlockPos
              .getManhattanDistance(oldVal)) {
            return vec;
          }
          return oldVal;
        });
      }
    }
    return result;
  }

  private static Map<String, Double> doBiomePredict(ClientPlayerEntity player,
      BlockPos playerBlockPos, ClientWorld currentWorld, RegistryKey<Biome> biomeKey) {
    Map<String, Double> scoreMap = new HashMap<String, Double>();
    Map<String, Vec3i> biomePosMap = getNearBiomePosMap(playerBlockPos, currentWorld, biomeKey);
    Vec3d playerDir = player.getRotationVector().normalize();
    for (Map.Entry<String, Vec3i> entry : biomePosMap.entrySet()) {
      String biome = entry.getKey();
      Vec3d biomePos = new Vec3d(entry.getValue().getX() + 0.5, entry.getValue().getY() + 0.5,
          entry.getValue().getZ() + 0.5);

      Vec3d vecToBiome =
          biomePos.subtract(playerBlockPos.getX(), playerBlockPos.getY(), playerBlockPos.getZ())
              .normalize();
      double distance = vecToBiome.length();
      double cosTheta = playerDir.dotProduct(vecToBiome);

      if (cosTheta <= 0)
        continue;

      double score = Math.pow(cosTheta, Config.get().getDirectionAlpha())
          / Math.pow(distance + Config.get().getDistanceEpison(), Config.get().getDistanceBeta());
      scoreMap.put(biome, score);
    }
    return Util.softmaxScores(scoreMap);
  }

  public static Map<String, Double> getBiomePredict(ClientPlayerEntity player,
      BlockPos playerBlockPos, ClientWorld currentWorld, RegistryKey<Biome> biomeKey) {
    if (cacheMap == null || tickCount >= Config.get().getBiomePredictTickGap()) {
      tickCount = 0;
      cacheMap = doBiomePredict(player, playerBlockPos, currentWorld, biomeKey);
    } else {
      tickCount++;
    }
    return cacheMap;
  }
};
