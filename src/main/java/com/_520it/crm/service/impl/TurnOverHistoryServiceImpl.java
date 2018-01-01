package com._520it.crm.service.impl;

import com._520it.crm.domain.TurnOverHistory;
import com._520it.crm.mapper.TurnOverHistoryMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ITurnOverHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TurnOverHistoryServiceImpl implements ITurnOverHistoryService {
	@Autowired
	private TurnOverHistoryMapper historyMapper;
	

	public int insert(TurnOverHistory record) {
		return historyMapper.insert(record);
	}

	public TurnOverHistory selectByPrimaryKey(Long id) {
		return historyMapper.selectByPrimaryKey(id);
	}

	public List<TurnOverHistory> selectAll() {
		return historyMapper.selectAll();
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = historyMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<TurnOverHistory> result = historyMapper.queryList(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
