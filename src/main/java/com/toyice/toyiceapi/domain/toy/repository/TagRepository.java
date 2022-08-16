package com.toyice.toyiceapi.domain.toy.repository;

import com.toyice.toyiceapi.domain.toy.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

  void deleteByToy_Id(Long toyId);

}
