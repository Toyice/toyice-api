package com.toyice.toyiceapi.domain.toy.service;

import com.toyice.toyiceapi.domain.toy.dto.ToyRequest;
import com.toyice.toyiceapi.domain.toy.dto.ToyResponse;
import com.toyice.toyiceapi.domain.toy.model.Tag;
import com.toyice.toyiceapi.domain.toy.model.ToyLike;
import com.toyice.toyiceapi.domain.toy.model.Type;
import com.toyice.toyiceapi.domain.toy.model.Toy;
import com.toyice.toyiceapi.domain.toy.repository.TagRepository;
import com.toyice.toyiceapi.domain.toy.repository.ToyRepository;
import com.toyice.toyiceapi.domain.common.utils.ImageUtils;
import java.io.File;
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
      return toyRepository.findAll(Sort.by(Direction.DESC, "createdDate")).stream()
          .map(ToyResponse.GetList::of)
          .collect(Collectors.toList());
    }
  }

  @SneakyThrows
  public ToyResponse.Save save(ToyRequest.Save request, MultipartFile image) {

    Toy toy = toyRepository.save(request.toEntity());
    File file = new File(ImageUtils.getImageDir(toy.getMainImage()));
    image.transferTo(file);
    toy.saveTagList(request.getTagList());

    return ToyResponse.Save.of(toy);
  }

  @SneakyThrows
  public ToyResponse.Update update(Long toyId, ToyRequest.Update request, MultipartFile image) {

    Toy toy = toyRepository.findById(toyId).get();
    tagRepository.deleteByToy_Id(toy.getId());
    toy.update(request);
    File file = new File(ImageUtils.getImageDir(toy.getMainImage()));
    image.transferTo(file);

    return ToyResponse.Update.of(toy);
  }

  public void delete(Long toyId) {
    toyRepository.deleteById(toyId);
  }


  public ToyResponse.Update like(Long toyId) {
    Toy toy = toyRepository.findById(toyId).get();
    ToyLike toyLike = ToyLike.builder()
        .toy(toy)
        .build();
    List<ToyLike> toyLikeList = toy.getToyLikeList();
    toyLikeList.add(toyLike);
    return ToyResponse.Update.of(toy);
  }
}
