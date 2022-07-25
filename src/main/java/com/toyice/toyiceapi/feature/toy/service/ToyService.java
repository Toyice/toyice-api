package com.toyice.toyiceapi.feature.toy.service;

import com.toyice.toyiceapi.feature.toy.dto.ReviewRequest;
import com.toyice.toyiceapi.feature.toy.dto.ReviewResponse;
import com.toyice.toyiceapi.feature.toy.dto.ToyRequest;
import com.toyice.toyiceapi.feature.toy.dto.ToyResponse;
import com.toyice.toyiceapi.feature.toy.model.Tag;
import com.toyice.toyiceapi.feature.toy.model.ToyLike;
import com.toyice.toyiceapi.feature.toy.model.Type;
import com.toyice.toyiceapi.feature.toy.model.Review;
import com.toyice.toyiceapi.feature.toy.model.Toy;
import com.toyice.toyiceapi.feature.toy.repository.ReviewRepository;
import com.toyice.toyiceapi.feature.toy.repository.TagRepository;
import com.toyice.toyiceapi.feature.toy.repository.ToyRepository;
import com.toyice.toyiceapi.utils.ImageUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ToyService {

  private final ToyRepository toyRepository;

  private final ReviewRepository reviewRepository;

  private final TagRepository tagRepository;

  public ToyResponse.Get get(Long toyId) {
    Toy toy = toyRepository.findById(toyId).get();
    toy.updateViews();
    return ToyResponse.Get.of(toy);
  }

  @Transactional(readOnly = true)
  public List<ToyResponse.GetList> getList(String projectType) {
    if (StringUtils.hasText(projectType)) {
      return toyRepository.findByTypeOrderByCreatedDateDesc(Type.findByValue(projectType)).stream()
          .map(ToyResponse.GetList::of)
          .collect(Collectors.toList());
    } else {
      return toyRepository.findAll(Sort.by(Direction.DESC, "createdDate")).stream().map(ToyResponse.GetList::of)
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

    List<Tag> tagList = null;
    if (request.getTagList() != null) {
      tagList = new ArrayList<>();
      for (String value : request.getTagList()) {
        tagList.add(Tag.builder()
            .toy(toy)
            .value(value)
            .build());
      }
    }
    toy.setTagList(tagList);

    return ToyResponse.Get.of(toy);
  }

  @SneakyThrows
  public ToyResponse.Get update(Long toyId, ToyRequest.Update request, MultipartFile image) {

    Toy toy = toyRepository.findById(toyId).get();

    toy.setTitle(request.getTitle());
    toy.setDescription(request.getDescription());
    toy.setType(Type.findByValue(request.getType()));
    toy.setNotionUrl(request.getNotionUrl());

    if (image != null) {
      File file = new File(ImageUtils.getImageDir(toy.getMainImageIdPath()));
      image.transferTo(file);
      toy.setMainImage(toy.getMainImageIdPath());
    } else {
      toy.setMainImage(Toy.getMainImageDefaultPath());
    }

    toy.getTagList().removeAll(toy.getTagList());
    tagRepository.deleteByToy_Id(toyId);

    List<Tag> tagList = null;
    if (request.getTagList() != null) {
      tagList = new ArrayList<>();
      for (String value : request.getTagList()) {
        tagList.add(Tag.builder()
            .toy(toy)
            .value(value)
            .build());
      }
    }
    toy.setTagList(tagList);


    return ToyResponse.Get.of(toy);
  }

  public void delete(Long toyId) {
    toyRepository.deleteById(toyId);
  }

  public ReviewResponse.Get saveReview(ReviewRequest.Save request) {
    Toy toy = toyRepository.findById(request.getToyId()).get();
    Review review = reviewRepository.save(request.toEntity(toy));
    return ReviewResponse.Get.of(review);
  }

  public ToyResponse.Get like(Long toyId) {
    Toy toy = toyRepository.findById(toyId).get();
    ToyLike toyLike = ToyLike.builder()
        .toy(toy)
        .build();
    List<ToyLike> toyLikeList = toy.getToyLikeList();
    toyLikeList.add(toyLike);
    toy.setToyLikeList(toyLikeList);
    return ToyResponse.Get.of(toy);
  }
}
