package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//课程的信息封装类
@Getter@Setter
public class Course {

    public static final int NORMAL = 0;//已上
    public static final int LEAVE  = 1;//未上

    private Long id;

    private String name;

    private Integer state; //状态:已上/未上

    private String remark;  //备注:


    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date schoolTime;  //上课时间

    private Classroom classroom;  //教室

    private Employee teacher;   //上课老师

    private Employee classTeacher;  //班主任

    private Grade grade;  //班级
}
