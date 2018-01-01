package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by joker on 2017/11/9.
 */
@Setter@Getter
public class ContactQueryObject extends QueryObject{
    private String keyword;
    private  String name;
    private Long schoolId;
}
