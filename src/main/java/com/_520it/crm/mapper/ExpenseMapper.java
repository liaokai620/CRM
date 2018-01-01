package com._520it.crm.mapper;

import com._520it.crm.domain.Expense;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ExpenseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Expense record);

    Expense selectByPrimaryKey(Long id);

    List<Expense> selectAll();

    int updateByPrimaryKey(Expense record);

    Long queryCount(QueryObject qo);

    List<Expense> queryList(QueryObject qo);

    void audit(Long id);
}