package com.toyice.toyiceapi.feature.toy.dto;

import com.toyice.toyiceapi.feature.toy.model.UserVoice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserVoiceResponse {

  @ApiModel("UserVoiceResponse.Save")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class GetList {

    @ApiModelProperty(value = "식별번호")
    private Long id;

    @ApiModelProperty(value = "질문")
    private String question;

    public static GetList of(UserVoice userVoice) {
      return GetList.builder()
          .id(userVoice.getId())
          .question(userVoice.getQuestion())
          .build();
    }
    public static List<GetList> of(List<UserVoice> userVoiceList){
      if(userVoiceList == null){
        return null;
      }
      return userVoiceList.stream().map(GetList::of).collect(Collectors.toList());
    }
  }


}
