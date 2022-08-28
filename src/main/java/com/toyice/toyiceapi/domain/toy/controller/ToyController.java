package com.toyice.toyiceapi.domain.toy.controller;

import com.toyice.toyiceapi.domain.toy.dto.ToyRequest;
import com.toyice.toyiceapi.domain.toy.dto.ToyResponse;
import com.toyice.toyiceapi.domain.toy.service.ToyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "toy", description = "toy api")
@RestController
@RequestMapping("/api/toy")
@RequiredArgsConstructor
public class ToyController {

  private final ToyService toyService;

  @Operation(summary = "토이 조회")
  @GetMapping("/{toyId}")
  public ToyResponse.Get get(@PathVariable Long toyId) {
    return toyService.get(toyId);
  }

  @Operation(summary = "토이 목록 조회")
  @GetMapping("/list")
  public List<ToyResponse.GetList> getList(@RequestParam(required = false) String type) {
    return toyService.getList(type);
  }

  @Operation(summary = "토이 생성")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ToyResponse.Save save(
      @RequestPart ToyRequest.Save request,
      @RequestPart MultipartFile image) {
    return toyService.save(request, image);
  }

  @Operation(summary = "토이 수정")
  @PutMapping("/{toyId}")
  public ToyResponse.Update update(
      @PathVariable Long toyId,
      @RequestPart ToyRequest.Update request,
      @RequestPart MultipartFile image) {
    return toyService.update(toyId, request, image);
  }

  @Operation(summary = "토이 삭제")
  @DeleteMapping("/{toyId}")
  public void delete(@PathVariable Long toyId) {
    toyService.delete(toyId);
  }

  @Operation(summary = "토이 좋아요/좋아요 취소")
  @PutMapping("/{toyId}/like")
  public ToyResponse.Update update(@PathVariable Long toyId) {
    return toyService.like(toyId);
  }


}
