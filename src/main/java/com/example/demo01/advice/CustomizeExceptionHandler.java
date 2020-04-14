package com.example.demo01.advice;

import com.alibaba.fastjson.JSON;
import com.example.demo01.dto.ResultDTO;
import com.example.demo01.exception.CustomizeException;
import com.example.demo01.exception.CustomzieErrorCode;
import org.springframework.http.HttpStatus;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * controller 增强器
 * @author sam
 * @since 2017/7/17
 */

@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response)  {
        String contentType = request.getContentType();
        if("application/json".equals(contentType))
        {
            ResultDTO resultDTO;
            //return json
            if (e instanceof CustomizeException) {
                resultDTO= ResultDTO.errorOf((CustomizeException) e);
            } else {

                resultDTO= ResultDTO.errorOf(CustomzieErrorCode.SYS_ERROR);
            }
            try
            {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }
            catch(IOException ioe)
            {

            }
            return null;
        }
        else {
            //return "error"

            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomzieErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }



}
