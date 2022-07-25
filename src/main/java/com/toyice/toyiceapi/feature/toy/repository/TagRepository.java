package com.toyice.toyiceapi.feature.toy.repository;

import com.toyice.toyiceapi.feature.toy.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  void deleteByToy_Id(Long toyId);

}
