package com._520it.crm.service.impl;

import com._520it.crm.domain.Course;
import com._520it.crm.mapper.CourseMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourseQueryObject;
import com._520it.crm.service.ICourseService;
import com._520it.crm.util.FileUploadUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Course record) {
        List<Course> courses = courseMapper.selectAll();
        for (Course cours : courses) {

        courseMapper.insert(cours);
        }
    }

    @Override
    public Course selectByPrimaryKey(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Course record) {
        courseMapper.updateByPrimaryKey(record);
    }

    //上传课程表
    @Override
    public void upload(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        FileUploadUtil.upload(req,"course");
        for(int i= 0;i<=12;i++){
        courseMapper.upload();
        }
        resp.sendRedirect("/uploadOK");
    }

    //下载模板
    @Override
    public void download(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        //获取网页点击获取具体的文件
        String filename = "course.xls";

        //设置获取的编码为UTF-8
        filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");

        //获取文件的路径；
        String path = req.getSession().getServletContext().getRealPath("/WEB-INF/download");

        //创建路径对象
        File file = new File(path, filename);

        //非IE浏览器设置
        filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");

        //设置文件下载名称
        resp.setHeader("Content-Disposition", "attachmen;filename=course.xls");

        //获取输出到浏览器的流，输出流
        OutputStream out = resp.getOutputStream();
        IOUtils.copy(new FileInputStream(file), out);
    }

    @Override
    public PageResult queryPage(CourseQueryObject qo) {
        Long count = courseMapper.queryForCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }

        List<Course> list = courseMapper.queryForList(qo);

        return new PageResult(count, list);

    }
}
