package com._520it.crm.mapper;

import com._520it.crm.domain.Dayjob;
import com._520it.crm.query.DayJobQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DayjobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dayjob record);

    Dayjob selectByPrimaryKey(Long id);

    List<Dayjob> selectAll();

    int updateByPrimaryKey(Dayjob record);

    Long total(DayJobQueryObject qo);

    List<Dayjob> queryForList(DayJobQueryObject qo);

    void markSuccess(@Param("ids")Long[] ids);

    void markFailed(@Param("ids")Long[] ids);

    void editDescription(Dayjob dayjob);
}