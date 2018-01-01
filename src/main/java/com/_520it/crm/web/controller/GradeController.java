package com._520it.crm.web.controller;

import com._520it.crm.domain.Grade;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.GradeQueryObject;
import com._520it.crm.service.IGradeService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;

    @RequiresPermissions("grade:index")
    @PermissionName("班级列表")
    @RequestMapping("")
    public String index(){
        return "grade";
    }

    @RequiresPermissions("grade:list")
    @PermissionName("班级数据")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(GradeQueryObject qo){
        PageResult result = gradeService.queryPage(qo);
        return result;
    }

    @RequiresPermissions("grade:save")
    @RequestMapping("/save")
    @ResponseBody
    @PermissionName("班级新增")
    public AjaxResult save(Grade grade){
        AjaxResult result = null;
        try{
            gradeService.insert(grade);
            result = new AjaxResult(true,"保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员!");
        }
        return result;
    }

    @RequiresPermissions("grade:update")
    @PermissionName("班级更新")
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Grade grade){
        AjaxResult result = null;
        try{
            gradeService.updateByPrimaryKey(grade);
            result = new AjaxResult(true,"更新成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员!");
        }
        return result;
    }

    @RequiresPermissions("grade:delete")
    @PermissionName("班级删除")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try{
            gradeService.deleteByPrimaryKey(id);
            result = new AjaxResult(true,"删除成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员!");
        }
        return result;
    }


    @RequiresPermissions("grade:graduate")
    @PermissionName("班级结业")
    @RequestMapping("/graduate")
    @ResponseBody
    public AjaxResult graduate(Long id){
        AjaxResult result = null;
        try{
            gradeService.graduate(id);
            result = new AjaxResult(true,"结业成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("办理失败,请联系管理员!");
        }
        return result;
    }

    //分配班主任
    @RequiresPermissions("grade:allot")
    @PermissionName("分配班主任")
    @RequestMapping("/allot")
    @ResponseBody
    public AjaxResult allot(Grade grade){
        AjaxResult result = null;
        try{
            gradeService.allot(grade);
            result = new AjaxResult(true,"分配成功!");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("失败,请联系管理员!");
        }
        return result;
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public List<Grade> listAll(){
        List<Grade> result = gradeService.selectAll();
        return result;
    }

    //为编辑课程查询用;
    @RequiresPermissions("grade:list")
    @PermissionName("班级数据")
    @RequestMapping("/queryForListByCourse")
    @ResponseBody
    public List<Grade> queryForListByCourse(){
        List<Grade> result = gradeService.selectAll();
        return result;
    }
}
