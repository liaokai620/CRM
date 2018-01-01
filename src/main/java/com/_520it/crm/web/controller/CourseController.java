package com._520it.crm.web.controller;

import com._520it.crm.domain.Course;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourseQueryObject;
import com._520it.crm.service.ICourseService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {


    @Autowired
    private ICourseService courseService;

    @RequiresPermissions("course:index")
    @PermissionName("课程列表")
    @RequestMapping("")
    public String index(){
        return "course";
    }

    @RequiresPermissions("course:list")
    @PermissionName("课程数据")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CourseQueryObject qo){
        PageResult result = courseService.queryPage(qo);
        return result;
    }

    @RequiresPermissions("course:save")
    @RequestMapping("/save")
    @ResponseBody
    @PermissionName("课程新增")
    public AjaxResult save(Course course){
        AjaxResult result = null;
        try{
            courseService.insert(course);
            result = new AjaxResult(true,"保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员!");
        }
        return result;
    }

    //课程表模板下载
    @RequiresPermissions("course:download")
    @RequestMapping("/download")
    @PermissionName("课程表下载")
    public void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        courseService.download(req,resp);

    }

    //课程表上传
    @RequiresPermissions("course:upload")
    @RequestMapping("/upload")
    @PermissionName("课程表上传")
    @ResponseBody
    public void upload(HttpServletRequest req, HttpServletResponse resp) {
        try {
            courseService.upload(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresPermissions("course:update")
    @PermissionName("课程更新")
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Course course){
        AjaxResult result = null;
        try{
            courseService.updateByPrimaryKey(course);
            result = new AjaxResult(true,"更新成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员!");
        }
        return result;
    }

    @RequiresPermissions("course:delete")
    @PermissionName("课程删除")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try{
            courseService.deleteByPrimaryKey(id);
            result = new AjaxResult(true,"删除成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员!");
        }
        return result;
    }

    //班级页面需要查询获取到所有课程信息;
    @RequestMapping("/queryListFromGrade")
    @ResponseBody
    public List<Course> queryListFromGrade(){
        List<Course> result = courseService.selectAll();
        return result;
    }



}
