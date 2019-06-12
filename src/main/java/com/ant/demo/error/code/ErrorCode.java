package com.ant.demo.error.code;

public enum ErrorCode {

    SYSTEM_UNKNOWN_ERROR("-10000","系统未知异常,请联系相关客服")
    ,ADD_TEACHER_AND_STUDENT_ERROR("10000","添加老师与学生失败")
    ,TEACHER_NAME_SAME("10001","已经存在同名的老师")
    ,TEACHERS_STUDENT_NAME_SAME("10002","老师下面的学生不能有相同的名字")
    ,;

    private String msg;
    private String code;

    private ErrorCode(String code, String msg)
    {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg()
    {
        return this.msg;
    }
    public String getCode() {
        return this.code;
    }
}
