package com.example.demo01.controller;


import com.example.demo01.dto.CommentCreateDTO;
import com.example.demo01.dto.CommentDTO;
import com.example.demo01.dto.ResultDTO;
import com.example.demo01.enums.CommentTypeEnum;
import com.example.demo01.exception.CustomzieErrorCode;
import com.example.demo01.model.Comment;

import com.example.demo01.model.User;
import com.example.demo01.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {



    @Autowired(required = false)
    private CommentService commentService;

    @ResponseBody
    @RequestMapping( "/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomzieErrorCode.NOT_LOGIN);
        }
        if(commentCreateDTO==null|| StringUtils.isBlank(commentCreateDTO.getContent()))
        {
            return ResultDTO.errorOf(CustomzieErrorCode.CONTENT_IS_EMPTY);

        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping( "/comment/{id}")
    public ResultDTO<List> comment(@PathVariable(name = "id") Long id,
                             Model model){
        List<CommentDTO> commentDTOS= commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
