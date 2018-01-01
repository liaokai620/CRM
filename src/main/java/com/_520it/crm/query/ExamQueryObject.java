package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created on 2017/11/10.
 *
 * @author NO_ONE
 *         描述:
 */
@Setter
@Getter
public class ExamQueryObject extends QueryObject {
    private String keyword;

    public String getKeyword() {
        return com.alibaba.druid.util.StringUtils.isEmpty(keyword) ? null : keyword;
    }
}
/**
 * 错误 :
 * 总结 :
 */