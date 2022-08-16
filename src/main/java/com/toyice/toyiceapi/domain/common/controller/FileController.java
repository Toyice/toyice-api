package com.toyice.toyiceapi.domain.common.controller;

import com.toyice.toyiceapi.domain.common.utils.ImageUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
public class FileController {

  @SneakyThrows
  @GetMapping(
      value = "/images/{path}",
      produces = {MediaType.IMAGE_PNG_VALUE}
  )
  public ResponseEntity getImage(@PathVariable String path) {
    InputStream in = new FileInputStream(ImageUtils.getImageDir(path));
    byte[] bytes = IOUtils.toByteArray(in);
    in.close();
    return new ResponseEntity(bytes, HttpStatus.OK);
  }

}
