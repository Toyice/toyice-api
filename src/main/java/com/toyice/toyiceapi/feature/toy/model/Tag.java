package com.toyice.toyiceapi.feature.toy.model;

import java.util.Arrays;

public enum Tag {
  RED("아이디어"),
  YELLOW("시각화"),
  BLUE("서비스 개발");

  private final String value;

  Tag(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static Tag findByValue(String value) {
    return Arrays.stream(Tag.values())
        .filter(tag -> tag.getValue().equals(value))
        .findAny()
        .orElse(null);
  }


}
