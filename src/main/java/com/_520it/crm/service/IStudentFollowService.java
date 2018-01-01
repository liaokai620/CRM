package com._520it.crm.service;

import com._520it.crm.domain.StudentFollow;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentFollowQueryObject;

import java.util.List;

public interface IStudentFollowService {
	int deleteByPrimaryKey(Long id);
    int insert(StudentFollow follow);
    StudentFollow selectByPrimaryKey(Long id);
    List<StudentFollow> selectAll();
    int updateByPrimaryKey(StudentFollow follow);
    PageResult query(StudentFollowQueryObject qo);

    void audit(StudentFollow follow);
}
