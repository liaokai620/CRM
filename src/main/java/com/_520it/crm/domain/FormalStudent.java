package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 学员管理模块 -> 正式学员
 */
@Setter
@Getter
public class FormalStudent {

    public static final Integer STATE_YES = 1;  //已缴清
    public static final Integer STATE_NO = 0;   //未缴清

    private Long id;

    private BigDecimal totalamount; //总学费

    private BigDecimal paid;        //已缴费

    private BigDecimal notpaid;     //待缴费

    private Integer feestate;       //支付状态

    private String inputtime;       //入学时间

    private String schoolname;      //学校名称

    private String qq;              //qq

    private String phonenumber;     //电话

    private String paytype;         //支付类型

    private Integer state;          //状态

    private Student student;        //学员

    private Employee salesman;      //营销人员

    private Grade grade;            //班级

}