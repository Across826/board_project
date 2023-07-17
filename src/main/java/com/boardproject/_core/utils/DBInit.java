package com.boardproject._core.utils;

import com.boardproject.board.Board;
import com.boardproject.board.BoardRepository;
import com.boardproject.user.User;
import com.boardproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
                    .roles("GENERAL")
                    .build();
            userRepository.save(ssar);

            User love = User.builder()
                    .username("love")
                    .password(passwordEncoder.encode("1234"))
                    .email("love@nate.com")
                    .nickName("러브")
                    .roles("GENERAL")
                    .build();
            userRepository.save(love);

            Board boardWithThumbnail = Board.builder()
                    .userId(1L)
                    .catagory("새싹회원")
                    .title("썸네일 있는 글")
                    .content("<p>썸네일이 있어요! " +
                            "<img src=\"/file/load?path=saved&name=590ea280-bb19-4dd6-9532-af9d0c13355b_HTTP-HTTPS-SSL-Certificate-Unsecure-scaled-e1661731905954.jpg\" style=\"width: 975.733px;\">" +
                            "<img src=\"/file/load?path=saved&name=9350b9af-6ea5-45da-970a-17e0a09abce3_BE 5기 QR.png\" style=\"width: 480px;\">" +
                            "</p>")
                    .thumbnail("9350b9af-6ea5-45da-970a-17e0a09abce3_BE 5기 QR.png")
                    .build();
            boardRepository.save(boardWithThumbnail);

            Board boardNoThumbnail = Board.builder()
                    .userId(1L)
                    .catagory("GENERAL")
                    .title("썸네일 없는 글")
                    .content("<p>썸네일이 없어요!</p>")
                    .build();
            boardRepository.save(boardNoThumbnail);
        };
    }
}