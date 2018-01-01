package com._520it.crm.web.controller;

import com._520it.crm.domain.Queryholiday;
import com._520it.crm.domain.Salary;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.query.SalaryQueryObject;
import com._520it.crm.service.IIncomeService;
import com._520it.crm.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @RequestMapping("")
    public String index() {
        return "salary";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(SalaryQueryObject qo) {
        PageResult pageResult = null;
        pageResult = salaryService.queryPage(qo);
        return pageResult;
    }


    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Salary salary) {
        AjaxResult result = null;
        try {
            int i = salaryService.insert(salary);
            if (i == -1) {
                Salary salary1 = salaryService.selectByPrimaryKey(salary.getEmployee().getId());

                result = new AjaxResult(false, "这个月" + salary1.getEmployee().getRealname() + "员工已经新增过一次工资单了,如果需要修改请点编辑按钮");
            } else {

                result = new AjaxResult(true, "保存成功!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员!");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Salary salary) {
        AjaxResult result = null;
        try {
            salaryService.updateByPrimaryKey(salary);
            result = new AjaxResult(true, "更新成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员!");
        }
        return result;
    }

    @RequestMapping("/quit")
    @ResponseBody
    public AjaxResult quit(Long id) {
        AjaxResult result = null;
        try {
            salaryService.quit(id);
            result = new AjaxResult(true, "确认成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("确认失败,请联系管理员!");
        }
        return result;
    }
}
