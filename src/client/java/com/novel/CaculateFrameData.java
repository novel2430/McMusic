package com.novel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * CaculateFrameData
 */
@Getter
public class CaculateFrameData {
  private int index = 0;
  private Map<String, Double> biome = new HashMap<String, Double>();
  private Map<String, Double> time = new HashMap<String, Double>();
  private Map<String, Double> climate = new HashMap<String, Double>();
  private Map<String, Double> temperature = new HashMap<String, Double>();
  private Map<String, Double> health = new HashMap<String, Double>();
  private Map<String, Double> hunger = new HashMap<String, Double>();
  private Map<String, Double> status = new HashMap<String, Double>();
  private Map<String, Double> motion = new HashMap<String, Double>();
  private Map<String, Double> placing = new HashMap<String, Double>();

  private void updateMap(Map<String, Double> map, String key) {
    if (!map.containsKey(key)) {
      map.put(key, 1.0);
    } else {
      map.replace(key, map.get(key) + 1);
    }
  }

  private void caculateMap(Map<String, Double> map, int size) {
    for (Map.Entry<String, Double> entry : map.entrySet()) {
      map.replace(entry.getKey(), (entry.getValue() / size) * 100);
    }
  }

  private void caculate(List<FrameData> dataList) {
    int size = dataList.size();
    for (FrameData data : dataList) {
      // biome
      updateMap(biome, data.biome());
      // time
      updateMap(time, data.time());
      // climate
      updateMap(climate, data.climate());
      // temperature
      updateMap(temperature, data.temp());
      // health
      updateMap(health, data.hleath());
      // hunger
      updateMap(hunger, data.hunger());
      // status (fire, water)
      //// fire
      updateMap(status, data.fire());
      //// water
      updateMap(status, data.wet());
      // motion (sneak, sprint)
      //// sneak
      updateMap(motion, data.sneak());
      //// sprint
      updateMap(motion, data.sprint());
      // placing (in lava, on rail, on gorund)
      //// lava
      updateMap(placing, data.lava());
      //// rail
      updateMap(placing, data.rail());
      //// ground
      updateMap(placing, data.ground());
    }
    // biome
    caculateMap(biome, size);
    // time
    caculateMap(time, size);
    // climate
    caculateMap(climate, size);
    // temperature
    caculateMap(temperature, size);
    // health
    caculateMap(health, size);
    // hunger
    caculateMap(hunger, size);
    // status (fire, water)
    caculateMap(status, size);
    // motion (sneak, sprint)
    caculateMap(motion, size);
    // placing (in lava, on rail, on gorund)
    caculateMap(placing, size);
  };

  public CaculateFrameData() {};

  public CaculateFrameData(int index, List<FrameData> list) {
    this.index = index;
    caculate(list);
  }
}
