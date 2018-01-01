package com._520it.crm.mapper;

import com._520it.crm.domain.Disappear;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface DisappearMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Disappear record);

    Disappear selectByPrimaryKey(Long id);

    List<Disappear> selectAll();

    int updateByPrimaryKey(Disappear record);

    Long queryCount(QueryObject qo);

    List<Disappear> queryList(QueryObject qo);

}