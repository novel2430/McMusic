package com.novel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * FrameDataQueueMap
 */
public class FrameDataQueueMap {
  private static ConcurrentMap<Integer, List<FrameData>> frameDataQueueMap;

  private static void initMap() {
    if (frameDataQueueMap == null) {
      frameDataQueueMap = new ConcurrentHashMap<Integer, List<FrameData>>();
    }
  }

  public static List<FrameData> getQueue(int index) {
    initMap();
    return frameDataQueueMap.get(index);
  }

  public static void addData(int index, FrameData data) {
    initMap();
    if (frameDataQueueMap.containsKey(index)) {
      frameDataQueueMap.get(index).add(data);
    } else {
      List<FrameData> list = new ArrayList<FrameData>();
      list.add(data);
      frameDataQueueMap.put(index, list);
    }
  }
}
