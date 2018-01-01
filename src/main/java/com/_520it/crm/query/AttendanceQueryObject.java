package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter
public class AttendanceQueryObject extends QueryObject {
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	private Integer state;

	public Date getEndDate() {
		return endDate != null ? DateUtil.getEndDate(endDate) : null;
	}
}
