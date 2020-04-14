package com.example.demo01.service;

import com.example.demo01.dto.CommentDTO;
import com.example.demo01.enums.CommentTypeEnum;
import com.example.demo01.enums.NotificationEnum;
import com.example.demo01.enums.NotificationStatusEnum;
import com.example.demo01.exception.CustomizeException;
import com.example.demo01.exception.CustomzieErrorCode;

import com.example.demo01.mapper.*;
import com.example.demo01.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private QuestionExMapper questionExMapper;

    @Autowired(required = false)
    private CommentExtMapper commentExtMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment) {

        /*检测传输的数据是否缺少或丢失*/
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomzieErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomzieErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null)
            {
                throw new CustomizeException(CustomzieErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);

            //增加评论数
            Comment parentcomment = new Comment();
            parentcomment.setId(comment.getParentId());
            parentcomment.setCommentCount(1);
            commentExtMapper.incCommentCount1(parentcomment);
            /*创建通知*/
            createNotify(comment, dbComment);
        } else {
            //回复问题
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question ==null)
            {
                throw new CustomizeException(CustomzieErrorCode.QUESTION_NOT_FOUND);
            }
            else
            {
                commentMapper.insert(comment);
                question.setCommentCount(1);
                questionExMapper.incCommentCount(question);
            }
            Notification notification = new Notification();
            notification.setType(NotificationEnum.REPLY_COMMENT.getType());
            notification.setGmtCreate(comment.getGmtCreate());
            notification.setNotifier(comment.getCommentator());
            notification.setOuterid(comment.getParentId());
            notification.setReceiver(question.getCreator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notificationMapper.insert(notification);
        }

    }

    private void createNotify(Comment comment, Comment dbComment) {
        Notification notification = new Notification();
        notification.setType(NotificationEnum.REPLY_COMMENT.getType());
        notification.setGmtCreate(comment.getGmtCreate());
        notification.setNotifier(comment.getCommentator());
        notification.setOuterid(comment.getParentId());
        notification.setReceiver(dbComment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0)
        {
            return new ArrayList<>();
        }
        //获取去除重复的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userId=new ArrayList<>();
        userId.addAll(commentators);

        //通过userId找到user组成列表
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userId);
        List<User> users = userMapper.selectByExample(userExample);

        //通过users生成一种user的Map
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //将comment列表转换成commentDTO列表（commentDTO比前者多包含一个user）
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }


}
