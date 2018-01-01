package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/11.
 */
@Setter
@Getter
public class ExpenseReportQueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime; //开始时间


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime; //结束时间


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndTime() {
        if (endTime != null) {

            return DateUtil.getEndDate(endTime);
        }
        return null;
    }


    private String groupBy = "emp.realname";

    private String keyword;

    public static Map<String, Object> expenseMap = new LinkedHashMap<>();


    static {
        expenseMap.put("emp.realname", "出纳人");
        expenseMap.put("e.expendtype", "支出方式");
        expenseMap.put("e.expendmode", "支出类型");
        expenseMap.put("date_format(e.expendtime,'%Y-%m-%d')", "支出时间(日)");
        expenseMap.put("date_format(e.expendtime,'%Y-%m')", "支出时间(月)");
    }
}
