package com.ant.demo.conversion;

import com.ant.demo.controller.from.StudentFrom;
import com.ant.demo.dao.model.StudentDO;
import com.ant.demo.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentConver {
    public static List<StudentDO> teacherStudentVO2StudentForAdd(List<StudentFrom> studentFroms){
        List studentDOs = new ArrayList();
        studentFroms.stream().forEach(studentFrom -> {
            StudentDO studentDO = new StudentDO();
            if(StringUtils.isBlank(studentFrom.getUuid())){
                studentDO.setUuid(UUIDUtils.getUUID());
            }
            studentDO.setAddress(studentFrom.getAddress());
            studentDO.setName(studentFrom.getName());
            studentDO.setSex(studentFrom.getSex());
            studentDO.setAge(studentFrom.getAge());
            studentDO.setCreater("System");
            studentDO.setCreateTime(new Date());
            studentDO.setUpdater("System");
            studentDO.setUpdaterTime(new Date());
            studentDOs.add(studentDO);
        });
        return studentDOs;
    }

    public static List<StudentFrom> dataDo2StudentFrom(List<StudentDO> studentDOs){
        List<StudentFrom> studentFroms = new ArrayList<>();
        studentDOs.stream().forEach(studentDO -> {
            StudentFrom studentFrom = new StudentFrom();
            studentFrom.setUuid(studentDO.getUuid());
            studentFrom.setAddress(studentDO.getAddress());
            studentFrom.setAge(studentDO.getAge());
            studentFrom.setName(studentDO.getName());
            studentFrom.setSex(studentDO.getSex());
            studentFroms.add(studentFrom);
        });
        return studentFroms;
    }
}
