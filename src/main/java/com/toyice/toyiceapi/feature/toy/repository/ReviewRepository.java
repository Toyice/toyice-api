package com.toyice.toyiceapi.feature.toy.repository;

import com.toyice.toyiceapi.feature.toy.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
