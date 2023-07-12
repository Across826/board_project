package com.boardproject.board.dto;

import com.boardproject.board.Board;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoardReqeust {

    @Getter
    @Setter
    public static class BoardFormDTO{
        @NotNull
        private Long userId;

        @NotEmpty
        private String catagory;

        @NotEmpty
        @Length(max=100)
        private String title;

        @NotEmpty
        @Length(max=1000)
        private String content;

        public Board toEntity(){
            return Board.builder()
                    .userId(userId)
                    .catagory(catagory)
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}