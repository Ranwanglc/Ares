package com.example.demo01.exception;

public enum CustomzieErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，换一个试试吧"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何的问题或者评论进行回复"),
    NOT_LOGIN(2003,"未登录，请先登录"),
    SYS_ERROR(2004,"服务器脑壳进水了，您要不先歇一会"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，再试试"),
    CONTENT_IS_EMPTY(2007,"您的评论内容为空")
    ;

    private Integer code;
    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomzieErrorCode( Integer code,String message) {
        this.message = message;
        this.code = code;
    }


}
