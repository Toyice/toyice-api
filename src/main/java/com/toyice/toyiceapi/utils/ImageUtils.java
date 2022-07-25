package com.toyice.toyiceapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUtils {

  private static String HOST;

  private static String PORT;

  private static String IMAGE_DIR;

  @Value("${toyice.public-ip}")
  public void setHost(String host) {
    HOST = host;
  }

  @Value("${server.port}")
  public void setPort(String port) {
    PORT = port;
  }

  @Value("${toyice.image-dir}")
  public void setImageDir(String imageDir) {
    IMAGE_DIR = imageDir;
  }

  public static String getImageDir() {
    return IMAGE_DIR;
  }

  public static String getImageDir(String path) {
    return IMAGE_DIR + path;
  }

  public static String getImageUrl(String path) {
    return "http://" + HOST + ":" + PORT + "/uploads/images/" + path;
  }

}
