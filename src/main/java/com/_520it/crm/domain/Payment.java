package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Payment {

    public static final Integer STATE_NO = 0;   //缴费状态 0未缴清  1已缴清
    public static final Integer STATE_YES = 1;

    private Long id;


    private BigDecimal planTuition;         //计划学费


    private String privilege;               //优惠说明

    private BigDecimal prepaid;             //已付款

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nearlyPaymentTime;         //最近付款时间


    private BigDecimal otherCost = BigDecimal.ZERO;           //其他费用

    private BigDecimal totalAmount = BigDecimal.ZERO;         //总费用

    private BigDecimal arrearage = BigDecimal.ZERO;           //还欠学费

    private BigDecimal tuition;             //学费

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nestPaymentTime;           //下次缴费时间

    private BigDecimal cheapSum;            //优惠金额

    private String otherCheap;              //其他优惠

    private String saleWater;               //销售流水

    private SystemDictionaryItem  cheapWay; //折扣方式

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastPaymentTime;           //最后付款时间

    private Integer reminderTimes;          //催款次数

    private Student student;                //学生

    private Grade grade;                    //班级

    private Employee salesman;              //营销人员

    private Integer state = STATE_NO;       //缴费状态 默认未缴清

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrolTime;                 //入学时间

    private String paymentType;             //缴费方式

}