package com.toyice.toyiceapi.domain.toy.dto;

import com.toyice.toyiceapi.domain.toy.model.Toy;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ToyResponse {

  @Schema(name="ToyResponse.Get")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Get {

    @Schema(description = "식별번호")
    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "한 줄 요약")
    private String description;

    @Schema(description = "노션 url")
    private String notionUrl;

    @Schema(description = "대표 이미지")
    private String mainImage;

    @Schema(description = "조회수")
    private Integer views;

    @Schema(description = "좋아요")
    private Integer likes;

    @Schema(description = "유형")
    private String type;

    @Schema(description = "태그")
    private List<String> tagList;

    public Get(Toy toy) {
      this.id = toy.getId();
      this.title = toy.getTitle();
      this.description = toy.getDescription();
      this.notionUrl = toy.getNotionUrl();
      this.mainImage = toy.getMainImageUrl();
      this.views = toy.getViews();
      this.likes = toy.getNumOfLike();
      this.type = toy.getType().getValue();
      this.tagList = toy.getTagStringList();
    }

  }

  @Schema(name = "ToyResponse.GetList")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class GetList {

    @Schema(description = "식별번호")
    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "한 줄 요약")
    private String description;

    @Schema(description = "대표 이미지")
    private String mainImage;

    @Schema(description = "조회수")
    private Integer views;

    @Schema(description = "좋아요")
    private Integer likes;

    @Schema(description = "유형")
    private String type;

    @Schema(description = "태그")
    private List<String> tagList;

    public GetList(Toy toy) {
      this.id = toy.getId();
      this.title = toy.getTitle();
      this.description = toy.getDescription();
      this.mainImage = toy.getMainImageUrl();
      this.views = toy.getViews();
      this.likes = toy.getNumOfLike();
      this.type = toy.getType().getValue();
      this.tagList = toy.getTagStringList();
    }

  }

  @Schema(name="ToyResponse.Save")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @Schema(description = "식별번호")
    private Long id;

    public Save(Toy toy) {
      this.id = toy.getId();
    }

  }

  @Schema(name="ToyResponse.Update")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Update {

    @Schema(description = "식별번호")
    private Long id;

    public Update(Toy toy) {
      this.id = toy.getId();
    }

  }


}
