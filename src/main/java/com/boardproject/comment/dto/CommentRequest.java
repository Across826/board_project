package com.boardproject.comment.dto;

import com.boardproject.board.Board;
import com.boardproject.comment.Comment;
import com.boardproject.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @Getter
    @Setter
    public static class CreateDTO {
        @NotNull
        private Long boardId;

        @NotNull
        private Long userId;

        private int depth;

        private Long groupId;

        @NotEmpty
        @Length(max=50)
        private String content;

        private Board board;

        private User user;

        public Comment toEntity(){
            return Comment.builder()
                    .board(board)
                    .user(user)
                    .depth(depth)
                    .groupId(groupId)
                    .content(content)
                    .build();
        }
    }
}
