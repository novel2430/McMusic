package com.novel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

/**
 * BiomeMap
 */
public class BiomeMap {
  private static final Map<RegistryKey<Biome>, String> biomeMap = new ConcurrentHashMap<RegistryKey<Biome>, String>();

  private BiomeMap() {}

  private static void buildMap() {
    // biomeMap = new HashMap<RegistryKey<Biome>, String>();
    biomeMap.put(BiomeKeys.BADLANDS, "Badlands");
    biomeMap.put(BiomeKeys.BEACH, "Beach");
    biomeMap.put(BiomeKeys.BIRCH_FOREST, "Birch Forest");
    biomeMap.put(BiomeKeys.BAMBOO_JUNGLE, "Bamboo Jungle");
    biomeMap.put(BiomeKeys.BASALT_DELTAS, "Basalt Deltas");
    biomeMap.put(BiomeKeys.COLD_OCEAN, "Cold Ocean");
    biomeMap.put(BiomeKeys.CHERRY_GROVE, "Cherry Grove");
    biomeMap.put(BiomeKeys.CRIMSON_FOREST, "Crimson Forest");
    biomeMap.put(BiomeKeys.DESERT, "Desert");
    biomeMap.put(BiomeKeys.DEEP_DARK, "Deep Dark");
    biomeMap.put(BiomeKeys.DEEP_OCEAN, "Deep Ocean");
    biomeMap.put(BiomeKeys.DARK_FOREST, "Dark Forest");
    biomeMap.put(BiomeKeys.DEEP_COLD_OCEAN, "Deep Cold Ocean");
    biomeMap.put(BiomeKeys.DRIPSTONE_CAVES, "Dripstone Caves");
    biomeMap.put(BiomeKeys.DEEP_FROZEN_OCEAN, "Deep Frozen Ocean");
    biomeMap.put(BiomeKeys.DEEP_LUKEWARM_OCEAN, "Deep Lukewarm Ocean");
    biomeMap.put(BiomeKeys.END_BARRENS, "End Barrens");
    biomeMap.put(BiomeKeys.END_MIDLANDS, "End Midlands");
    biomeMap.put(BiomeKeys.END_HIGHLANDS, "End Highlands");
    biomeMap.put(BiomeKeys.ERODED_BADLANDS, "Eroded Badlands");
    biomeMap.put(BiomeKeys.FOREST, "Forest");
    biomeMap.put(BiomeKeys.FROZEN_OCEAN, "Frozen Ocean");
    biomeMap.put(BiomeKeys.FROZEN_PEAKS, "Frozen Peaks");
    biomeMap.put(BiomeKeys.FROZEN_RIVER, "Frozen River");
    biomeMap.put(BiomeKeys.FLOWER_FOREST, "Flower Forest");
    biomeMap.put(BiomeKeys.GROVE, "Grove");
    biomeMap.put(BiomeKeys.ICE_SPIKES, "Ice Spikes");
    biomeMap.put(BiomeKeys.JUNGLE, "Jungle");
    biomeMap.put(BiomeKeys.JAGGED_PEAKS, "Jagged Peaks");
    biomeMap.put(BiomeKeys.LUSH_CAVES, "Lush Caves");
    biomeMap.put(BiomeKeys.LUKEWARM_OCEAN, "Lukewarm Ocean");
    biomeMap.put(BiomeKeys.MEADOW, "Meadow");
    biomeMap.put(BiomeKeys.MANGROVE_SWAMP, "Mangrove Swamp");
    biomeMap.put(BiomeKeys.MUSHROOM_FIELDS, "Mushroom Fields");
    biomeMap.put(BiomeKeys.NETHER_WASTES, "Nether Wastes");
    biomeMap.put(BiomeKeys.OCEAN, "Ocean");
    biomeMap.put(BiomeKeys.OLD_GROWTH_PINE_TAIGA, "Old Growth Pine Taiga");
    biomeMap.put(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, "Old Growth Birch Forest");
    biomeMap.put(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, "Old Growth Spruce Taiga");
    biomeMap.put(BiomeKeys.PLAINS, "Plains");
    biomeMap.put(BiomeKeys.RIVER, "River");
    biomeMap.put(BiomeKeys.SWAMP, "Swamp");
    biomeMap.put(BiomeKeys.SAVANNA, "Savanna");
    biomeMap.put(BiomeKeys.SNOWY_PLAINS, "Snowy Plains");
    biomeMap.put(BiomeKeys.SNOWY_BEACH, "Snowy Beach");
    biomeMap.put(BiomeKeys.SNOWY_TAIGA, "Snowy Taiga");
    biomeMap.put(BiomeKeys.STONY_PEAKS, "Stony Peaks");
    biomeMap.put(BiomeKeys.STONY_SHORE, "Stony Shore");
    biomeMap.put(BiomeKeys.SNOWY_SLOPES, "Snowy Slopes");
    biomeMap.put(BiomeKeys.SPARSE_JUNGLE, "Sparse Jungle");
    biomeMap.put(BiomeKeys.SAVANNA_PLATEAU, "Sa Plateau");
    biomeMap.put(BiomeKeys.SOUL_SAND_VALLEY, "Soul Sand Valley");
    biomeMap.put(BiomeKeys.SUNFLOWER_PLAINS, "Sunflower Plains");
    biomeMap.put(BiomeKeys.SMALL_END_ISLANDS, "Small End Islands");
    biomeMap.put(BiomeKeys.TAIGA, "Taiga");
    biomeMap.put(BiomeKeys.THE_END, "The End");
    biomeMap.put(BiomeKeys.THE_VOID, "The Void");
    biomeMap.put(BiomeKeys.WARM_OCEAN, "Warm Ocean");
    biomeMap.put(BiomeKeys.WARPED_FOREST, "Warped Forest");
    biomeMap.put(BiomeKeys.WINDSWEPT_HILLS, "Windswept Hills");
    biomeMap.put(BiomeKeys.WOODED_BADLANDS, "Wooded Badlands");
    biomeMap.put(BiomeKeys.WINDSWEPT_FOREST, "Windswept Forest");
    biomeMap.put(BiomeKeys.WINDSWEPT_SAVANNA, "Windswept Savanna");
    biomeMap.put(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, "Windswept Gravelly Hills");

  }

  public static Map<RegistryKey<Biome>, String> getMap() {
    if (biomeMap.size() == 0) {
      // Build Map
      buildMap();
    }
    return biomeMap;
  }

}
