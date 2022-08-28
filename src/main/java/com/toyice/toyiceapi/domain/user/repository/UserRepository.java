package com.toyice.toyiceapi.domain.user.repository;

import com.toyice.toyiceapi.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
