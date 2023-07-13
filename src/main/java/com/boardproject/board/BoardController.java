package com.boardproject.board;

import com.boardproject._core.security.CustomUserDetails;
import com.boardproject.board.BoardService;
import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
import com.boardproject.user.UserResponse;
import com.boardproject.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", userDetails.getUser());
        return "boardForm";
    }

    @PostMapping("/create")
    public String create(@Valid BoardReqeust.BoardFormDTO boardFormDTO, MultipartFile file, Errors errors) {
        BoardResponse.CreateDTO boradDTO = boardService.create(boardFormDTO, file);
        return "redirect:/board/" + boradDTO.getId();
    }

    @GetMapping("/{id}")
    public String read(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        BoardResponse.DetailsDTO boardDTO = boardService.getDetailsById(id);

        model.addAttribute("board", boardDTO);
        model.addAttribute("user", userDetails.getUser());
        return "boardDetails";
    }
}
