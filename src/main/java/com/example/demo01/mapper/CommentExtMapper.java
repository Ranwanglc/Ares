package com.example.demo01.mapper;


import com.example.demo01.model.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentExtMapper {
    int incCommentCount1(@Param("record") Comment record);
}
