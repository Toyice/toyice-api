package com.toyice.toyiceapi.domain.common.utils;

import java.io.File;
import java.io.IOException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

  public static void saveImage(MultipartFile image, String fileName) throws IOException {
    File file = new File(ImageUtils.getImageDir(fileName));
    image.transferTo(file);
  }

}
