package com._520it.crm.service;

import com._520it.crm.domain.Grade;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.GradeQueryObject;

import java.util.List;

public interface IGradeService {

    void deleteByPrimaryKey(Long id);

    void insert(Grade record);

    Grade selectByPrimaryKey(Long id);

    List<Grade> selectAll();

    void updateByPrimaryKey(Grade record);

    PageResult queryPage(GradeQueryObject qo);

    /**
     * 分配班主任
     * @param grade
     */
    void allot(Grade grade);

    /**
     * 给班级办理结业
     * @param id
     */
    void graduate(Long id);
}
