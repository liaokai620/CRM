package com._520it.crm.web.controller;

import com._520it.crm.domain.Classroom;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassroomQueryObject;
import com._520it.crm.service.IClassroomService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {


    @Autowired
    private IClassroomService classroomService;

    @RequiresPermissions("classroom:index")
    @PermissionName("教室列表")
    @RequestMapping("")
    public String index(){
        return "classroom";
    }

    @RequiresPermissions("classroom:list")
    @PermissionName("教室数据")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(ClassroomQueryObject qo){
        PageResult result = classroomService.queryPage(qo);
        return result;
    }

    @RequiresPermissions("classroom:save")
    @RequestMapping("/save")
    @ResponseBody
    @PermissionName("教室新增")
    public AjaxResult save(Classroom classroom){
        AjaxResult result = null;
        try{
            classroomService.insert(classroom);
            result = new AjaxResult(true,"保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员!");
        }
        return result;
    }

    @RequiresPermissions("classroom:update")
    @PermissionName("教室更新")
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Classroom classroom){
        AjaxResult result = null;
        try{
            classroomService.updateByPrimaryKey(classroom);
            result = new AjaxResult(true,"更新成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员!");
        }
        return result;
    }

    @RequiresPermissions("classroom:delete")
    @PermissionName("教室删除")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try{
            classroomService.deleteByPrimaryKey(id);
            result = new AjaxResult(true,"删除成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员!");
        }
        return result;
    }


    @RequiresPermissions("classroom:useRoom")
    @PermissionName("教室启用")
    @RequestMapping("/useRoom")
    @ResponseBody
    public AjaxResult useRoom(Long id){
        AjaxResult result = null;
        try{
            classroomService.useRoom(id);
            result = new AjaxResult(true,"启用成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("启用失败,请联系管理员!");
        }
        return result;
    }

    //在课程页面填写增加和编辑时需要查询到所有的教室
    @RequestMapping("/queryForListByCourse")
    @ResponseBody
    public List<Classroom> queryForListByCourse(){
        List<Classroom> result = classroomService.selectAll();
        return result;
    }


}
