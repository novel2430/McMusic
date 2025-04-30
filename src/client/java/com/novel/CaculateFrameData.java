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
  @JSONField(name = "Monster", ordinal = 11)
  private Map<String, Double> monster = new HashMap<String, Double>();
  @JSONField(name = "Attacked", ordinal = 12)
  private Map<String, Double> attacker = new HashMap<String, Double>();
  @JSONField(name = "BiomePredict", ordinal = 13)
  private Map<String, Double> biomePredict = new HashMap<String, Double>();

  private void calculateFromBuffer(Map<String, Integer> bufferMap, Map<String, Double> dataMap,
      int pushCount) {
    for (Map.Entry<String, Integer> entry : bufferMap.entrySet()) {
      dataMap.put(entry.getKey(), (entry.getValue() / (double) pushCount) * 100);
    }
  }

  public CaculateFrameData() {};

  public CaculateFrameData(Integer index) {
    this.index = index;
  };

  public void buildDataFromBuffer() {
    int pushCount = FrameDataBuffer.getPushCount();
    calculateFromBuffer(FrameDataBuffer.getBiomeMap(), this.biome, pushCount);
    calculateFromBuffer(FrameDataBuffer.getTimeMap(), this.time, pushCount);
    calculateFromBuffer(FrameDataBuffer.getClimateMap(), this.climate, pushCount);
    calculateFromBuffer(FrameDataBuffer.getTemperatureMap(), this.temperature, pushCount);
    calculateFromBuffer(FrameDataBuffer.getHealthMap(), this.health, pushCount);
    calculateFromBuffer(FrameDataBuffer.getHungerMap(), this.hunger, pushCount);
    calculateFromBuffer(FrameDataBuffer.getStatusMap(), this.status, pushCount);
    calculateFromBuffer(FrameDataBuffer.getMotionMap(), this.motion, pushCount);
    calculateFromBuffer(FrameDataBuffer.getPlacingMap(), this.placing, pushCount);
    calculateFromBuffer(FrameDataBuffer.getAttackMap(), this.attacker, pushCount);
    // Monster
    Map<String, Integer> monsterMap = FrameDataBuffer.getMonsterMap();
    for (Map.Entry<String, Integer> entry : monsterMap.entrySet()) {
      monster.put(entry.getKey(), (double) entry.getValue() / pushCount);
    }
    // Biome Predict
    for (Map.Entry<String, Double> entry : FrameDataBuffer.getBiomePredictMap().entrySet()) {
      biomePredict.put(entry.getKey(), (entry.getValue() / (double) pushCount) * 100);
    }
  }

  public String toJSONBeautyString() {
    return JSON.toJSONString(this, true);
  }

  public String toJSONString() {
    return JSON.toJSONString(this);
  }
}
