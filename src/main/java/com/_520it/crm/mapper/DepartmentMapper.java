package com._520it.crm.mapper;

import com._520it.crm.domain.Department;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    Long queryCount(QueryObject qo);

    List<Department> queryList(QueryObject qo);
}