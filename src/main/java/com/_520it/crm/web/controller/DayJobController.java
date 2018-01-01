package com._520it.crm.web.controller;

import com._520it.crm.domain.Dayjob;
import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DayJobQueryObject;
import com._520it.crm.service.IDayJobService;
import com._520it.crm.service.IEmployeeService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author MrHou
 * @date 2017/11/9.
 */
@Controller
@RequestMapping("/dayJob")
public class DayJobController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDayJobService   dayJobService;

    @RequestMapping("")
    public String index() {
        return "dayJob";
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("dayJob:list")
    public PageResult list( DayJobQueryObject qo) {
        PageResult result = dayJobService.result(qo);
        return result;
    }

    @RequestMapping("/checkEmployeeForForm")
    @ResponseBody
    public List<Employee> checkEmployeeForForm() {
        List<Employee> result = null;
        result = employeeService.selectAll();
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("dayJob:save")
    public AjaxResult save(Dayjob dayjob) {
        AjaxResult result = null;
        try {
            dayJobService.insert(dayjob);
            result = new AjaxResult(true,"保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("dayJob:update")
    public AjaxResult update(Dayjob dayjob) {
        AjaxResult result = null;
        try {
            dayJobService.updateByPrimaryKey(dayjob);
            result = new AjaxResult(true,"保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("dayJob:delete")
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {
            dayJobService.deleteByPrimaryKey(id);
            result = new AjaxResult(true,"删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
    @RequestMapping("/markSuccess")
    @ResponseBody
    @RequiresPermissions("dayJob:markSuccess")
    public AjaxResult markSuccess(@RequestParam("ids[]") Long[] ids) {
        AjaxResult result = null;
        try {
            dayJobService.markSuccess(ids);
            result = new AjaxResult(true,"标记完成!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
    @RequestMapping("/markFailed")
    @ResponseBody
    @RequiresPermissions("dayJob:markFailed")
    public AjaxResult markFailed(@RequestParam("ids[]") Long[] ids) {
        AjaxResult result = null;
        try {
            dayJobService.markFailed(ids);
            result = new AjaxResult(true,"标记失败!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
    @RequestMapping("/editDescription")
    @ResponseBody
    @RequiresPermissions("dayJob:editDescription")
    public AjaxResult editDescription(Dayjob dayjob) {
        AjaxResult result = null;
        try {
            dayJobService.editDescription(dayjob);
            result = new AjaxResult(true,"修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

}
