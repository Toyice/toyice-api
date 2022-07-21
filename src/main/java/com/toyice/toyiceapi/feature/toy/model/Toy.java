package com.toyice.toyiceapi.feature.toy.model;

import com.toyice.toyiceapi.utils.ImageUtils;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Toy {

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
  private Integer likes;

  @Column(nullable = false)
  private Tag tag;

  @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
  private List<UserVoice> userVoiceList;

  public static String getMainImageDefaultPath() {
    return "toy-default-main.jpeg";
  }

  public String getMainImageIdPath() {
    return "toy-" + this.id + "-main.jpeg";
  }

  public String getMainImageUrl() {
    return ImageUtils.getImageUrl(this.mainImage);
  }

  public void updateViews() {
    this.views = this.views+1;
  }

}
