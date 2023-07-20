package com.boardproject.board.dto;

import com.boardproject.board.Board;
import com.boardproject.comment.Comment;
import com.boardproject.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
        private List<Comment> commentList;

        public DetailsDTO(Board board, User user) {
            this.id = board.getId();
            this.writerId = user.getId();
            this.writerNickName = user.getNickName();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.thumbnail = board.getThumbnail();
            this.commentList = board.getCommentList();
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

    @Getter
    public static class ListDTO {
        private Long id;
        private String title;
        private String content;
        private String thumbnail;
        private String writerNickName;

        public ListDTO(Board board, User user) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.thumbnail = board.getThumbnail();
            this.writerNickName = user.getNickName();
        }
    }
}
