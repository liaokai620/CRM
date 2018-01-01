package com._520it.crm.query;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Student;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter
public class StudentQueryObject extends QueryObject {
    private String keyword;
    private Long currentUserId = ((Employee) SecurityUtils.getSubject().getPrincipal()).getId();
    private Student student;

    //快速查询
    private Long queryId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryTime = new Date();

    public String getKeyword(){
        return StringUtils.hasLength(keyword)? keyword:null;
    }
}
