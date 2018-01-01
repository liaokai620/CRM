package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/11/9.
 */

//字典目录明细对象
@Setter
@Getter
public class SystemDictionaryItem {

    private Long id; //字典目录明细id

    private SystemDictionary parent; //字典目录

    private String name; //字典明细名称

    private String intro; //字典明细简介

}
