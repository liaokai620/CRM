package com._520it.crm.web.controller;

import com._520it.crm.domain.Department;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("")
    @RequiresPermissions("department:index")
    @PermissionName("部门列表")
    public String index(){
        return "department";
    }

    @RequestMapping("/selectListForEmployeeForm")
    @ResponseBody
    public List<Department> selectListForEmployeeForm() {
        List<Department> result = departmentService.selectAll();
        return result;
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("department:index")
    @PermissionName("部门数据")
    public PageResult list(QueryObject qo){
        PageResult result = departmentService.queryPage(qo);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("department:save")
    @PermissionName("部门保存")
    public AjaxResult save(Department department){
        AjaxResult result = null;
        try {
            departmentService.save(department);
            result = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("department:update")
    @PermissionName("部门更新")
    public AjaxResult update(Department department){
        AjaxResult result = null;
        try {
            departmentService.update(department);
            result = new AjaxResult("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("department:delete")
    @PermissionName("部门删除")
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try {
            departmentService.delete(id);
            result = new AjaxResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }


}
