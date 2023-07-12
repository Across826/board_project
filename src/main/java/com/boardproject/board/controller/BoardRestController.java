package com.boardproject.board.controller;

import com.boardproject._core.utils.ApiUtils;
import com.boardproject.board.BoardService;
import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardRestController {
    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid BoardReqeust.BoardFormDTO boardFormDTO, MultipartFile file, Errors errors){
        BoardResponse.CreateDTO responseBody = boardService.create(boardFormDTO,file);

        return ResponseEntity.ok(ApiUtils.success(responseBody));
    }
}
