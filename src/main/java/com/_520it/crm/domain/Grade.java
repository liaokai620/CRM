package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//班级信息封装类
@Getter@Setter
public class Grade {

    public static final int NORMAL = 0;//未毕业
    public static final int LEAVE  = 1;//已结业

    private Long id;

    private String name; //班级名称

    private Integer number; //班级人数

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginTime;  //开班时间

    private int state = NORMAL;  //状态:默认未毕业

    private Employee classTeacher; //班主任

    private Course course; //课程

}