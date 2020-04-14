package com.example.demo01.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private String content;
    private Integer type;
    private long parentId;
}
