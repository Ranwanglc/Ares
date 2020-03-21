package com.example.demo01.controller;


import com.example.demo01.dto.QuestionDTO;
import com.example.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {


    @Autowired(required = false)
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") int id,
            Model model
    ){
        QuestionDTO questionDTO =questionService.getByid(id);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
