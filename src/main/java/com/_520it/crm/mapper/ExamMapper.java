package com._520it.crm.mapper;

import com._520it.crm.domain.Exam;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    Exam selectByPrimaryKey(Long id);

    List<Exam> selectAll();

    int updateByPrimaryKey(Exam record);

    Long queryCount(QueryObject qo);

    List<Exam> queryList(QueryObject qo);

    void result(@Param("result")String result,@Param("id") long examId,@Param("auditorId") long auditorId);
}