package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter
public class CourseQueryObject extends QueryObject {

	private Long gradeId;  //班级id

	private Long teacherId;  // 老师id

	private Long classroomId;  // 教室id


	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date beginTime;  //起始时间

	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date endTime;    //结束时间


	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date newDate;    //选择当天的事假

}
