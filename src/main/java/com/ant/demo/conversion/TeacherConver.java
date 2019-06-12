package com.ant.demo.conversion;

import com.ant.demo.controller.from.TeacherStudentFrom;
import com.ant.demo.dao.model.StudentDO;
import com.ant.demo.dao.model.TeacherDO;
import com.ant.demo.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class TeacherConver {

    public static TeacherDO teacherStudentFrom2Teacher(TeacherStudentFrom teacherStudentFrom){
        TeacherDO teacherDO = new TeacherDO();
        String teacherUUID = teacherStudentFrom.getTeacherUUID();
        teacherDO.setUuid(teacherUUID);
        if(StringUtils.isEmpty(teacherUUID)){
            teacherDO.setUuid(UUIDUtils.getUUID());
        }
        teacherDO.setAddress(teacherStudentFrom.getAddress());
        teacherDO.setName(teacherStudentFrom.getName());
        teacherDO.setSex(teacherStudentFrom.getSex());
        return teacherDO;
    }

    public static TeacherStudentFrom dataDo2TeacherStudentFrom(TeacherDO teacherDO, List<StudentDO> studentDOS){
        TeacherStudentFrom teacherStudentFrom = new TeacherStudentFrom();
        teacherStudentFrom.setTeacherUUID(teacherDO.getUuid());
        teacherStudentFrom.setName(teacherDO.getName());
        teacherStudentFrom.setAddress(teacherDO.getAddress());
        teacherStudentFrom.setSex(teacherDO.getSex());
        teacherStudentFrom.setStudentList(StudentConver.dataDo2StudentFrom(studentDOS));
        return teacherStudentFrom;
    }
}
