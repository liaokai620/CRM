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
 *         描述:学员跟踪
 */
@Setter@Getter
public class StudentFollow {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private Student student;

    private Employee consultant;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nextMeetingTime;

    private String order;

    private  SystemDictionaryItem wantLevel;

    private String qq;

    private String tel;

    private String consultantWay;

    private String digest;

    private Employee auditor;

    private SystemDictionaryItem level;

    private String auditInfo;

    private Boolean audit = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date meetingTime;

    private String content;

    private School school;

    private Contact contact;

    private Integer consultLength;

    private Grade wantClass;

 }
/**
 * 错误 :
 * 总结 :
 */