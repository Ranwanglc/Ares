package com.example.demo01.dto;

import com.example.demo01.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String tittle;
    private String description;
    private long gmtCreate;
    private long gmtCodified;
    private int creator;
    private String tag;
    private  int likeCount;
    private  int viewCount;
    private int commentCount;
    private User user;
}
