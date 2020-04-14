package com.example.demo01.controller;

import com.example.demo01.dto.AccessTokenTDO;
import com.example.demo01.dto.GithubUser;


import com.example.demo01.model.User;

import com.example.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo01.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {



    private GithubProvider githubProvider;

    @Autowired
    public void setGithubProvider (GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }



    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired(required = false)
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response,
                           HttpServletRequest request
                           ) throws IOException {
        AccessTokenTDO accessTokenTDO = new AccessTokenTDO();
        accessTokenTDO.setClient_id(clientId);
        accessTokenTDO.setCode(code);
        accessTokenTDO.setClient_secret(clientSecret);
        accessTokenTDO.setRedirect_uri(redirectUri);
        accessTokenTDO.setState(state);
        String accessToken=githubProvider.GetAccessToken(accessTokenTDO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        if(githubUser != null){
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));

            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            Cookie cookie =new Cookie("token",token);
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            return "redirect:/";
        }
        else{
            return "redirect:/";
        }


    }

    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response

    ){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "Todo";
    }
}
