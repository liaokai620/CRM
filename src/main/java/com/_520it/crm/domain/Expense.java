package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工管理模块->支出管理
 */
@Setter
@Getter
public class Expense {

    public static final Integer STATE_NONE = 0;
    public static final Integer STATE_NORMAL = 1;


    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expendtime;            //支出时间

    private BigDecimal expendamount;    //支出总金额

    private String expendtype;          //支出方式

    private String expendmode;          //支出类型

    private String classify;            //详细分类

    private String subject;             //支出所属学科

    private String billnumber;          //单据号码

    private Integer audit = STATE_NONE; //状态

    private Employee teller;            //出纳

    private Student person;             //经手人

    private String note;                //摘要

}