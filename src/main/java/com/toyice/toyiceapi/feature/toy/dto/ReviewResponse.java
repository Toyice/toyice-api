package com.toyice.toyiceapi.feature.toy.dto;

import com.toyice.toyiceapi.feature.toy.model.Review;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReviewResponse {

  @ApiModel("ReviewResponse.Get")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Get {

    @ApiModelProperty(value = "식별번호")
    private Long id;

    @ApiModelProperty(value = "내용")
    private String content;

    public static Get of(Review review) {
      return Get.builder()
          .id(review.getId())
          .content(review.getContent())
          .build();
    }

  }

  @ApiModel("ReviewResponse.GetList")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class GetList {

    @ApiModelProperty(value = "식별번호")
    private Long id;

    @ApiModelProperty(value = "내용")
    private String content;


    public static GetList of(Review review) {
      return GetList.builder()
          .id(review.getId())
          .content(review.getContent())
          .build();
    }

    public static List<GetList> of(List<Review> reviewList) {
      if (reviewList == null) {
        return null;
      }
      return reviewList.stream().map(GetList::of).collect(Collectors.toList());
    }

  }

}
