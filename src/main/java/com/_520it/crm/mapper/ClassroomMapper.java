package com._520it.crm.mapper;

import com._520it.crm.domain.Classroom;
import com._520it.crm.query.ClassroomQueryObject;

import java.util.List;

public interface ClassroomMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Classroom record);

    Classroom selectByPrimaryKey(Long id);

    List<Classroom> selectAll();

    void updateByPrimaryKey(Classroom record);

    Long queryForCount(ClassroomQueryObject qo);

    List<Classroom> queryForList(ClassroomQueryObject qo);

    /**
     * 办理启用教室的业务
     * @param id
     */
    void useRoom(Long id);
}