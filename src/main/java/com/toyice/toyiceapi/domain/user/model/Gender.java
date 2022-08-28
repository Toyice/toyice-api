package com.toyice.toyiceapi.domain.user.model;

import com.toyice.toyiceapi.domain.common.advice.exception.EnumValueNotValidException;
import java.util.Arrays;

public enum Gender {
  MEN("남"),
  WOMEN("여");

  private final String value;

  Gender(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static Gender findByValue(String value) {
    Gender gender = Arrays.stream(
            Gender.values())
        .filter(g -> g.getValue().equals(value))
        .findAny()
        .orElse(null);

    if(gender == null){
      throw new EnumValueNotValidException(value);
    }
    return gender;
  }


}
