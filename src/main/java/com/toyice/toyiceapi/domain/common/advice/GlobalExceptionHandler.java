package com.toyice.toyiceapi.domain.common.advice;

import com.toyice.toyiceapi.domain.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse.Simple defaultException(Exception e){
    return ErrorResponse.Simple.builder()
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse.Simple illegalStateException(IllegalStateException e){
    return ErrorResponse.Simple.builder()
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse.Simple runTimeException(RuntimeException e){
    return ErrorResponse.Simple.builder()
        .message(e.getMessage())
        .build();
  }

}
