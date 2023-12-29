package com.novel;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;

/**
 * CaculateFrameData
 */
@Getter
public class CaculateFrameData {
  @JSONField(name = "Index", ordinal = 1)
  private int index = 0;
  @JSONField(name = "Biome", ordinal = 2)
  private Map<String, Double> biome = new HashMap<String, Double>();
  @JSONField(name = "Time", ordinal = 3)
  private Map<String, Double> time = new HashMap<String, Double>();
  @JSONField(name = "Climate", ordinal = 4)
  private Map<String, Double> climate = new HashMap<String, Double>();
  @JSONField(name = "Temperature", ordinal = 5)
  private Map<String, Double> temperature = new HashMap<String, Double>();
  @JSONField(name = "Health", ordinal = 6)
  private Map<String, Double> health = new HashMap<String, Double>();
  @JSONField(name = "Hunger", ordinal = 7)
  private Map<String, Double> hunger = new HashMap<String, Double>();
  @JSONField(name = "Status", ordinal = 8)
  private Map<String, Double> status = new HashMap<String, Double>();
  @JSONField(name = "Motion", ordinal = 9)
  private Map<String, Double> motion = new HashMap<String, Double>();
  @JSONField(name = "Placing", ordinal = 10)
  private Map<String, Double> placing = new HashMap<String, Double>();

  private void updateMap(Map<String, Double> map, String key) {
    if (!map.containsKey(key)) {
      map.put(key, 1.0);
    } else {
      map.replace(key, map.get(key) + 1);
    }
  }

  private void noCalculateUpdateMap(Map<String, Double> map, String key) {
    map.put(key, 100.0);
  }

  private void caculateMap(Map<String, Double> map, int size) {
    for (Map.Entry<String, Double> entry : map.entrySet()) {
      map.replace(entry.getKey(), (entry.getValue() / size) * 100);
    }
  }

  private void clearMap(Map<String, Double> map) {
    map.clear();
  }

  private void resetAllMap() {
    clearMap(this.biome);
    clearMap(this.time);
    clearMap(this.climate);
    clearMap(this.temperature);
    clearMap(this.health);
    clearMap(this.hunger);
    clearMap(this.status);
    clearMap(this.motion);
    clearMap(this.placing);
  }

  public CaculateFrameData() {};

  public void updateAllMap(FrameData data) {
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


  public void caculateAllMap(int size) {
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
  }

  public void updateNoCalculate(int index, FrameData data) {
    resetAllMap();
    // biome
    noCalculateUpdateMap(biome, data.biome());
    // time
    noCalculateUpdateMap(time, data.time());
    // climate
    noCalculateUpdateMap(climate, data.climate());
    // temperature
    noCalculateUpdateMap(temperature, data.temp());
    // health
    noCalculateUpdateMap(health, data.hleath());
    // hunger
    noCalculateUpdateMap(hunger, data.hunger());
    // status (fire, water)
    //// fire
    noCalculateUpdateMap(status, data.fire());
    //// water
    noCalculateUpdateMap(status, data.wet());
    // motion (sneak, sprint)
    //// sneak
    noCalculateUpdateMap(motion, data.sneak());
    //// sprint
    noCalculateUpdateMap(motion, data.sprint());
    // placing (in lava, on rail, on gorund)
    //// lava
    noCalculateUpdateMap(placing, data.lava());
    //// rail
    noCalculateUpdateMap(placing, data.rail());
    //// ground
    noCalculateUpdateMap(placing, data.ground());

  }

  public String toJSONBeautyString() {
    return JSON.toJSONString(this, true);
  }

  public String toJSONString() {
    return JSON.toJSONString(this);
  }
}
