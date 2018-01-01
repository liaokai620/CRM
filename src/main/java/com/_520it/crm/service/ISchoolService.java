package com._520it.crm.service;

import com._520it.crm.domain.School;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SchoolQueryObject;

import java.util.List;

/**
 * Created by joker on 2017/11/10.
 */
public interface ISchoolService {

    int deleteByPrimaryKey(Long id);

    int insert(School record);

    School selectByPrimaryKey(Long id);

    List<School> selectAll();

    int updateByPrimaryKey(School record);
    //分页
    PageResult query(SchoolQueryObject qo);
}
