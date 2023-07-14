package com.boardproject.board.dto;

import com.boardproject.board.Board;
import com.boardproject.user.User;
import lombok.Getter;

public class BoardResponse {

    @Getter
    public static class CreateDTO{
        private Long id;
        private Long userId;
        private String catagory;
        private String title;

        public CreateDTO(Board board) {
            this.id = board.getId();
            this.userId = board.getUserId();
            this.catagory = board.getCatagory();
            this.title = board.getTitle();
        }
    }

    @Getter
    public static class DetailsDTO{
        private Long id;
        private Long writerId;
        private String writerNickName;
        private String title;

        private String content;

        private String thumbnail;

        public DetailsDTO(Board board, User user) {
            this.id = board.getId();
            this.writerId = user.getId();
            this.writerNickName = user.getNickName();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.thumbnail = board.getThumbnail();
        }
    }

    @Getter
    public static class UpdateDTO {
        private Long id;
        private Long userId;
        private String catagory;
        private String title;

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.userId = board.getUserId();
            this.catagory = board.getCatagory();
            this.title = board.getTitle();
        }
    }
}
