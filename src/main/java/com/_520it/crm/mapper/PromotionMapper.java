package com._520it.crm.mapper;

import com._520it.crm.domain.Promotion;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PromotionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Promotion record);

    Promotion selectByPrimaryKey(Long id);

    List<Promotion> selectAll();

    int updateByPrimaryKey(Promotion record);

    Long queryCount(QueryObject qo);

    List<Promotion> queryList(QueryObject qo);

}