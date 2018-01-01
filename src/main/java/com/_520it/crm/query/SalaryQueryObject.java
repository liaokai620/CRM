package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
@Setter
@Getter
public class SalaryQueryObject extends QueryObject {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private     Date   beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private     Date   endDate;
    @DateTimeFormat(pattern = "yyyy-MM")
    private     Date   month;
    @DateTimeFormat(pattern = "yyyy-MM")
    private     Date   monthPlus;

    private Integer state;

    public Date getMonthPlus() {

        return  DateUtil.getMonthPlus(month) ;
    }

    public Date getEndDate() {
        return endDate != null ? DateUtil.getEndDate(endDate) : null;
    }
}

