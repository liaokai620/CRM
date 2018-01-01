package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class PromotionQueryObject extends QueryObject {
    private String     keyword;         //关键字查询
    private Long beforegradeId;         //以前班级id
    private Long salesmanId;            //营销人员id

    public String getKeyword(){
        return StringUtils.isEmpty(keyword) ? null : keyword;
    }
}
