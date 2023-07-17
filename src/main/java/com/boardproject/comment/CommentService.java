package com.boardproject.comment;

import com.boardproject._core.errors.exception.Exception404;
import com.boardproject.board.Board;
import com.boardproject.board.BoardRepository;
import com.boardproject.comment.dto.CommentRequest;
import com.boardproject.user.User;
import com.boardproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(CommentRequest.CreateDTO createDTO) {
        Board boardPs = boardRepository.findById(createDTO.getBoardId())
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        User userPS = userRepository.findById(createDTO.getUserId())
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));

        createDTO.setBoard(boardPs);
        createDTO.setUser(userPS);

        Comment comment = createDTO.toEntity();
        commentRepository.save(comment);
    }

    @Transactional
    public void delete(CommentRequest.DeleteDTO deleteDTO) {
        commentRepository.deleteById(deleteDTO.getId());
    }
}
