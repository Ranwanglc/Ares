package com.example.demo01.controller;

import com.example.demo01.dto.AccessTokenTDO;
import com.example.demo01.dto.GithubUser;

import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo01.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {



    private GithubProvider githubProvider;

    @Autowired
    public void setGithubProvider (GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }


    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){this.userMapper=userMapper;}

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request
                           ) throws IOException {
        AccessTokenTDO accessTokenTDO = new AccessTokenTDO();
        accessTokenTDO.setClient_id(clientId);
        accessTokenTDO.setCode(code);
        accessTokenTDO.setClient_secret(clientSecret);
        accessTokenTDO.setRedirect_uri(redirectUri);
        accessTokenTDO.setState(state);
        String accessToken=githubProvider.GetAccessToken(accessTokenTDO);
        System.out.println(accessToken);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        System.out.println(githubUser);
        if(githubUser != null){
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert1(user);
            request.getSession().setAttribute("user",githubUser);return "redirect:/";
        }
        else{
            return "redirect:/";
        }


    }
}
