package com.example.demo01.mapper;

import com.example.demo01.model.Question;
import com.example.demo01.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExMapper {
    int incView(@Param("record") Question record);
}