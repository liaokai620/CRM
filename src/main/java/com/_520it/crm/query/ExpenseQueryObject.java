package com._520it.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class ExpenseQueryObject extends QueryObject {
    private String     keyword;         //关键字查询
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date       beginTime;       //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date       endTime;         //结束时间

    private BigDecimal minexpendamount;     //最小收款金额
    private BigDecimal maxexpendamount;     //最大收款金额
    private Long       tellerId;        //出纳
    private Long       personId;        //收款人
    private String     expendtype;      //收款类型
    private String     expendmode;       //班级名称
    private String     classify;     //学科名称
    private String     subject;    //营销人员名称
}
