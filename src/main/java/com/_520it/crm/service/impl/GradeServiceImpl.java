package com._520it.crm.service.impl;

import com._520it.crm.domain.Grade;
import com._520it.crm.mapper.GradeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.GradeQueryObject;
import com._520it.crm.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService{

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        gradeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Grade record) {

        gradeMapper.insert(record);
    }

    @Override
    public Grade selectByPrimaryKey(Long id) {
        return gradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Grade record) {
        gradeMapper.updateByPrimaryKey(record);
    }

    @Override
    public void graduate(Long id) {
        gradeMapper.gradeMapper(id);
    }

    //分配班主任
    @Override
    public void allot(Grade grade) {
        gradeMapper.allot(grade);
    }

    @Override
    public PageResult queryPage(GradeQueryObject qo) {
        Long count = gradeMapper.queryForCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }

        List<Grade> list = gradeMapper.queryForList(qo);

        return new PageResult(count, list);

    }
}
