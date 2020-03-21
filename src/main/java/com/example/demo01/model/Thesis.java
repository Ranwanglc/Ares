package com.example.demo01.model;

import lombok.Data;

@Data
public class Thesis {
    private int id;
    private String tittle;
    private String paragraph;
    private long gmt_create;
    private long gmt_modified;
    private int creator;
    private String reference;


}
