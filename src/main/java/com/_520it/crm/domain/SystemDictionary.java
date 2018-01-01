package com._520it.crm.domain;


import lombok.Getter;
import lombok.Setter;

//字典目录对象
@Getter@Setter
public class SystemDictionary {

    private Long id; //字典id

    private String sn;//字典编号

    private String name;//字典名称

    private String intro;//字典简介

}