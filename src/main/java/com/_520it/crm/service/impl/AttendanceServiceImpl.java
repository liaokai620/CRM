package com._520it.crm.service.impl;

import com._520it.crm.domain.Attendance;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.AttendanceMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.AttendanceQueryObject;
import com._520it.crm.service.IAttendanceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return attendanceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Attendance record) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        Date date = new Date();
        record.setWorktime("09:00:00");
        record.setClocktime(date);
        Calendar cal = Calendar.getInstance();//使用日历类
        cal.setTime(date);
        int hour = cal.get(cal.HOUR_OF_DAY);//获得时
        int minute = cal.get(cal.MINUTE);//获得分
        int second = cal.get(cal.SECOND);//获得秒
        if (hour > 9) {
            record.setState(1);
        } else {
            record.setState(0);
        }
        record.setEmployee(employee);
        return attendanceMapper.insert(record);
    }

    @Override
    public Attendance selectByPrimaryKey(Long id) {
        return attendanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Attendance> selectAll() {
        return attendanceMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Attendance record) {
        return attendanceMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(AttendanceQueryObject qo) {
        Long count = attendanceMapper.count(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<?> results = attendanceMapper.queryForList(qo);
        return new PageResult(count, results);
    }
}
