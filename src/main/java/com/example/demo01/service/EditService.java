package com.example.demo01.service;

import com.example.demo01.mapper.ThesisMapper;
import com.example.demo01.model.ThesisWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditService {

    @Autowired(required = false)
    private ThesisMapper thesisMapper;

    public void insert(ThesisWithBLOBs thesis){
        thesisMapper.insert(thesis);
    }
}
