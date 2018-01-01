package com._520it.crm.service;

import com._520it.crm.domain.Classroom;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassroomQueryObject;

import java.util.List;

public interface IClassroomService {

    void deleteByPrimaryKey(Long id);

    void insert(Classroom record);

    Classroom selectByPrimaryKey(Long id);

    List<Classroom> selectAll();

    void updateByPrimaryKey(Classroom record);

    PageResult queryPage(ClassroomQueryObject qo);

    /**
     * 启用教室
     * @param id
     */
    void useRoom(Long id);
}
