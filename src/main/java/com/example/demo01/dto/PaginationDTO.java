package com.example.demo01.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {


         if(totalCount%size==0&&totalCount!=0){totalPage=totalCount/size;}
         else {totalPage=(totalCount/size)+1;}



        this.page=page;


         //展示什么页数
        pages.add(page);
         for (int i=1;i<=3;i++)
         {
             //判断page前的三个数
             if(page-i>0){pages.add(0,page-i);}

             //判断page后三个数字显示什么
             if(page+i<=totalPage){pages.add(page+i);}

         }

         //是否展示上一页
         if(page==1){showPrevious=false;}
         else{showPrevious=true;}

         //是否展示下一页
         if(totalPage==page){showNext=false;}
         else{showNext=true;}

         //是否展示首页
        if(pages.contains(1)){showFirstPage=false;}
        else{showFirstPage=true;}

        //是否展示尾页
        if(pages.contains(totalPage)){showEndPage=false;}
        else{showEndPage=true;}
    }
}
