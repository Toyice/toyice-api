package com.toyice.toyiceapi.feature.toy.dto;
import com.toyice.toyiceapi.feature.toy.model.Review;
import com.toyice.toyiceapi.feature.toy.model.Toy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReviewRequest {

  @ApiModel("ReviewRequest.Save")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @ApiModelProperty(value = "내용", required = true)
    private String content;

    @ApiModelProperty(value = "토이 식별번호", required = true)
    private Long toyId;

    public Review toEntity(Toy toy) {
      return Review.builder()
          .content(this.content)
          .toy(toy)
          .build();
    }

  }


}
