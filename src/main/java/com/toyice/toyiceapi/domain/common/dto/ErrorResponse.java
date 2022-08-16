package com.toyice.toyiceapi.domain.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorResponse {

  @ApiModel("ErrorResponse.Simple")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Simple {
    @ApiModelProperty(value = "메시지")
    private String message;
  }

}
