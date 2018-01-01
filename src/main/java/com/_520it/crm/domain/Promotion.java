package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Promotion {

    public static final Integer STATE_DOWN = 0;
    public static final Integer STATE_UP = 1;

    private Long id;

    private BigDecimal account;         //销售流水

    private BigDecimal tip;             //其他杂费

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date promotiontime;         //升级时间

    private Integer state = STATE_DOWN; //当前状态

    private Employee salesman;          //营销人员

    private Student student;            //学员

    private Payment payment;              //学费

    private Grade beforegrade;          //以前的班级

    private Grade aftergrade;           //流入班级

}