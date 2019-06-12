package com.ant.demo.dao.mapper;

import com.ant.demo.dao.model.StudentDO;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentDO record);

    int insertSelective(StudentDO record);

    StudentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentDO record);

    int updateByPrimaryKey(StudentDO record);

    void insertStudents(List<StudentDO> studentDOs);

    List<StudentDO> selectByUUIDS(List<String> studentUUID);

    int deleteByUUIDS(List<String> studentUUID);
}