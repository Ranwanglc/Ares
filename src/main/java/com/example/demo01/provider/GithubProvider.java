package com.example.demo01.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo01.dto.AccessTokenTDO;
import com.example.demo01.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GithubProvider {
    public String GetAccessToken(AccessTokenTDO accessTokenTDO )  {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenTDO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String str = response.body().string();

            return str.split("&")[0].split("=")[1];
        }
        catch(IOException ignored){

        }
        return null;
    }

    public GithubUser getUser(String accessToken)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try
        {
            Response response = client.newCall(request).execute();

            assert response.body() != null;
            String str=response.body().string();
            GithubUser githubUser=JSON.parseObject(str,GithubUser.class);
            return githubUser;

        }
        catch (IOException e){}
        return null;
    }
}
