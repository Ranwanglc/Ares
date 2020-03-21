package com.example.demo01.mapper;

import com.example.demo01.model.Thesis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface EditMapper {
    @Insert({"insert into PUBLIC.THESIS (tittle,paragraph,gmt_create,gmt_modified,creator,reference) values(#{tittle},#{paragraph},#{gmt_create},#{gmt_modified},#{creator},#{reference})"})
    void create(Thesis thesis);
}
