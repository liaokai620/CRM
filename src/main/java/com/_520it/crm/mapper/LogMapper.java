package com._520it.crm.mapper;

import com._520it.crm.domain.Log;

import java.util.List;

public interface LogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    Log selectByPrimaryKey(Long id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);
}