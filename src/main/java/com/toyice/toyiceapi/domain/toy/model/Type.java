package com.toyice.toyiceapi.domain.toy.model;

import com.toyice.toyiceapi.domain.common.advice.exception.TypeValueNotValidException;
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
    Type type = Arrays.stream(Type.values())
        .filter(t -> t.getValue().equals(value))
        .findAny()
        .orElse(null);

    if(type == null){
      throw new TypeValueNotValidException(value);
    }
    return type;
  }


}
