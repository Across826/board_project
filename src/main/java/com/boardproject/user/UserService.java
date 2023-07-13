package com.boardproject.user;

import com.boardproject._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse.BoardWriterDTO getBoardWriterById(Long userId) {
        Optional<User> userOP = userRepository.findById(userId);
        User user = userOP.orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));
        return new UserResponse.BoardWriterDTO(user);
    }
}
