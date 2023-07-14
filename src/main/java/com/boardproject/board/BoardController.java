package com.boardproject.board;

import com.boardproject._core.security.CustomUserDetails;
import com.boardproject.board.dto.BoardReqeust;
import com.boardproject.board.dto.BoardResponse;
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

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", userDetails.getUser());
        return "board/boardCreateForm";
    }

    @PostMapping("/create")
    public String create(@Valid BoardReqeust.CreateFormDTO createFormDTO, MultipartFile file, Errors errors) {
        BoardResponse.CreateDTO boradDTO = boardService.create(createFormDTO, file);
        return "redirect:/board/" + boradDTO.getId();
    }

    @GetMapping("/{id}")
    public String read(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        BoardResponse.DetailsDTO boardDTO = boardService.getDetailsById(id);

        model.addAttribute("board", boardDTO);
        model.addAttribute("user", userDetails.getUser());
        return "board/boardDetails";
    }

    @PostMapping("/update/form")
    public String updateForm(Model model, @Valid BoardReqeust.UpdateFormDTO updateDTO, Errors errors) {
        model.addAttribute("board", updateDTO);
        return "board/boardUpdateForm";
    }

    @PostMapping("/update")
    public String update(@Valid BoardReqeust.UpdateDTO updateDTO, Errors errors) {
        BoardResponse.UpdateDTO update = boardService.update(updateDTO);
        return "redirect:/board/" + update.getId();
    }

    // @Todo 삭제 후 목록 보기로 이동 (목록 보기 url 연결 필요)
    @PostMapping("/delete")
    public String delete(@Valid BoardReqeust.DeleteDTO deleteDTO, Errors errors) {
        boardService.delete(deleteDTO);
        return "redirect:";
    }
}
