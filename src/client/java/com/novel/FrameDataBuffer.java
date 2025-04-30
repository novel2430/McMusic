package com.novel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FrameDataBuffer {
  private static final AtomicInteger pushCount = new AtomicInteger(0);

  private static final Map<String, Integer> biome = new HashMap<String, Integer>();
  private static final Map<String, Integer> time = new HashMap<String, Integer>();
  private static final Map<String, Integer> climate = new HashMap<String, Integer>();
  private static final Map<String, Integer> temperature = new HashMap<String, Integer>();
  private static final Map<String, Integer> health = new HashMap<String, Integer>();
  private static final Map<String, Integer> hunger = new HashMap<String, Integer>();
  private static final Map<String, Integer> status = new HashMap<String, Integer>();
  private static final Map<String, Integer> motion = new HashMap<String, Integer>();
  private static final Map<String, Integer> placing = new HashMap<String, Integer>();
  private static final Map<String, Integer> monster = new HashMap<String, Integer>();
  private static final Map<String, Integer> attacker = new HashMap<String, Integer>();
  private static final Map<String, Double> biomePredict = new HashMap<String, Double>();

  public static Map<String, Double> getBiomePredictMap(){
    return biomePredict;
  }

  public static Map<String, Integer> getBiomeMap(){
    return biome;
  }

  public static Map<String, Integer> getTimeMap(){
    return time;
  }

  public static Map<String, Integer> getClimateMap(){
    return climate;
  }

  public static Map<String, Integer> getTemperatureMap(){
    return temperature;
  }

  public static Map<String, Integer> getHealthMap(){
    return health;
  }

  public static Map<String, Integer> getHungerMap(){
    return hunger;
  }

  public static Map<String, Integer> getStatusMap(){
    return status;
  }

  public static Map<String, Integer> getMotionMap(){
    return motion;
  }

  public static Map<String, Integer> getPlacingMap(){
    return placing;
  }

  public static Map<String, Integer> getMonsterMap(){
    return monster;
  }

  public static Map<String, Integer> getAttackMap(){
    return attacker;
  }

  public static void push(FrameData snap) {
    // update Map Data
    biome.merge(snap.biome(), 1, Integer::sum);
    time.merge(snap.time(), 1, Integer::sum);
    climate.merge(snap.climate(), 1, Integer::sum);
    temperature.merge(snap.temp(), 1, Integer::sum);
    health.merge(snap.hleath(), 1, Integer::sum);
    hunger.merge(snap.hunger(), 1, Integer::sum);
    status.merge(snap.fire(), 1, Integer::sum);
    status.merge(snap.wet(), 1, Integer::sum);
    motion.merge(snap.sneak(), 1, Integer::sum);
    motion.merge(snap.sprint(), 1, Integer::sum);
    placing.merge(snap.lava(), 1, Integer::sum);
    placing.merge(snap.rail(), 1, Integer::sum);
    placing.merge(snap.ground(), 1, Integer::sum);
    attacker.merge(snap.attacker(), 1,Integer::sum);
    if (snap.mosterRec() != null) {
      monster.merge("sum", snap.mosterRec().hostile(), Integer::sum);
      monster.merge("zombie", snap.mosterRec().zombie(), Integer::sum);
      monster.merge("zoglin", snap.mosterRec().zoglin(), Integer::sum);
      monster.merge("vex", snap.mosterRec().vex(), Integer::sum);
      monster.merge("blaze", snap.mosterRec().blaze(), Integer::sum);
      monster.merge("giant", snap.mosterRec().giant(), Integer::sum);
      monster.merge("patrik", snap.mosterRec().patrik(), Integer::sum);
      monster.merge("piglin", snap.mosterRec().piglin(), Integer::sum);
      monster.merge("spider", snap.mosterRec().spider(), Integer::sum);
      monster.merge("warden", snap.mosterRec().warden(), Integer::sum);
      monster.merge("wither", snap.mosterRec().wither(), Integer::sum);
      monster.merge("creeper", snap.mosterRec().creeper(), Integer::sum);
      monster.merge("skelton", snap.mosterRec().skelton(), Integer::sum);
      monster.merge("enderman", snap.mosterRec().enderman(), Integer::sum);
      monster.merge("guardian", snap.mosterRec().guardian(), Integer::sum);
      monster.merge("endermite", snap.mosterRec().endermite(), Integer::sum);
      monster.merge("silverfish", snap.mosterRec().silverfish(), Integer::sum);
    }
    for(Map.Entry<String, Double> entry : snap.biomePredict().entrySet()) {
      biomePredict.merge(entry.getKey(), entry.getValue(), Double::sum);
    }
    pushCount.incrementAndGet(); 
  }

  public static void clear() {
    biome.clear();
    time.clear();
    climate.clear();
    temperature.clear();
    health.clear();
    hunger.clear();
    status.clear();
    motion.clear();
    placing.clear();
    monster.clear();
    attacker.clear();
    pushCount.getAndSet(0);
    biomePredict.clear();
  }

  public static Integer getPushCount() {
    return pushCount.get();
  }

};
