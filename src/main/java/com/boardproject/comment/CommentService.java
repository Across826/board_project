package com.boardproject.comment;

import com.boardproject.comment.dto.CommentRequest;
import com.boardproject.comment.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(CommentRequest.CreateDTO createDTO) {
        Comment comment = createDTO.toEntity();
        commentRepository.save(comment);
    }
}
