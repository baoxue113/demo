package com.ant.demo.service.impl;

import com.ant.demo.constant.system.error.ErrorCodeConstant;
import com.ant.demo.controller.from.TeacherStudentFrom;
import com.ant.demo.conversion.StudentConver;
import com.ant.demo.conversion.TeacherConver;
import com.ant.demo.dao.mapper.StudentMapper;
import com.ant.demo.dao.mapper.TeacherMapper;
import com.ant.demo.dao.mapper.TeacherStudentRelationshipMapper;
import com.ant.demo.dao.model.StudentDO;
import com.ant.demo.dao.model.TeacherDO;
import com.ant.demo.dao.model.TeacherStudentRelationshipDO;
import com.ant.demo.error.exception.BusinessException;
import com.ant.demo.result.Result;
import com.ant.demo.service.TeacherService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Resource
    StudentMapper studentMapper;
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    TeacherStudentRelationshipMapper teacherStudentRelationshipMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addTeacherAndStudent(TeacherStudentFrom teacherStudentFrom) {

        // 添加老师
        TeacherDO teacherDO = TeacherConver.teacherStudentFrom2Teacher(teacherStudentFrom);
        try {
            teacherMapper.insertSelective(teacherDO);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new BusinessException(ErrorCodeConstant.DATABASE_UNIQUE_ERROR,e.getCause().getLocalizedMessage(),teacherDO);
            }
            throw e;
        }
        // 添加学生
        List<StudentDO> studentDOs = StudentConver.teacherStudentVO2StudentForAdd(
                teacherStudentFrom.getStudentList());
        List<TeacherStudentRelationshipDO> relationships = new ArrayList<>();
        studentDOs.stream().forEach(studentDO -> {
            TeacherStudentRelationshipDO teacherStudentRelationshipDO = new TeacherStudentRelationshipDO();
            teacherStudentRelationshipDO.setStudentUuid(studentDO.getUuid());
            teacherStudentRelationshipDO.setTeacherUuid(teacherDO.getUuid());
            teacherStudentRelationshipDO.setStudentName(studentDO.getName());
            relationships.add(teacherStudentRelationshipDO);
        });
        studentMapper.insertStudents(studentDOs);
        // 添加老师与学生关系
        try {
            teacherStudentRelationshipMapper.insertTeacherStudentRelationship(relationships);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new BusinessException(ErrorCodeConstant.DATABASE_UNIQUE_ERROR,e.getCause().getLocalizedMessage(),relationships);
            }
            throw e;
        }
        return Result.success(null);
    }

    @Override
    public Result queryTeacherAndStudentByName(String name) {
        TeacherDO teacherDO = teacherMapper.selectByName(name);
        List<String> studentUUIDs = teacherStudentRelationshipMapper.selectByTeacherUUID(teacherDO.getUuid());
        List<StudentDO> studentDOS = studentMapper.selectByUUIDS(studentUUIDs);
        return Result.success(TeacherConver.dataDo2TeacherStudentFrom(teacherDO,studentDOS));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteTeacherAndStudentByName(String name) {
        TeacherDO teacherDO = teacherMapper.selectByName(name);
        String teacherUuid = teacherDO.getUuid();
        teacherMapper.deleteByUUID(teacherUuid);
        List<String> studentUUIDs = teacherStudentRelationshipMapper.selectByTeacherUUID(teacherUuid);
        studentMapper.deleteByUUIDS(studentUUIDs);
        teacherStudentRelationshipMapper.deleteByUUID(teacherUuid);
        return Result.success(teacherDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteTeacherAndStudentByStudentName(String name) {

        return Result.success(null);
    }
}
