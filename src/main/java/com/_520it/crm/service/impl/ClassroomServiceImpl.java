package com._520it.crm.service.impl;

import com._520it.crm.domain.Classroom;
import com._520it.crm.mapper.ClassroomMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassroomQueryObject;
import com._520it.crm.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassroomServiceImpl implements IClassroomService{

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        classroomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Classroom record) {

        classroomMapper.insert(record);
    }

    @Override
    public Classroom selectByPrimaryKey(Long id) {
        return classroomMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Classroom> selectAll() {
        return classroomMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Classroom record) {
        classroomMapper.updateByPrimaryKey(record);

    }

    @Override
    public void useRoom(Long id) {
        classroomMapper.useRoom(id);
    }

    @Override
    public PageResult queryPage(ClassroomQueryObject qo) {
        Long count = classroomMapper.queryForCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }

        List<Classroom> list = classroomMapper.queryForList(qo);

        return new PageResult(count, list);

    }
}
