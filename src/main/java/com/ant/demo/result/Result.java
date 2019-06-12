package com.ant.demo.result;


import com.ant.demo.constant.system.error.ErrorCodeConstant;
import com.ant.demo.error.code.ErrorCode;
import com.ant.demo.error.exception.BusinessException;
import org.springframework.validation.ObjectError;

public class Result {
     boolean requestIsOk;

     String message;

     String code;

     Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.requestIsOk = true;
        result.data = data;
        return result;
    }

    public static Result failure(String code, String message){
        Result result = new Result();
        result.requestIsOk = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public static Result failure(ErrorCode errorCode){
        Result result = new Result();
        result.requestIsOk = false;
        result.code = errorCode.getCode();
        result.message = errorCode.getMsg();
        return result;
    }

    public static Result failure(ObjectError error) {
        Result result = new Result();
        result.requestIsOk = false;
        result.code = ErrorCodeConstant.VALID_ERROR_CODE;
        result.message = error.getDefaultMessage();
        return result;
    }

    public static Result failure(BusinessException e) {
        Result result = new Result();
        result.requestIsOk = false;
        result.code = e.getCode();
        result.message = e.getMessage();
        result.data = e.getData();
        return result;
    }


    public boolean isRequestIsOk() {
        return requestIsOk;
    }

    public void setRequestIsOk(boolean requestIsOk) {
        this.requestIsOk = requestIsOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
