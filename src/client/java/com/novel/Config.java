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

  private Config() {
    pauseSecond = 5.0; // gap between two caculation
    savePath = "."; // where to save output files
                    // e.g: </home/novel2430/Documents >
                    // --> DO NOT put "/" at the end! <--
    debug = true; // need or not printing log
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
            && json.containsKey("Debug")) {
          this.pauseSecond = Double.parseDouble(json.get("PauseSecond").toString());
          this.savePath = (String) json.get("SavePath");
          this.debug = (Boolean) json.get("Debug");
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

}
