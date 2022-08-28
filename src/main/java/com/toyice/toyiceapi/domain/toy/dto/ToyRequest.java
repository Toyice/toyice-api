package com.toyice.toyiceapi.domain.toy.dto;

import com.toyice.toyiceapi.domain.toy.model.Type;
import com.toyice.toyiceapi.domain.toy.model.Toy;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ToyRequest {

  @Schema(name = "ToyRequest.Save")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Save {

    @Schema(description = "제목", required = true)
    private String title;

    @Schema(description = "한 줄 요약", required = true)
    private String description;

    @Schema(description = "노션 url", required = true)
    private String notionUrl;

    @Schema(description = "유형", required = true)
    private String type;

    @Schema(description = "태그")
    private List<String> tagList;

    public Toy toEntity() {
      return Toy.builder()
          .title(this.title)
          .description(this.description)
          .notionUrl(this.notionUrl)
          .type(Type.findByValue(this.type))
          .build();
    }


  }
  @Schema(name = "ToyRequest.Update")
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Update {

    @Schema(description = "제목", required = true)
    private String title;

    @Schema(description = "한 줄 요약", required = true)
    private String description;

    @Schema(description = "노션 url", required = true)
    private String notionUrl;

    @Schema(description = "유형", required = true)
    private String type;

    @Schema(description = "태그")
    private List<String> tagList;

  }



}
