package com.novel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;
import net.minecraft.client.MinecraftClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Util
 */
public class Util {
  private static final AtomicBoolean isGameRunning = new AtomicBoolean(false);

  private static OkHttpClient httpClient = httpClientBuild();

  private static OkHttpClient httpClientBuild() {
    OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
    return httpClient;
  }

  private static Request buildRequest(String apiUrl, JSONObject json) {
    MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
    String url = Config.get().getServerUrl() + apiUrl;
    RequestBody requestBody = RequestBody.create(json.toJSONString(), mediaType);
    Request req = new Request.Builder().url(url).post(requestBody).build();
    return req;
  }

  private static Boolean doPostIsSuccess(Request req) throws IOException {
    Response resp = httpClient.newCall(req).execute();
    JSONObject respJson = (JSONObject) JSON.parse(resp.body().string());
    Integer state = Integer.parseInt(respJson.get("state").toString());
    if (state == 1)
      return true;
    return false;
  }

  public static void printLog(String text) {
    if (Config.get().getDebug())
      LoggerFactory.getLogger("ouputdata").info(text);
  }

  public static void printWarnLog(String text) {
    LoggerFactory.getLogger("ouputdata").warn(text);
  }

  public static void httpAddPlayer() {
    JSONObject json = new JSONObject();
    json.put("PlayerName", Config.get().getPlayerName());
    Request req = buildRequest("/api/input/add-player", json);
    Config.get().setSendHttp(true);
    try {
      if (!doPostIsSuccess(req)) {
        Config.get().setSendHttp(false);
        Util.printWarnLog("Player already running the game, no sending Http execution");
      }
    } catch (Exception e) {
      Config.get().setSendHttp(false);
      Util.printWarnLog("Http Failed");
    }
  }

  public static void httpAddPlayerData(String gameStart, CaculateFrameData data) {
    if (!Config.get().getSendHttp())
      return;
    JSONObject json = new JSONObject();
    json.put("PlayerName", Config.get().getPlayerName());
    json.put("GameStartTime", gameStart);
    json.put("Data", data);
    Request req = buildRequest("/api/input/add-player-data", json);
    try {
      if (!doPostIsSuccess(req)) {
        Util.printWarnLog("Http Failed, Player Not Exist");
      }
    } catch (Exception e) {
      Util.printWarnLog("Http Failed");
    }
  }

  public static void httpRemovePlayer() {
    if (!Config.get().getSendHttp())
      return;
    JSONObject json = new JSONObject();
    json.put("PlayerName", Config.get().getPlayerName());
    Request req = buildRequest("/api/input/remove-player", json);
    try {
      if (!doPostIsSuccess(req)) {
        Util.printWarnLog("Http Failed");
      }
    } catch (Exception e) {
      Config.get().setSendHttp(false);
      Util.printWarnLog("Http Failed");
    }
  }

  public static void updateGameStatus(MinecraftClient client) {
      isGameRunning.set(client.player != null && client.world != null);
  }

  public static boolean isGameRunning() {
      return isGameRunning.get();
  }

  public static Map<String, Double> softmaxScores(Map<String, Double> scores) {
    double sumExp = 0.0;
    for (double s : scores.values()) {
        sumExp += Math.exp(s);
    }
    Map<String, Double> result = new HashMap<String, Double>();
    for (Map.Entry<String, Double> entry : scores.entrySet()) {
        result.put(entry.getKey(), Math.exp(entry.getValue()) / sumExp);
    }
    return result;
  }


}
