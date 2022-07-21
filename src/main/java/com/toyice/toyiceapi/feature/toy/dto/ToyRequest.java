package com.toyice.toyiceapi.feature.toy.dto;

import com.toyice.toyiceapi.feature.toy.model.Tag;
import com.toyice.toyiceapi.feature.toy.model.Toy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ToyRequest {

  @ApiModel("ToyResponse.Save")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @ApiModelProperty(value = "한 줄 요약", required = true)
    private String description;

    @ApiModelProperty(value = "노션 url", required = true)
    private String notionUrl;

    @ApiModelProperty(value = "태그", required = true)
    private String tag;

    @ApiModelProperty(value = "유저보이스")
    private List<UserVoiceRequest.Save> userVoiceList;

    public Toy toEntity() {
      return Toy.builder()
          .title(this.title)
          .description(this.description)
          .notionUrl(this.notionUrl)
          .mainImage(Toy.getMainImageDefaultPath())
          .tag(Tag.findByValue(tag))
          .likes(0)
          .views(0)
          .build();
    }


  }

  @ApiModel("ToyResponse.Update")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Update {

    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @ApiModelProperty(value = "한 줄 요약", required = true)
    private String description;

    @ApiModelProperty(value = "노션 url", required = true)
    private String notionUrl;

    @ApiModelProperty(value = "태그", required = true)
    private String tag;

    @ApiModelProperty(value = "유저보이스")
    private List<UserVoiceRequest.Save> userVoiceList;


  }



}
