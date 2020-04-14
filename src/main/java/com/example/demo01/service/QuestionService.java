package com.example.demo01.service;

import com.example.demo01.dto.PaginationDTO;
import com.example.demo01.dto.QuestionDTO;
import com.example.demo01.exception.CustomizeException;
import com.example.demo01.exception.CustomzieErrorCode;
import com.example.demo01.mapper.QuestionExMapper;
import com.example.demo01.mapper.QuestionMapper;
import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.Question;
import com.example.demo01.model.QuestionExample;
import com.example.demo01.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private QuestionExMapper questionExMapper;

    public PaginationDTO list(Integer page,Integer size){
        PaginationDTO paginationDTO= new PaginationDTO();



        Integer totalCount=(int)questionMapper.countByExample(new QuestionExample());

        Integer totalPage_u;
        if(totalCount==0){totalPage_u=totalCount/size;}
        else {totalPage_u=(totalCount/size)+1;}
        //不合法page处理
        if(page<1){page=1;}
        if(page>totalPage_u){page=totalPage_u;}

        paginationDTO.setPagination(totalCount,page,size);
        Integer offset =size*(page-1);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questions=questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));

        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return  paginationDTO;
    }

    public PaginationDTO List(Long id, Integer page, Integer size) {
        PaginationDTO paginationDTO= new PaginationDTO();
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount=(int)questionMapper.countByExample(questionExample);
        Integer totalPage_u;
        if(totalCount==0){totalPage_u=totalCount/size;}
        else {totalPage_u=(totalCount/size)+1;}
        //不合法page处理
        if(page<1){page=1;}
        if(page>totalPage_u){page=totalPage_u;}
        Integer offset =size*(page-1);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        example.setOrderByClause("gmt_create desc");
        List<Question> questions=questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));

        paginationDTO.setPagination(totalCount,page,size);
        for(Question question : questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return  paginationDTO;
    }

    public QuestionDTO getByid(Long id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if(question ==null)
        {
            throw new CustomizeException(CustomzieErrorCode.QUESTION_NOT_FOUND);

        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        Question dbquestion=questionMapper.selectByPrimaryKey(question.getId());
        if(dbquestion == null)
        {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
        }
        else
        {
            //更新
            question.setGmtModified(question.getGmtCreate());
            Question updatequestion =new Question();
            updatequestion.setGmtModified(System.currentTimeMillis());
            updatequestion.setTag(question.getTag());
            updatequestion.setDescription(question.getDescription());
            updatequestion.setTittle(question.getTittle());
            QuestionExample questionExample=new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int  update=questionMapper.updateByExampleSelective(updatequestion,questionExample);
            if(update!=1)
            {
                throw new CustomizeException(CustomzieErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question= new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if (StringUtils.isBlank(questionDTO.getTag())) {
            return new ArrayList<>();
        }
        String tags = questionDTO.getTag().replace('，', ',').replace(',', '|');
        questionDTO.setTag(tags);
        Question question=new Question();
        question.setId(questionDTO.getId());
        question.setTag(tags);
        List<Question> list=questionExMapper.selectRelated(question);
        List<QuestionDTO> collect = list.stream().map(q -> {
            QuestionDTO questionDTO1 = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());
        return collect;
    }
}
