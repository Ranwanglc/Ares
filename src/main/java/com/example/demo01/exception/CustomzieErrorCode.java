package com.example.demo01.exception;

public enum CustomzieErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，换一个试试吧");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomzieErrorCode(String message) {
        this.message = message;
    }
}
