package com.boardproject.board;

import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardService {
    public BoardResponse.CreateDTO create(BoardReqeust.BoardFormDTO boardFormDTO, MultipartFile file) {
        return null;
    }
}
