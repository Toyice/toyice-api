package com.toyice.toyiceapi.domain.user.service;

import com.toyice.toyiceapi.domain.common.utils.ImageUtils;
import com.toyice.toyiceapi.domain.user.dto.UserRequest;
import com.toyice.toyiceapi.domain.user.dto.UserResponse;
import com.toyice.toyiceapi.domain.user.dto.UserResponse.Get;
import com.toyice.toyiceapi.domain.user.model.User;
import com.toyice.toyiceapi.domain.user.repository.UserRepository;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserResponse.Get get(Long userId){
    User user = userRepository.findById(userId).get();
    return new UserResponse.Get(user);
  }

//  public UserResponse.Save save(UserRequest.Save request, MultipartFile image){
//
//    User user = userRepository.save(request.toEntity());
//    File file = new File((ImageUtils.getImageDir()))
//  }

}
