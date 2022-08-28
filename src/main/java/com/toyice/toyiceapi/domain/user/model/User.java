package com.toyice.toyiceapi.domain.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column
  private String nickname;

  @Column
  private Gender gender;

  @Column
  private int age;

  @Column
  private String description;

  @Column
  private String job;

  @Column
  private String portfolioUrl;

  @Column
  private String profileImage;

  public void update(){

  }
}
