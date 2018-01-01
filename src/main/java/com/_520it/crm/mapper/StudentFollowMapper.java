package com._520it.crm.mapper;

import com._520it.crm.domain.StudentFollow;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.StudentFollowQueryObject;

import java.util.List;

public interface StudentFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentFollow follow);

    StudentFollow selectByPrimaryKey(Long id);

    List<StudentFollow> selectAll();

    int updateByPrimaryKey(StudentFollow follow);

    long queryCount(QueryObject qo);

    List<StudentFollow> queryList(StudentFollowQueryObject qo);

    void audit(StudentFollow follow);
}