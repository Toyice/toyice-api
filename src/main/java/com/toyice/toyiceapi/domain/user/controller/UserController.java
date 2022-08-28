package com.toyice.toyiceapi.domain.user.controller;

import com.toyice.toyiceapi.domain.user.dto.UserResponse;
import com.toyice.toyiceapi.domain.user.dto.UserResponse.Get;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "user", description = "user api")
@RequiredArgsConstructor
public class UserController {

  @GetMapping
  public UserResponse.Get get(){
    return new Get();
  }
}
