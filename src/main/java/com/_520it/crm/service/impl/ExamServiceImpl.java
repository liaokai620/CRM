package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Exam;
import com._520it.crm.mapper.ExamMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IExamService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements IExamService {
	@Autowired
	private ExamMapper examMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return examMapper.deleteByPrimaryKey(id);
	}

	public int insert(Exam record) {
		record.setExamTime(new Date());
		record.setSalesman((Employee)SecurityUtils.getSubject().getPrincipal());
		return examMapper.insert(record);
	}

	public Exam selectByPrimaryKey(Long id) {
		return examMapper.selectByPrimaryKey(id);
	}

	public List<Exam> selectAll() {
		return examMapper.selectAll();
	}

	public int updateByPrimaryKey(Exam record) {
		return examMapper.updateByPrimaryKey(record);
	}

	@Override
	public void result(String result,long id) {
		Employee current = (Employee) SecurityUtils.getSubject().getPrincipal();
		examMapper.result(result,id,current.getId());
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = examMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Exam> result = examMapper.queryList(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
