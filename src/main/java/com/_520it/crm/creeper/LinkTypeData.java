package com._520it.crm.creeper;

import lombok.Getter;
import lombok.Setter;

/**
 * Created on 2017/11/13.
 *
 * @author NO_ONE
 *         描述: 需要的数据对象   目前只需要链接
 */
@Setter
@Getter
public class LinkTypeData {
    private Long id;

    /**
     * 链接地址
     */
    private String linkHref;

    /**
     * 链接标题
     */
    private String  linkText;
    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;
}
/**
 * 错误 :
 * 总结 :
 */