package com._520it.crm.mapper;

import com._520it.crm.domain.Attendance;
import com._520it.crm.query.AttendanceQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attendance record);

    Attendance selectByPrimaryKey(Long id);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance record);

    Long count(AttendanceQueryObject qo);

    List<?> queryForList(AttendanceQueryObject qo);

    List<Attendance> selectbyPrimaryEmployee(@Param("EmpId") Long EmpId, @Param("startTime") Date startTime,@Param("endTime")  Date endTime);
}