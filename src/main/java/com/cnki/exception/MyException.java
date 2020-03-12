package com.cnki.exception;

/**
 * 自定义异常类，主要处理运行时异常
 */
public class MyException extends RuntimeException {

    private String message;

    public MyException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
