package com.toyice.toyiceapi.feature.toy.model;

import com.toyice.toyiceapi.feature.common.model.BaseTimeEntity;
import com.toyice.toyiceapi.utils.ImageUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

  @Column(nullable = false)
  private String mainImage;

  @Column(nullable = false)
  private Integer views;

  @Column(nullable = false)
  private Type type;

  @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
  private List<Review> reviewList;

  @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
  private List<ToyLike> toyLikeList;

  @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
  private List<Tag> tagList;

  public int getNumOfLike(){
    if(this.toyLikeList == null){
      return 0;
    }else{
      return this.toyLikeList.size();
    }
  }

  public List<String> getTagStringList(){
    if(this.tagList == null || this.getTagList().size() == 0){
      return null;
    }else{
      List<String> tagStringList = new ArrayList<>();
      this.tagList.stream().forEach(tag -> tagStringList.add(tag.getValue()));
      return tagStringList;
    }

  }

  public static String getMainImageDefaultPath() {
    return "toy-default-main.png";
  }

  public String getMainImageIdPath() {
    return "toy-" + this.id + "-main.png";
  }

  public String getMainImageUrl() {
    return ImageUtils.getImageUrl(this.mainImage);
  }

  public void updateViews() {
    this.views = this.views+1;
  }

}
