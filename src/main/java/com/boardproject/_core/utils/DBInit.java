package com.boardproject._core.utils;

import com.boardproject.board.Board;
import com.boardproject.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.boardproject.user.User;
import com.boardproject.user.UserRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.boardproject._core.utils.FileHandler.FILES_PATH;
import static com.boardproject._core.utils.FileHandler.SEPARATOR_CHAR;

@RequiredArgsConstructor
@Component
public class DBInit {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Bean
    CommandLineRunner initDB(){
        return args -> {
            User ssar = User.builder()
                    .username("ssar")
                    .password(passwordEncoder.encode("1234"))
                    .email("ssar@nate.com")
                    .nickName("보리")
                    .roles("새싹회원")
                    .build();
            userRepository.save(ssar);

            Path path = Paths.get(FILES_PATH+SEPARATOR_CHAR +"f5137072-fc18-4f96-b4be-135b03899ae9_BE 5기 QR.png");
            Board boardWithThumbnail = Board.builder()
                    .userId(1L)
                    .catagory("새싹회원")
                    .title("썸네일 있는 글")
                    .content("<p>하이하이</p>")
                    .thumbnail(path.toRealPath().toString())
                    .build();
            boardRepository.save(boardWithThumbnail);

            Board boardNoThumbnail = Board.builder()
                    .userId(1L)
                    .catagory("새싹회원")
                    .title("썸네일 없는 글")
                    .content("<p>썸네일이 없어요!</p>")
                    .build();
            boardRepository.save(boardNoThumbnail);
        };
    }
}