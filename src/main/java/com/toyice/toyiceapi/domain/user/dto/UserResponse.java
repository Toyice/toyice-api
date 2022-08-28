package com.toyice.toyiceapi.domain.user.dto;

import com.toyice.toyiceapi.domain.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {

  @Schema(name="UserResponse.Get")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Get {

    @Schema(description = "식별번호")
    private Long id;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "성별")
    private String gender;

    @Schema(description = "나이")
    private int age;

    @Schema(description = "한줄소개")
    private String description;

    @Schema(description = "직업")
    private String job;

    @Schema(description = "포트폴리오 URL")
    private String portfolioUrl;

    @Schema(description = "프로필이미지")
    private String profileImage;

    public Get(User user) {
      this.id = user.getId();
      this.nickname = user.getNickname();
      this.gender = user.getGender().getValue();
      this.age = user.getAge();
      this.job = user.getJob();
      this.description = user.getDescription();
      this.portfolioUrl = user.getPortfolioUrl();
      this.profileImage = user.getProfileImage();
    }

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @Schema(description = "식별번호")
    private Long id;

    public Save(User user) {
      this.id = user.getId();
    }
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Update {

    @Schema(description = "식별번호")
    private Long id;

    public Update(User user) {
      this.id = user.getId();
    }
  }


}
