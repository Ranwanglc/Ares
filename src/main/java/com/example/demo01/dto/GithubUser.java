package com.example.demo01.dto;

public class GithubUser {
    private String bio;
    private String name;
    private Long id;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "bio='" + bio + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
