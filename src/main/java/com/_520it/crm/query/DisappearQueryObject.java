package com._520it.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class DisappearQueryObject extends QueryObject {
    private String  keyword;     //关键字查询
    private Long    studentId;     //根据学生
    private Integer toclassdate;       //上课天数
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    disappeartime;     //流失时间
    private Long    salesmanId;     //营销人员
}
