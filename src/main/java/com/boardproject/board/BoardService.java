package com.boardproject.board;

import com.boardproject.File.FileService;
import com.boardproject._core.errors.exception.Exception404;
import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
import com.boardproject.user.User;
import com.boardproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Transactional
    public BoardResponse.CreateDTO create(BoardReqeust.BoardFormDTO boardFormDTO, MultipartFile file) {
        Board board = boardFormDTO.toEntity();

        if(!file.isEmpty()) {
            String savedPath = fileService.save(file);
            board.setThumbnail(savedPath);
        }

        Board boardPS = boardRepository.save(board);
        return new BoardResponse.CreateDTO(boardPS);
    }

    @Transactional
    public BoardResponse.DetailsDTO getDetailsById(Long boardId){
        Optional<Board> boardOP = boardRepository.findById(boardId);
        Board boardPS = boardOP.orElseThrow(() -> new Exception404("개시글을 찾을 수 없습니다."));

        Optional<User> userOP = userRepository.findById(boardPS.getUserId());
        User user = userOP.orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));

        return new BoardResponse.DetailsDTO(boardPS,user);
    }

    @Transactional
    public BoardResponse.UpdateDTO update(BoardReqeust.UpdateDTO updateDTO) {
        Optional<Board> boardOP = boardRepository.findById(updateDTO.getId());
        Board boardPS = boardOP.orElseThrow(() -> new Exception404("개시글을 찾을 수 없습니다."));

        updateDTO.setEntity(boardPS);

        return new BoardResponse.UpdateDTO(boardPS);
    }

    @Transactional
    public void delete(BoardReqeust.DeleteDTO deleteDTO) {
        boardRepository.deleteById(deleteDTO.getId());
        fileService.delete(deleteDTO.getThumbnail());
    }
}
