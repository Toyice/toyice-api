package com.toyice.toyiceapi.feature.toy.repository;

import com.toyice.toyiceapi.feature.toy.model.UserVoice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVoiceRepository extends JpaRepository<UserVoice, Long> {

  List<UserVoice> findByToyId(Long toyId);
}
