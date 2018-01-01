package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

//教室信息封装类
@Getter@Setter
public class Classroom {

    public static final int NORMAL = 0;//已启用
    public static final int LEAVE = 1;//未启用

    private Long id;

    private String name;

    private String stite; //教室地址

    private int seatNumber;  //座位数量

    private int state;   //状态;

}