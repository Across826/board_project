package com.boardproject.board;

import com.boardproject._core.errors.exception.Exception404;
import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

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

    @Transactional
    public BoardResponse.DetailsDTO getDetailsById(Long boardId){
        Optional<Board> boardOP = boardRepository.findById(boardId);
        Board board = boardOP.orElseThrow(() -> new Exception404("개시글을 찾을 수 없습니다."));
        return new BoardResponse.DetailsDTO(board);
    }

}
