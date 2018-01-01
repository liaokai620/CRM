package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 学员管理模块 -> 学员流失
 */
@Setter
@Getter
public class Disappear {

    public static final Integer STATE_YES = 0;
    public static final Integer STATE_NO = 1;

    private Long id;

    private Integer toclassdate;        //上课天数

    private String disappearphases;     //流失学科

    private String disappearcause;      //流失原因

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disappeartime;         //流失时间

    private Boolean refund = false;             //是否退款

    private Integer state = 0;          //状态

    private Student student;            //学员

    private Employee inputuser;         //录入人

    private Employee salesman;          //营销人员

    private Grade grade;                //流失班级

}