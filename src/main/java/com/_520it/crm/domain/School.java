package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Setter@Getter
public class School {
    /************客户信息***************/
    private Long id;
    private String name;
    private String shortname;
    /************学校详细信息***************/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    private String address;
    private String tel;
    private Integer studentnumber;
    private Integer teachernumber;
    private Integer employeenumber;
    private String introduction; // 简介
    private Long academicid;//学制

    //需要使用内联
    private Long deptId; //所属部门  模拟数据
    private Employee maket; //营销人员

    private SystemDictionaryItem star; //星级
    private SystemDictionaryItem nature; //学校性质  模拟数据

}
