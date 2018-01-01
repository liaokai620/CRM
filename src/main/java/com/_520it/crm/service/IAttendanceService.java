package com._520it.crm.service;

import com._520it.crm.domain.Attendance;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.AttendanceQueryObject;

import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
public interface IAttendanceService {
    int deleteByPrimaryKey(Long id);

    int insert(Attendance record);

    Attendance selectByPrimaryKey(Long id);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance record);

    PageResult queryPage(AttendanceQueryObject qo);
}
