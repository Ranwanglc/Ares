package com.example.demo01.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
public class ToDoController {



    @GetMapping("/")
    public String Todo(HttpServletRequest request,
                       Model model){

        return "/Todo";
    }

}
