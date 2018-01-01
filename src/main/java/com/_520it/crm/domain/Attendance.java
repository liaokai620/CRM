package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Attendance {       //考勤表
    private Long id;            //考勤编号

    private String worktime;      //规定上班时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date clocktime;     //打卡时间

    private Integer state;      //状态

    private Employee employee;  //员工

}