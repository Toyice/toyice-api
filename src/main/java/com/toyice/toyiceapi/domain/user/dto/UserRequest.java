package com.toyice.toyiceapi.domain.user.dto;

import com.toyice.toyiceapi.domain.user.model.Gender;
import com.toyice.toyiceapi.domain.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserRequest {

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @Schema(description = "닉네임", required = true)
    private String nickname;

    @Schema(description = "성별", required = true)
    private String gender;

    @Schema(description = "나이", required = true)
    private int age;

    @Schema(description = "한줄소개", required = true)
    private String description;

    @Schema(description = "직업")
    private String job;

    @Schema(description = "포트폴리오 URL")
    private String portfolioUrl;

    public User toEntity() {
      return User.builder()
          .nickname(this.nickname)
          .gender(Gender.findByValue(this.gender))
          .age(this.age)
          .description(this.description)
          .job(this.job)
          .portfolioUrl(this.portfolioUrl)
          .build();
    }

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Update {

    @Schema(description = "닉네임", required = true)
    private String nickname;

    @Schema(description = "성별", required = true)
    private String gender;

    @Schema(description = "나이", required = true)
    private int age;

    @Schema(description = "한줄소개", required = true)
    private String description;

    @Schema(description = "직업")
    private String job;

    @Schema(description = "포트폴리오 URL")
    private String portfolioUrl;

  }
}
