package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
public class Student {

    private Long id;//主键

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fileDate;//建档日期

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date meetingTime;//约访时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextFollowTime;//下次跟进时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;//录入时间

    private String wechat;//微信

    private Integer age;//年龄

    private String address;//地址

    private SystemDictionaryItem education;//学历

    private Integer workAge;//工作年限

    private Grade wantClass;//意向班级

    private SystemDictionaryItem clientType;//客户类型

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;//大学入学时间

    private SystemDictionaryItem wantSubject;//意向学科

    private Employee salesman;//销售人员

    private School wantSchool;//意向校区

    private SystemDictionaryItem comeFrom;//来源

    private String qq;//qq

    private SystemDictionaryItem gender;//性别

    private String trainagency;//培训机构

    private String major;//专业

    private SystemDictionaryItem wantLevel;//意向程度

    private Employee otherSalesMan;//其他销售人员

    private String collageClass;//大学班级

    private Employee inputUser;//录入人


    private String name;//姓名

    private String tel;//电话

    private String email;///邮箱

    private SystemDictionaryItem area;//地域

    private School school;//学校客户

    private String sponsor;//介绍人

    private int status;//状态

    private SystemDictionaryItem computer;//笔记本电脑

    private Boolean nopay;//零付款

    private String problemFocus;//关注问题

    private String intro;//备注

    private String IDcard;//身份证

    private String emergencyContact;//紧急联系人

    private String emergencyTel;//紧急联系电话

    private String IDcardCopy;//身份证复印件

    private String degreeCopy;//学位证复印件

    private String employment;//专业

    private String workExperience;//工作经验

    private SystemDictionaryItem foreignLanguageLevel;//外语水平

    private SystemDictionaryItem payType;//付款方式

    private String currentAddr;//当前地址

    private String familyAddr;//家庭地址

    private String houseHoldAddr;//家庭住址

    private String otherLevel;//其他水平

    private Grade grade;//选择班级

    private Boolean turn = false;//是否转正

}