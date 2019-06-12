package com.ant.demo.controller.from;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class StudentFrom {
    String uuid;
    String name;
    @Pattern(regexp = "^['男'|'女']$", message = "学生性别填写错误")
    String sex;
    String address;
    @Min(value = 10,message = "学生年龄不能小于10")
    @Max(value = 15,message = "学生年龄不能大于15")
    Short age;

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

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
