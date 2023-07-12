package com.boardproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {
    @GetMapping("/create")
    public String create(Model model){
        // 유저 id, role 받아와야함 (시큐리티)
        model.addAttribute("userId",1);
        model.addAttribute("catagory","GENERAL");
        return "boardForm";
    }
}
