package com.boardproject.board;

import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private com.boardproject._core.utils.FileHandler FileHandler;

    @Transactional
    public BoardResponse.CreateDTO create(BoardReqeust.BoardFormDTO boardFormDTO, MultipartFile file) {
        Board board = boardFormDTO.toEntity();

        if(!file.isEmpty()) {
            String savedPath = FileHandler.save(file);
            board.setThumbnailPath(savedPath);
        }

        Board boardPS = boardRepository.save(board);
        return new BoardResponse.CreateDTO(boardPS);
    }
}
