package com.ant.demo.controller.from;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

public class TeacherStudentFrom {
    String teacherUUID;
    @NotBlank(message = "老师名不能为空")
    String name;
    @NotBlank(message = "老师性别不能为空")
    @Pattern(regexp = "^['男'|'女']$", message = "老师性别填写错误")
    String sex;
    @NotBlank(message = "老师住址不能为空")
    String address;
    @NotEmpty(message = "老师下面必须要有学生")
    @Valid
    List<StudentFrom> studentList;

    public String getTeacherUUID() {
        return teacherUUID;
    }

    public void setTeacherUUID(String teacherUUID) {
        this.teacherUUID = teacherUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StudentFrom> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentFrom> studentList) {
        this.studentList = studentList;
    }
}
