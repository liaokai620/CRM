package com._520it.crm.mapper;

import com._520it.crm.domain.FormalStudent;
import com._520it.crm.domain.Income;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface FormalStudentMapper {

    int insert(FormalStudent record);

    FormalStudent selectByPrimaryKey(Long id);

    List<FormalStudent> selectAll();

    int updateByPrimaryKey(FormalStudent record);

    Long queryCount(QueryObject qo);

    List<Income> queryList(QueryObject qo);
}