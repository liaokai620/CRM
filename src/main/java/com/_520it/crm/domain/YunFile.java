package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class YunFile {
    private Long id;

    private String name;

    private Employee uploader;

    private Date inputTime;

}