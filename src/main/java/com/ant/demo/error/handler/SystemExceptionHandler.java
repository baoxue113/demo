package com.ant.demo.error.handler;

import com.ant.demo.controller.TeacherController;
import com.ant.demo.error.code.ErrorCode;
import com.ant.demo.error.exception.BusinessException;
import com.ant.demo.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(TeacherController.class);
    /**
     * 处理系统业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity handleServiceException(BusinessException e) {
        logger.error("",e);
        return new ResponseEntity(Result.failure(e), HttpStatus.OK);
    }

    /**
     * 处理系统未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleSystemException(Exception e) {
        logger.error("",e);
        return new ResponseEntity(Result.failure(ErrorCode.SYSTEM_UNKNOWN_ERROR), HttpStatus.OK);
    }
}
