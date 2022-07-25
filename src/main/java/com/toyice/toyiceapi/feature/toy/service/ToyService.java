package com.toyice.toyiceapi.feature.toy.service;

import com.toyice.toyiceapi.feature.toy.dto.ReviewRequest;
import com.toyice.toyiceapi.feature.toy.dto.ReviewResponse;
import com.toyice.toyiceapi.feature.toy.dto.ToyRequest;
import com.toyice.toyiceapi.feature.toy.dto.ToyResponse;
import com.toyice.toyiceapi.feature.toy.dto.UserVoiceRequest;
import com.toyice.toyiceapi.feature.toy.model.Review;
import com.toyice.toyiceapi.feature.toy.model.Tag;
import com.toyice.toyiceapi.feature.toy.model.Toy;
import com.toyice.toyiceapi.feature.toy.model.UserVoice;
import com.toyice.toyiceapi.feature.toy.repository.ReviewRepository;
import com.toyice.toyiceapi.feature.toy.repository.ToyRepository;
import com.toyice.toyiceapi.feature.toy.repository.UserVoiceRepository;
import com.toyice.toyiceapi.utils.ImageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ToyService {

  private final ToyRepository toyRepository;

  private final UserVoiceRepository userVoiceRepository;

  private final ReviewRepository reviewRepository;

  public ToyResponse.Get get(Long toyId) {
    Toy toy = toyRepository.findById(toyId).get();
    toy.updateViews();
    return ToyResponse.Get.of(toy);
  }

  @Transactional(readOnly = true)
  public List<ToyResponse.GetList> getList(String tag) {
    if (StringUtils.hasText(tag)) {
      return toyRepository.findByTag(Tag.findByValue(tag)).stream().map(ToyResponse.GetList::of)
          .collect(Collectors.toList());
    } else {
      return toyRepository.findAll().stream().map(ToyResponse.GetList::of)
          .collect(Collectors.toList());
    }
  }

  @SneakyThrows
  public ToyResponse.Get save(ToyRequest.Save request, MultipartFile image) {
    Toy toy = toyRepository.save(request.toEntity());

    if (image != null) {
      File file = new File(ImageUtils.getImageDir(toy.getMainImageIdPath()));
      image.transferTo(file);
      toy.setMainImage(toy.getMainImageIdPath());
    }

    List<UserVoice> userVoiceList = new ArrayList<>();
    for (UserVoiceRequest.Save userVoice : request.getUserVoiceList()) {
      userVoiceList.add(userVoice.toEntity(toy));
    }
    userVoiceRepository.saveAll(userVoiceList);

    return ToyResponse.Get.of(toy, userVoiceList);
  }

  @SneakyThrows
  public ToyResponse.Get update(Long toyId, ToyRequest.Update request, MultipartFile image) {
    Toy toy = toyRepository.findById(toyId).get();

    toy.setTitle(request.getTitle());
    toy.setDescription(request.getDescription());
    toy.setTag(Tag.findByValue(request.getTag()));
    toy.setNotionUrl(request.getNotionUrl());

    if (image != null) {
      File file = new File(ImageUtils.getImageDir(toy.getMainImageIdPath()));
      image.transferTo(file);
      toy.setMainImage(toy.getMainImageIdPath());
    } else{
      toy.setMainImage(Toy.getMainImageDefaultPath());
    }

    List<UserVoice> userVoiceList = new ArrayList<>();
    for (UserVoiceRequest.Save userVoice : request.getUserVoiceList()) {
      userVoiceList.add(userVoice.toEntity(toy));
    }
    userVoiceRepository.saveAll(userVoiceList);

    return ToyResponse.Get.of(toy, userVoiceList);
  }

  public void delete(Long toyId) {
    toyRepository.deleteById(toyId);
  }

  public ReviewResponse.Get saveReview(ReviewRequest.Save request){
    Toy toy = toyRepository.findById(request.getToyId()).get();
    Review review = reviewRepository.save(request.toEntity(toy));
    return ReviewResponse.Get.of(review);
  }

  public ReviewResponse.Get updateReview(Long reviewId, ReviewRequest.Update request){
    Review review = reviewRepository.findById(reviewId).get();
    review.setContent(review.getContent());
    return ReviewResponse.Get.of(review);
  }

  public void deleteReview(Long reviewId){
    reviewRepository.deleteById(reviewId);
  }


}
