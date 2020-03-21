package com.example.demo01.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String bio;
    private String name;
    private Long id;
    private String avatar_url;


    @Override
    public String toString() {
        return "GithubUser{" +
                "bio='" + bio + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
