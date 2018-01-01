package com._520it.crm.query;

import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on 2017/11/11.
 *
 * @author NO_ONE
 *         描述:
 */
@Setter@Getter
public class TurnOverHistoryQueryObject extends QueryObject {
    private String keyword;

    public String getKeyword(){
        return StringUtils.isEmpty(keyword)?null : keyword;
    }
}
/**
 * 错误 :
 * 总结 :
 */