package com.example.demo01.mapper;

import com.example.demo01.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    @Insert({"insert into  PUBLIC.USER (name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})"})
     void insert1(User user);

}
