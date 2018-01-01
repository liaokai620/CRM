package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class FormalStudentQueryObject extends QueryObject {
    private String keyword;         //关键字查询
    private Integer stateId;        //付款状态
    private Long studentId;         //学生id
    private Long salesmanId;        //营销人员id
    private String paymentType;     //付款方式

    public String getPaymentType(){
        return StringUtils.isEmpty(paymentType) ? null : paymentType;
    }
}
