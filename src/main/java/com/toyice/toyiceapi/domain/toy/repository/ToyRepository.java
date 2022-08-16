package com.toyice.toyiceapi.domain.toy.repository;

import com.toyice.toyiceapi.domain.toy.model.Type;
import com.toyice.toyiceapi.domain.toy.model.Toy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Long> {
  List<Toy> findByTypeOrderByCreatedDateDesc(Type type);

}
