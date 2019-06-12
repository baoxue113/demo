package com.ant.demo.service.impl;

import com.ant.demo.dao.model.Teacher;
import com.ant.demo.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

//    @Resource
//    private TeacherMapper teacherMapper;
//
//    @Override
//    public int addUser(Teacher teacher) {
//
//        return teacherMapper.insertSelective(teacher);
//    }

//    /*
//    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
//    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
//    * pageNum 开始页数
//    * pageSize 每页显示的数据条数
//    * */
//    @Override
//    public List<User> findAllUser(int pageNum, int pageSize) {
//        //将参数传给这个方法就可以实现物理分页了，非常简单。
//        PageHelper.startPage(pageNum, pageSize);
//        return userMapper.selectAllUser();
//    }
}