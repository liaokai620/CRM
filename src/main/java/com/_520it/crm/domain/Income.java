package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Income {

    public static final Integer STATE_WAIT = 0;
    public static final Integer STATE_RECHECK = 1;
    public static final Integer STATE_AUDIT = 2;


    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date incometime;

    private BigDecimal incomeamount;      //收款金额

    private String billnumber;      //单据号

    private String remark;          //备注

    private String subject;           //收款相关学科

    private String incometype;        //收款类型

    private Employee payee;         //收款人

    private Student student;        //学员

    private Employee salesman;      //营销人员

    private Grade grade;            //班级

    private Integer totalcount;     //统计次数

    private BigDecimal totalamount; //统计总金额

    private Integer state = STATE_WAIT; //审核状态

}