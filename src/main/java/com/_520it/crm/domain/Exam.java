package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created on 2017/11/10.
 *
 * @author NO_ONE
 *         描述:考试成绩
 */
@Setter@Getter
public class Exam {
    private Long    id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date    examTime;
    private String result;
    private String sn;
    private Employee resolver;
    private String name;
    private Employee salesman;
    private String qq;
    private String tel;
    private Grade wantGrade;
}

/**
 * 错误 :
 * 总结 :
 */