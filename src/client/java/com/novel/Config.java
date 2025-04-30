package com.novel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Config
 */
public class Config {
  private static Config config;
  private Double pauseSecond;
  private String savePath;
  private Boolean debug;
  private Boolean calculate;
  private String serverUrl;
  private Boolean sendHttp;
  private String playerName;
  private Integer playerDetectSize;
  // Predict Next Biome param
  private Double directionAlpha;
  private Double distanceBeta;
  private Double distanceEpison;
  private Integer biomePredictRange;
  private Integer biomePredictSampleRate;
  private Integer biomePredictTickGap;

  private Config() {
    pauseSecond = 5.0; // gap between two caculation
    savePath = "."; // where to save output files
                    // e.g: </home/novel2430/Documents >
                    // --> DO NOT put "/" at the end! <--
    debug = true; // need or not printing log
    calculate = true; // need or not do calculation
    serverUrl = "http://127.0.0.1:44349"; // server url
    sendHttp = true;
    playerName = "novel2430"; // player name
    playerDetectSize = 10;
    //
    directionAlpha = 1.0;
    distanceBeta = 1.0;
    distanceEpison = 0.001;
    biomePredictRange = 40;
    biomePredictSampleRate = 2;
    biomePredictTickGap = 20;
    readConfigFile();
  }

  private void readConfigFile() {
    String configFilePath = "./outputDataConfig.json";
    try {
      File file = new File(configFilePath);
      if (file.exists()) {
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        InputStream in = new FileInputStream(file);
        in.read(fileContent);
        in.close();
        JSONObject json = JSON.parseObject(new String(fileContent));
        if(json.containsKey("PauseSecond")) {
          this.pauseSecond = Double.parseDouble(json.get("PauseSecond").toString());
        }
        if(json.containsKey("SavePath")) {
          this.savePath = (String) json.get("SavePath");
        }
        if(json.containsKey("Debug")) {
          this.debug = (Boolean) json.get("Debug");
        }
        if(json.containsKey("Calculate")) {
          this.calculate = (Boolean) json.get("Calculate");
        }
        if (json.containsKey("URL")) {
          this.serverUrl = (String) json.get("URL");
        }
        if(json.containsKey("PlayerName")) {
          this.playerName = (String) json.get("PlayerName");
        }
        if(json.containsKey("DetectSize")) {
          this.playerDetectSize = Integer.parseInt(json.get("DetectSize").toString());
        }
        if(json.containsKey("BiomePredictDirectionAlpha")) {
          this.directionAlpha = Double.parseDouble(json.get("BiomePredictDirectionAlpha").toString());
        }
        if(json.containsKey("BiomePredictDistanceBeta")) {
          this.distanceBeta = Double.parseDouble(json.get("BiomePredictDistanceBeta").toString());
        }
        if(json.containsKey("BiomePredictDetectSize")) {
          this.biomePredictRange = Integer.parseInt(json.get("BiomePredictDetectSize").toString());
        }
        if(json.containsKey("BiomePredictSampleRate")) {
          this.biomePredictSampleRate = Integer.parseInt(json.get("BiomePredictSampleRate").toString());
        }
        if(json.containsKey("BiomePredictTickInterval")) {
          this.biomePredictTickGap = Integer.parseInt(json.get("BiomePredictTickInterval").toString());
        }
      } else {
        Util.printWarnLog("ERROR! Config File Not exists!");
      }
    } catch (Exception e) {
      Util.printWarnLog("ERROR! Reading Config File!");
      e.printStackTrace();
    }
  }

  public static Config get() {
    if (config == null) {
      synchronized (Config.class) {
        if (config == null) {
          config = new Config();
        }
      }
    }
    return config;
  }

  public Double getPauseSec() {
    return this.pauseSecond;
  }

  public String getSavePath() {
    return this.savePath;
  }

  public Boolean getDebug() {
    return this.debug;
  }

  public Boolean getCalculate() {
    return this.calculate;
  }

  public String getServerUrl() {
    return this.serverUrl;
  }

  public Boolean getSendHttp() {
    return this.sendHttp;
  }

  public void setSendHttp(Boolean bool) {
    this.sendHttp = bool;
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public Integer getPlayerDetectSize() {
    return this.playerDetectSize;
  }

  public Double getDirectionAlpha() {
    return this.directionAlpha;
  }

  public Double getDistanceBeta() {
    return this.distanceBeta;
  }

  public Double getDistanceEpison() {
    return this.distanceEpison;
  }

  public Integer getBiomePredictRange() {
    return this.biomePredictRange;
  }

  public Integer getBiomePredictSampleRate() {
    return this.biomePredictSampleRate;
  }

  public Integer getBiomePredictTickGap() {
    return biomePredictTickGap;
  }

}
