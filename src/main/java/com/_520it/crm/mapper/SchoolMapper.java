package com._520it.crm.mapper;

import com._520it.crm.domain.School;
import com._520it.crm.query.SchoolQueryObject;

import java.util.List;

public interface SchoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(School record);

    School selectByPrimaryKey(Long id);

    List<School> selectAll();

    int updateByPrimaryKey(School record);

    int queryForCount(SchoolQueryObject qo);

    List<School> queryForList(SchoolQueryObject qo);
}