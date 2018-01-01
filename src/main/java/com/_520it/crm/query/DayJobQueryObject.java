package com._520it.crm.query;

import com._520it.crm.domain.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MrHou
 * @date 2017/11/9.
 */
@Getter
@Setter
public class DayJobQueryObject extends QueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   queryTime;
    private Long querHandlerId;
    private Long currentId;
    private Long deptId;
    public Date getQueryTime() {
        if (queryTime != null) {

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(queryTime);
            calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动

            return calendar.getTime();
        }
        return null;
    }

}
