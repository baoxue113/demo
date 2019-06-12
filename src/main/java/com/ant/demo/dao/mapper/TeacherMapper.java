package com.ant.demo.dao.mapper;

import com.ant.demo.dao.model.TeacherDO;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherDO record);

    int insertSelective(TeacherDO record);

    TeacherDO selectByPrimaryKey(Integer id);

    TeacherDO selectByName(String name);

    int updateByPrimaryKeySelective(TeacherDO record);

    int updateByPrimaryKey(TeacherDO record);

    int deleteByUUID(String uuid);
}