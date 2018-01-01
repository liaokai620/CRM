package com._520it.crm.service.impl;

import com._520it.crm.domain.School;
import com._520it.crm.mapper.SchoolMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SchoolQueryObject;
import com._520it.crm.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by joker on 2017/11/10.
 */
@Service
public class SchoolService implements ISchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return schoolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(School record) {
        return schoolMapper.insert(record);
    }

    @Override
    public School selectByPrimaryKey(Long id) {
        return schoolMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<School> selectAll() {
        return schoolMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(School record) {
        return schoolMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(SchoolQueryObject qo) {
        int rows = schoolMapper.queryForCount(qo);
           if (rows == 0) {
                 return new PageResult(0L, Collections.EMPTY_LIST);

           }
        List<School> result = schoolMapper.queryForList(qo);
        return new PageResult((long) rows,result);
    }
}
