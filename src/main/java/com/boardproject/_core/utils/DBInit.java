package com.boardproject._core.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.boardproject.user.User;
import com.boardproject.user.UserRepository;

@RequiredArgsConstructor
@Component
public class DBInit {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDB(UserRepository userRepository){
        return args -> {
            User ssar = User.builder()
                    .username("ssar")
                    .password(passwordEncoder.encode("1234"))
                    .email("ssar@nate.com")
                    .nickName("보리")
                    .roles("새싹회원")
                    .build();
            userRepository.save(ssar);
        };
    }
}