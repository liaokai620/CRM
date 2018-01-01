package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/10.
 */
//销售报表高级查询
@Getter
@Setter
public class SaleQueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime; //开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime; //结束时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBeginTime() {
        if (beginTime != null) {
            return beginTime;
        }
        return null;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndTime() {
        if (endTime != null) {
            return DateUtil.getEndDate(endTime);
        }
        return null;
    }

    private String keyword;

    public String getKeyword() {
        return StringUtils.isNotBlank(keyword) ? keyword : null;
    }
}
