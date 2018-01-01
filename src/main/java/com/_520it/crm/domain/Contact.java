package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

//学校联系人
@Setter@Getter
public class Contact {
    private Long id;
    private String name;
    private String tel;
    private String email;
    //是否是主要联系人
    private Integer main;  // 1是主要联系人
    private String position;
    private Integer gender; //0代表姑娘 1 代表爷们
    private String introduction;
    //表单上的学校下拉列表
    private School school;
}