package com.boardproject.comment;

import com.boardproject.comment.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    @RequestMapping("/create")
    public String create(@Valid CommentRequest.CreateDTO createDTO, Error error){
        commentService.create(createDTO);
        return "redirect:/board/" + createDTO.getBoardId();
    }
}
