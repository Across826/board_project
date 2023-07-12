package com.boardproject.board.controller;

import com.boardproject._core.security.CustomUserDetails;
import com.boardproject.board.BoardService;
import com.boardproject.board.dto.BoardResponse;
import com.boardproject.user.UserResponse;
import com.boardproject.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/create")
    public String create(Model model,@AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("user", userDetails.getUser());
        return "boardForm";
    }

    @GetMapping("/details/{id}")
    public String read(Model model,@PathVariable("id") Long id,@AuthenticationPrincipal CustomUserDetails userDetails){
        BoardResponse.DetailsDTO boardDTO = boardService.getDetailsById(id);
        UserResponse.BoardWriterDTO writerDTO = userService.getBoardWriterById(boardDTO.getUserId());

        model.addAttribute("board", boardDTO);
        model.addAttribute("writer", writerDTO);
        model.addAttribute("user", userDetails.getUser());
        return "boardDetails";
    }
}
