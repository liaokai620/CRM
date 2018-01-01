package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.StudentFollow;
import com._520it.crm.mapper.StudentFollowMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentFollowQueryObject;
import com._520it.crm.service.IStudentFollowService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentFollowServiceImpl implements IStudentFollowService {
	@Autowired
	private StudentFollowMapper followMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return followMapper.deleteByPrimaryKey(id);
	}

	public int insert(StudentFollow follow) {
		follow.setConsultant((Employee)SecurityUtils.getSubject().getPrincipal());
		return followMapper.insert(follow);
	}

	public StudentFollow selectByPrimaryKey(Long id) {
		return followMapper.selectByPrimaryKey(id);
	}

	public List<StudentFollow> selectAll() {
		return followMapper.queryList(new StudentFollowQueryObject());
	}

	public int updateByPrimaryKey(StudentFollow follow) {
		return followMapper.updateByPrimaryKey(follow);
	}


    @Override
    public void audit(StudentFollow follow) {
        //follow.setAuditor((Employee)SecurityUtils.getSubject().getPrincipal());
        follow.setAudit(true);
        followMapper.audit(follow);
    }

    @Override
	public PageResult query(StudentFollowQueryObject qo) {
		Long count = followMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<StudentFollow> result = followMapper.queryList(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
