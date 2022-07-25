package com.toyice.toyiceapi.feature.toy.controller;

import com.toyice.toyiceapi.feature.toy.dto.ReviewRequest;
import com.toyice.toyiceapi.feature.toy.dto.ReviewResponse;
import com.toyice.toyiceapi.feature.toy.dto.ToyRequest;
import com.toyice.toyiceapi.feature.toy.dto.ToyResponse;
import com.toyice.toyiceapi.feature.toy.service.ToyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/toy")
@Api(value = "Toy", tags = {"Toy"})
@RequiredArgsConstructor
public class ToyController {

  private final ToyService toyService;

  @ApiOperation(value = "토이 조회")
  @GetMapping("/{toyId}")
  public ToyResponse.Get get(@PathVariable Long toyId) {
    return toyService.get(toyId);
  }

  @ApiOperation(value = "토이 목록 조회")
  @GetMapping("/list")
  public List<ToyResponse.GetList> getList(@RequestParam(required = false) String type) {
    return toyService.getList(type);
  }

  @ApiOperation(value = "토이 생성")
  @PostMapping
  public ToyResponse.Get save(
      @RequestPart @ApiParam(examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = "{}"))) ToyRequest.Save request,
      @RequestPart(required = false) MultipartFile image) {
    return toyService.save(request, image);
  }

  @ApiOperation(value = "토이 수정")
  @PutMapping("/{toyId}")
  public ToyResponse.Get update(
      @PathVariable Long toyId,
      @RequestPart ToyRequest.Update request,
      @RequestPart(required = false) MultipartFile image) {
    return toyService.update(toyId, request, image);
  }

  @ApiOperation(value = "토이 삭제")
  @DeleteMapping("/{toyId}")
  public void delete(@PathVariable Long toyId) {
    toyService.delete(toyId);
  }

  @ApiOperation(value = "리뷰 생성")
  @PostMapping("/review")
  public ReviewResponse.Get saveReview(@RequestBody ReviewRequest.Save request) {
    return toyService.saveReview(request);
  }

  @ApiOperation(value = "토이 좋아요/좋아요 취소")
  @PutMapping("/{toyId}/like")
  public ToyResponse.Get update( @PathVariable Long toyId) {
    return toyService.like(toyId);
  }


}
