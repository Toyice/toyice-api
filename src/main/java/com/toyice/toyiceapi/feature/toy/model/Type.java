package com.toyice.toyiceapi.feature.toy.model;

import java.util.Arrays;

public enum Type {
  IDEA("아이디어"),
  DESIGN("디자인"),
  SERVICE_DEVELOP("서비스 개발");

  private final String value;

  Type(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static Type findByValue(String value) {
    return Arrays.stream(Type.values())
        .filter(type -> type.getValue().equals(value))
        .findAny()
        .orElse(null);
  }


}
