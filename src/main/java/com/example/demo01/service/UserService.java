package com.example.demo01.service;

import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.User;
import com.example.demo01.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired(required =  false)
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample= new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> dbUser=userMapper.selectByExample(userExample);
        if(dbUser.size() ==0)
        {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
        else
        {
            User dbuser=dbUser.get(0);

            User updateUser=new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example=new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser,example);

        }
    }
}
