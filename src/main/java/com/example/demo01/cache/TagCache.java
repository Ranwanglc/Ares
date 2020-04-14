package com.example.demo01.cache;

import com.example.demo01.dto.TagDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS=new ArrayList<>();
        TagDTO program= new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node","python"));
        tagDTOS.add(program);

        TagDTO framework=new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","express","flask","koa","yii"));
        tagDTOS.add(framework);

        TagDTO thesis= new TagDTO();
        thesis.setCategoryName("论文模板");
        thesis.setTags(Arrays.asList("模板查找","图片插入"));
        tagDTOS.add(thesis);

        return tagDTOS;
    }
    public static String  filterInvalid(String tags)
    {
        String[] split = tags.split(",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
