package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Employee {
    public static final int NORMAL = 0;//正常状态
    public static final int LEAVE  = 1;//表示离职装填
    private Long       id;
    private String     username;
    private String     realname;
    private String     password;
    private String     tel;
    private String     email;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date       inputtime;
    private int        state;
    private boolean    admin;
    private Department dept;
    private List<Role> roles = new ArrayList<Role>();
}