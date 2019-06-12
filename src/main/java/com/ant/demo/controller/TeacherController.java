package com.ant.demo.controller;


import com.ant.demo.controller.from.TeacherStudentFrom;
import com.ant.demo.error.code.ErrorCode;
import com.ant.demo.error.exception.BusinessException;
import com.ant.demo.result.Result;
import com.ant.demo.service.TeacherService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    @ResponseBody
    public Result add(@Valid @RequestBody TeacherStudentFrom teacherStudentFrom, BindingResult errors) {
        // 校验失败
        if(errors.hasErrors()){
            return Result.failure(errors.getAllErrors().get(0));
        }
        // 业务逻辑
        return teacherService.addTeacherAndStudent(teacherStudentFrom);
    }

    @GetMapping("/get")
    @ResponseBody
    public Result get(@RequestParam(value = "name") String name) {

        // 业务逻辑
        return teacherService.queryTeacherAndStudentByName(name);
    }

    @GetMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam(value = "name") String name) {

        // 业务逻辑
        return teacherService.deleteTeacherAndStudentByName(name);
    }

    @GetMapping("/delete/student")
    @ResponseBody
    public Result deleteStudent(@RequestParam(value = "name") String name) {

        // 业务逻辑
        return teacherService.deleteTeacherAndStudentByStudentName(name);
    }
}