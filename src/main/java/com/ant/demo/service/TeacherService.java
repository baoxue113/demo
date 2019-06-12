package com.ant.demo.service;


import com.ant.demo.controller.from.TeacherStudentFrom;
import com.ant.demo.result.Result;

public interface TeacherService {
    /**
     * 添加老师和学生
     *
     * @param teacherStudentFrom
     * @return
     */
    Result addTeacherAndStudent(TeacherStudentFrom teacherStudentFrom);

    /**
     * 根据老师名查询老师以及学生
     * @param name
     * @return
     */
    Result queryTeacherAndStudentByName(String name);

    /**
     * 根据老师名删除老师以及学生
     *
     * @param name
     * @return
     */
    Result deleteTeacherAndStudentByName(String name);

    /**
     * 根据学生名删除学生以及老师
     *
     * @param name
     * @return
     */
    Result deleteTeacherAndStudentByStudentName(String name);
}
