package com.toyice.toyiceapi.domain.common.advice.exception;

public class EnumValueNotValidException extends IllegalStateException {
  public EnumValueNotValidException(String value){
    super("'"+value+"'는 올바르지 않은 유형 값입니다.");
  }

}
