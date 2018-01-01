package com._520it.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class IncomeQueryObject extends QueryObject {
    private String     keyword;             //关键字查询
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date       beginTime;           //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date       endTime;             //结束时间

    private BigDecimal minIncomeAmount;     //最小收款金额
    private BigDecimal maxIncomeAmount;     //最大收款金额
    private Long       payeeId;             //收款人
    private String       incomeTypeId;        //收款类型
    private Long       gradeId;             //班级名称
    private String       subjectId;           //学科名称
    private Long       salesmanId;          //营销人员名称

    public String getKeyword(){
        return StringUtils.isEmpty(keyword) ? null : keyword;
    }
}
