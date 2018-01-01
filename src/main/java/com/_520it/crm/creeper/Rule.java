package com._520it.crm.creeper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 2017/11/13.
 *
 * @author NO_ONE
 *         描述: 用于指定查询URL METHOD params 等
 */
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class Rule {
    public static final int GET = 0;
    public static final int POST = 1;

    public static final int CLASS = 0;
    public static final int ID = 1;
    public static final int SELECTION = 2;


    /**
     * 链接
     */
    private String  url;

    /**
     * 参数集合
     */
    private String[] params;
    /**
     * 采纳数对应值的集合
     */
    private String[] values;

    /**
     * 对返回的HTML 第一次过滤使用的标签,先设置type
     */
    private String resultTagName;

    /**
     * 设置resultTagName 的类型,默认为ID
     *  ID / CLASS / SELECTION
     */
    private int type;

    /**
     * 请求的类型 默认为GET
     *   GET /POST
     */
    private int requestMethod;
}
/**
 * 错误 :
 * 总结 :
 */