package com.toyice.toyiceapi.domain.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorResponse {

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Simple {
    @Schema(description = "메시지")
    private String message;
  }

}
