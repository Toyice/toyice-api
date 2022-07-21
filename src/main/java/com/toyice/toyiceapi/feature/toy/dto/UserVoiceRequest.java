package com.toyice.toyiceapi.feature.toy.dto;

import com.toyice.toyiceapi.feature.toy.model.Toy;
import com.toyice.toyiceapi.feature.toy.model.UserVoice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserVoiceRequest {

  @ApiModel("UserVoiceRequest.Save")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @ApiModelProperty(value = "질문", required = true)
    private String question;

    public UserVoice toEntity(Toy toy){
      return UserVoice.builder()
          .question(this.question)
          .toy(toy)
          .build();
    }

  }


  @ApiModel("UserVoiceRequest.Update")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Update {

    @ApiModelProperty(value = "식별번호")
    private Long id;

    @ApiModelProperty(value = "질문", required = true)
    private String question;

    public UserVoice toEntity(Toy toy){
      return UserVoice.builder()
          .id(this.id)
          .question(this.question)
          .toy(toy)
          .build();
    }

  }

}
