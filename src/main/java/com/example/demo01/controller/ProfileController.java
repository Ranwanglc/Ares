package com.example.demo01.controller;


import com.example.demo01.dto.PaginationDTO;
import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.User;
import com.example.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {



    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name="page",defaultValue = "1") Integer page,
            @RequestParam(name="size",defaultValue = "5") Integer size
    ){

        User user=(User)request.getSession().getAttribute("user");
        if(user ==null)
        {
            return "redirect:/";

        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }
        if("thesis".equals(action)){
            model.addAttribute("section","thesis");
            model.addAttribute("sectionName","我的论文");
        }
        if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }
        PaginationDTO pagination=questionService.List(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }

}
