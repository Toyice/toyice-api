package com.toyice.toyiceapi.domain.toy.model;

import com.toyice.toyiceapi.domain.common.model.BaseTimeEntity;
import com.toyice.toyiceapi.domain.common.utils.ImageUtils;
import com.toyice.toyiceapi.domain.toy.dto.ToyRequest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Toy extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private String notionUrl;

  @Column
  private String mainImage;

  @Column(nullable = false)
  private Integer views;

  @Column
  private Type type;

  @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
  private List<ToyLike> toyLikeList;

  @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
  private List<Tag> tagList;

  @PrePersist
  public void perPersist() {
    this.views = 0;
  }

  @PostPersist
  public void postPersist() {
    this.mainImage = "toy-" + this.id + "-main.png";
  }

  public void update(ToyRequest.Update request) {
    this.title = request.getTitle();
    this.description = request.getDescription();
    this.type = Type.findByValue(request.getType());
    this.notionUrl = request.getNotionUrl();

    List<Tag> tagList = null;
    if (request.getTagList() != null) {
      tagList = new ArrayList<>();
      for (String value : request.getTagList()) {
        tagList.add(Tag.builder()
            .toy(this)
            .value(value)
            .build());
      }
    }
    this.tagList = tagList;

  }


  public void saveTagList(List<String> tagStringList) {
    List<Tag> tagList = null;
    if (tagStringList != null) {
      tagList = new ArrayList<>();
      for (String value : tagStringList) {
        tagList.add(Tag.builder()
            .toy(this)
            .value(value)
            .build());
      }
    }
    this.tagList = tagList;
  }

  public int getNumOfLike() {
    if (this.toyLikeList == null) {
      return 0;
    } else {
      return this.toyLikeList.size();
    }
  }

  public List<String> getTagStringList() {
    if (this.tagList == null || this.getTagList().size() == 0) {
      return null;
    } else {
      List<String> tagStringList = new ArrayList<>();
      this.tagList.stream().forEach(tag -> tagStringList.add(tag.getValue()));
      return tagStringList;
    }

  }

  public String getMainImageUrl() {
    return ImageUtils.getImageUrl(this.mainImage);
  }

  public void updateViews() {
    this.views = this.views + 1;
  }

}
