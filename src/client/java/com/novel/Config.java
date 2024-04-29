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
        if (json.containsKey("PauseSecond") && json.containsKey("SavePath")
            && json.containsKey("Debug") && json.containsKey("Calculate") && json.containsKey("URL")
            && json.containsKey("PlayerName") && json.containsKey("DetectSize")) {
          this.pauseSecond = Double.parseDouble(json.get("PauseSecond").toString());
          this.savePath = (String) json.get("SavePath");
          this.debug = (Boolean) json.get("Debug");
          this.calculate = (Boolean) json.get("Calculate");
          this.serverUrl = (String) json.get("URL");
          this.playerName = (String) json.get("PlayerName");
          this.playerDetectSize = Integer.parseInt(json.get("DetectSize").toString());
        } else {
          Util.printWarnLog("ERROR! Config File Format Wrong!");
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

}
