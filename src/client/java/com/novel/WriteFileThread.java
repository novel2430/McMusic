package com.novel;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * WriteFileThread
 */
public class WriteFileThread implements Runnable {
  private String fileName;
  private String filePath;
  private String fileContent;

  public void run() {
    // LoggerFactory.getLogger("outputdata").info("=== Write Thread Start ===");
    try {
      BufferedWriter write = new BufferedWriter(new FileWriter(filePath + "/" + fileName));
      write.write(fileContent);
      write.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // LoggerFactory.getLogger("outputdata").info("=== Write Thread End ===");
  }

  public WriteFileThread(String name, String path, String content) {
    this.fileName = name;
    this.filePath = path;
    this.fileContent = content;
  }

}
