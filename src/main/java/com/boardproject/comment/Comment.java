package com.boardproject.comment;

import com.boardproject.board.Board;
import com.boardproject.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String content;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int depth ; // 0:댓글, 1:대댓글

    @Column(name = "group_id")
    private Long groupId;   // 대댓글인 경우 부모 댓글의 인덱스

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="created_at",nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Comment(Long id, String content, int depth, Long groupId, Board board, User user, Timestamp createdAt) {
        this.id = id;
        this.content = content;
        this.depth = depth;
        this.groupId = groupId;
        this.board = board;
        this.user = user;
        this.createdAt = createdAt;
    }
}
