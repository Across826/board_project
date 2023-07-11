package com.boardproject.board;

import com.boardproject.util.Role;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@DynamicInsert
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "thumbnail")
    private int isThumbnail; // 0:썸네일 없음, 1:썸네일 있음

    @Column(name = "file_id ")
    private Long fileId ;

    @ColumnDefault("'GENERAL'")
    @Enumerated(EnumType.STRING)
    private Role catagory; // 새싹:GENERAL, 우수:VIP

    @Column(name="hide_flag")
    @ColumnDefault("0")
    private int isHide; // 0:보임, 1:숨김/삭제

    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
