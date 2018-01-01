package com._520it.crm.mapper;

import com._520it.crm.domain.Queryholiday;
import com._520it.crm.query.AttendanceQueryObject;
import com._520it.crm.query.QueryHolidayQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryholidayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Queryholiday record);

    Queryholiday selectByPrimaryKey(Long id);

    List<Queryholiday> selectAll();

    int updateByPrimaryKey(Queryholiday record);

    Long count(QueryHolidayQueryObject qo);

    List<?> queryForList(QueryHolidayQueryObject qo);

    void changeState(@Param("queryHolidayId")Long id, @Param("auditor")String auditor,@Param("queryHolidayState")int queryHolidayStateQuit);

    List<Queryholiday> selectbyPrimaryEmployee(Long queryHolidayId);
}