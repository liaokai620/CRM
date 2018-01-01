package com._520it.crm.mapper;

import com._520it.crm.domain.Salary;
import com._520it.crm.query.AttendanceQueryObject;
import com._520it.crm.query.SalaryQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Salary record);

    Salary selectByPrimaryKey(Long id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);

    Long count(SalaryQueryObject qo);

    List<?> queryForList(SalaryQueryObject qo);


    void changeState(@Param("salaryId")Long id, @Param("salaryState")int salaryStateQuit);

    List<Salary> selectByPrimarytime(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("empId")Long empId);
}