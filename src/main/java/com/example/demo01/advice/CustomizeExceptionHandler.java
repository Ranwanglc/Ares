package com.example.demo01.advice;

import com.example.demo01.exception.CustomizeException;
import org.springframework.http.HttpStatus;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;

/**
 * controller 增强器
 * @author sam
 * @since 2017/7/17
 */

@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
        if(e instanceof CustomizeException)
        {
            model.addAttribute("message",e.getMessage());
        }
        else {

            String str="服务器脑壳进水了，您要不先歇一会";
            model.addAttribute("message",str);
        }
        return new ModelAndView("error");
    }



}
