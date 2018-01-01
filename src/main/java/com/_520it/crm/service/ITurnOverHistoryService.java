package com._520it.crm.service;

import com._520it.crm.domain.TurnOverHistory;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ITurnOverHistoryService {
    int insert(TurnOverHistory history);
    TurnOverHistory selectByPrimaryKey(Long id);
    List<TurnOverHistory> selectAll();
	PageResult queryPage(QueryObject qo);
}
