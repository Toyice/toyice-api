package com.toyice.toyiceapi.feature.toy.repository;

import com.toyice.toyiceapi.feature.toy.model.Tag;
import com.toyice.toyiceapi.feature.toy.model.Toy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
  List<Toy> findByTag(Tag tag);

}
