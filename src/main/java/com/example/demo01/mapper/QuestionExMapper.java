package com.example.demo01.mapper;

import com.example.demo01.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface QuestionExMapper {
    int incView(@Param("record") Question record);
    int incCommentCount(@Param("record") Question record);

    List<Question> selectRelated(Question question);
}