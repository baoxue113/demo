package com.ant.demo.error.exception;

import com.ant.demo.error.code.ErrorCode;

public class BusinessException extends RuntimeException{
    private String code;

    private String message;

    private Object data;

    public BusinessException(){

    }

    public BusinessException(String code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BusinessException(ErrorCode errorCode, Object data){
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
