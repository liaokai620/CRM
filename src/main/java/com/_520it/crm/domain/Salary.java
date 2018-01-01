package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

//工资表
@Getter
@Setter
@ToString
public class Salary {
    public static final BigDecimal DECIMAL_ZERO = BigDecimal.ZERO;
    private Long id;                      //编号

    private BigDecimal basesalary;        //基本工资

    private BigDecimal alldaysalary;      //全勤奖金

    private BigDecimal subsidy;           //补贴

    private BigDecimal fivesalary;        //五险一金

    private BigDecimal fine;              //罚款

    private BigDecimal commission;        //提成

    private BigDecimal totalsalary;       //总工资

    private BigDecimal aftersalary;       //税后工资

    private BigDecimal yearendsalary;     //年终奖

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date salarytime;        //日期

    private Employee employee;      //员工

    private int state;              //状态

    public BigDecimal getAlldaysalary() {
        if (alldaysalary == null) {
            return DECIMAL_ZERO;
        }
        return alldaysalary;
    }

    public BigDecimal getSubsidy() {
        if (subsidy == null) {
            return DECIMAL_ZERO;
        }
        return subsidy;

    }

    public BigDecimal getFivesalary() {
        if (fivesalary == null) {
            return DECIMAL_ZERO;
        }
        return fivesalary;
    }

    public BigDecimal getFine() {
        if (fine == null) {
            return DECIMAL_ZERO;
        }
        return fine;
    }

    public BigDecimal getCommission() {
        if (commission == null) {
            return DECIMAL_ZERO;
        }
        return commission;
    }

    public BigDecimal getYearendsalary() {
        if(yearendsalary==null){
            return DECIMAL_ZERO; 
        }
        return yearendsalary;
    }
}