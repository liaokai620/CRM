package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Dayjob {
    public static final int STATE_INIT    = 0;
    public static final int STATE_FAILD   = -1;
    public static final int STATE_SUCCESS = 1;
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date inputTime;


    private String jobDescription;

    private String dealDescription;

    private int state;

    private Employee handler;
}