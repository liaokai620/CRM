package com._520it.crm.query;

import com._520it.crm.domain.Employee;
import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

/**
 * Created on 2017/11/10.
 *
 * @author NO_ONE
 *         描述:
 */
@Setter@Getter
public class StudentFollowQueryObject extends QueryObject{
    private String keyword;

    private Long currentStaffId =((Employee) SecurityUtils.getSubject().getPrincipal()).getId(); //当前员工Id  :用来控制只能查看属于自己的潜在学员

    public String getKeyword(){
        return StringUtils.isEmpty(keyword)?null:keyword;
    }
}
/**
 * 错误 :
 * 总结 :
 */