package com.ant.demo.dao.mapper;

import com.ant.demo.dao.model.TeacherStudentRelationshipDO;

import java.util.List;

public interface TeacherStudentRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherStudentRelationshipDO record);

    int insertSelective(TeacherStudentRelationshipDO record);

    TeacherStudentRelationshipDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherStudentRelationshipDO record);

    int updateByPrimaryKey(TeacherStudentRelationshipDO record);

    void insertTeacherStudentRelationship(List<TeacherStudentRelationshipDO> relationships);

    /**
     * 查询老师下所有学生的uuid
     *
     * @param uuid
     * @return
     */
    List<String> selectByTeacherUUID(String uuid);

    /**
     * 删除老师下的所有学生关系
     *
     * @param teacherUuid
     * @return
     */
    int deleteByUUID(String teacherUuid);
}