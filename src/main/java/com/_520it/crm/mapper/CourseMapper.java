package com._520it.crm.mapper;

import com._520it.crm.domain.Course;
import com._520it.crm.query.CourseQueryObject;

import java.util.List;

public interface CourseMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Course record);

    Course selectByPrimaryKey(Long id);

    List<Course> selectAll();

    void updateByPrimaryKey(Course record);

    Long queryForCount(CourseQueryObject qo);

    List<Course> queryForList(CourseQueryObject qo);

    /**
     * 上传课程表的数据处理
     * @param course
     */
    void insertForm(Course course);

    /**
     * 上传
     */
    void upload();
}