package com._520it.crm.mapper;

import com._520it.crm.domain.Grade;
import com._520it.crm.query.GradeQueryObject;

import java.util.List;

public interface GradeMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Grade record);

    Grade selectByPrimaryKey(Long id);

    List<Grade> selectAll();

    void updateByPrimaryKey(Grade record);

    Long queryForCount(GradeQueryObject qo);

    List<Grade> queryForList(GradeQueryObject qo);

    /**
     * 分配班主任
     * @param grade
     */
    void allot(Grade grade);

    /**
     * 根据学生id查询班级
     * @param studentId
     * @return
     */
    Grade selectByStudentId(Long studentId);

    /**
     * 给班级办理结业
     * @param id
     */
    void gradeMapper(Long id);
}