package com._520it.crm.service;

import com._520it.crm.domain.Course;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourseQueryObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ICourseService {

    void deleteByPrimaryKey(Long id);

    void insert(Course record);

    Course selectByPrimaryKey(Long id);

    List<Course> selectAll();

    void updateByPrimaryKey(Course record);

    PageResult queryPage(CourseQueryObject qo);

    /**
     * 下载课程表模板的接口
     * @param req
     * @param resp
     */
    void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    /**
     * 上传课程表的接口
     * @param req
     * @param resp
     */
    void upload(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
