package com._520it.crm.mapper;

import com._520it.crm.domain.Income;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Income record);

    Income selectByPrimaryKey(Long id);

    List<Income> selectAll();

    int updateByPrimaryKey(Income record);

    Long queryCount(QueryObject qo);

    List<Income> queryList(QueryObject qo);

    /**
     * 根据学生id查询学费
     * @param studentId
     * @return
     */
    Income selectByStudentId(Long studentId);

    void updataState(@Param("state") Integer state, @Param("id") Long id);
}