package com.toyice.toyiceapi.feature.toy.dto;

import com.toyice.toyiceapi.feature.toy.dto.ToyResponse.Get;
import com.toyice.toyiceapi.feature.toy.model.Toy;
import com.toyice.toyiceapi.feature.toy.model.UserVoice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ToyResponse {

  @ApiModel("ToyResponse.Get")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Get {

    @ApiModelProperty(value = "식별번호")
    private Long id;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "한 줄 요약")
    private String description;

    @ApiModelProperty(value = "노션 url")
    private String notionUrl;

    @ApiModelProperty(value = "대표 이미지")
    private String mainImage;

    @ApiModelProperty(value = "조회수")
    private Integer views;

    @ApiModelProperty(value = "좋아요")
    private Integer likes;

    @ApiModelProperty(value = "태그")
    private String tag;

    @ApiModelProperty(value = "유저보이스")
    private List<UserVoiceResponse.GetList> userVoiceList;

    public static Get of(Toy toy) {
      return Get.builder()
          .id(toy.getId())
          .title(toy.getTitle())
          .description(toy.getDescription())
          .notionUrl(toy.getNotionUrl())
          .mainImage(toy.getMainImageUrl())
          .views(toy.getViews())
          .likes(toy.getLikes())
          .tag(toy.getTag().getValue())
          .userVoiceList(UserVoiceResponse.GetList.of(toy.getUserVoiceList()))
          .build();
    }
    public static Get of(Toy toy, List<UserVoice> userVoiceList) {
      return Get.builder()
          .id(toy.getId())
          .title(toy.getTitle())
          .description(toy.getDescription())
          .notionUrl(toy.getNotionUrl())
          .mainImage(toy.getMainImageUrl())
          .views(toy.getViews())
          .likes(toy.getLikes())
          .tag(toy.getTag().getValue())
          .userVoiceList(UserVoiceResponse.GetList.of(userVoiceList))
          .build();
    }


  }

  @ApiModel("ToyResponse.GetList")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class GetList {

    @ApiModelProperty(value = "식별번호")
    private Long id;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "한 줄 요약")
    private String description;

    @ApiModelProperty(value = "대표 이미지")
    private String mainImage;

    @ApiModelProperty(value = "조회수")
    private Integer views;

    @ApiModelProperty(value = "좋아요")
    private Integer likes;

    @ApiModelProperty(value = "태그")
    private String tag;

    public static GetList of(Toy toy) {
      return GetList.builder()
          .id(toy.getId())
          .title(toy.getTitle())
          .description(toy.getDescription())
          .mainImage(toy.getMainImageUrl())
          .views(toy.getViews())
          .likes(toy.getLikes())
          .tag(toy.getTag().getValue())
          .build();
    }


  }


}
