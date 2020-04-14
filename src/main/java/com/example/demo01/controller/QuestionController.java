package com.example.demo01.controller;



import com.example.demo01.dto.CommentDTO;
import com.example.demo01.dto.QuestionDTO;
import com.example.demo01.enums.CommentTypeEnum;

import com.example.demo01.service.CommentService;
import com.example.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {


    @Autowired(required = false)
    private QuestionService questionService;

    @Autowired(required = false)
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Long id,
            Model model
    ){
        QuestionDTO questionDTO =questionService.getByid(id);
        List<QuestionDTO> relatedQuestion=questionService.selectRelated(questionDTO);
        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestion",relatedQuestion);

        return "question";
    }
}
