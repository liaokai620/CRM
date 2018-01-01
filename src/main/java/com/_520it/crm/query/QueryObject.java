package com._520it.crm.query;

import com._520it.crm.domain.Employee;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

/**
 * Created on 2017/11/9.
 *
 * @author NO_ONE
 *         描述:
 */
@Setter
@Getter
public class QueryObject {
	private Integer page = 1;//当前页
	private Integer rows = 10;//每页条数
	public Integer getStart(){
		return (this.page-1)*this.rows;
	}
}

/**
 * 错误 :
 * 总结 :
 */