package com.example.demo01.controller;


import com.example.demo01.dto.QuestionDTO;
import com.example.demo01.model.Question;
import com.example.demo01.model.User;
import com.example.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {



    @Autowired(required=false)
    private QuestionService questionService;


    @GetMapping("/publish/{id}")
    public String Republish(@PathVariable(name = "id") Integer id,
                            Model model){
        QuestionDTO question= questionService.getByid(id);
        model.addAttribute("tittle",question.getTittle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);
        return "publish";

    }

    @GetMapping("/publish")
    public String publish()
    {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("tittle") String tittle,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Integer id,
            HttpServletRequest request,
            Model model
            ) {
        model.addAttribute("tittle",tittle);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(tittle==null||tittle=="")
        {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description=="")
        {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag==null||tag=="")
        {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        User user=(User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTittle(tittle);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        question.setId(id);
        questionService.createOrUpdate(question);

        return "redirect:/";
    }


}
