package com._520it.crm.service;

import com._520it.crm.domain.Exam;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IExamService {
	int deleteByPrimaryKey(Long id);
    int insert(Exam exam);
    Exam selectByPrimaryKey(Long id);
    List<Exam> selectAll();
    int updateByPrimaryKey(Exam exam);
	PageResult queryPage(QueryObject qo);

    void result(String result,long id);
}
