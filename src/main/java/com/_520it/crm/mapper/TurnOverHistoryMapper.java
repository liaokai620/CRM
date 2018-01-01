package com._520it.crm.mapper;

import com._520it.crm.domain.TurnOverHistory;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface TurnOverHistoryMapper {

    int insert(TurnOverHistory history);

    TurnOverHistory selectByPrimaryKey(Long id);

    List<TurnOverHistory> selectAll();


    Long queryCount(QueryObject qo);

    List<TurnOverHistory> queryList(QueryObject qo);
}