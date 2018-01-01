package com._520it.crm.web.controller;

import com._520it.crm.domain.Expense;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ExpenseQueryObject;
import com._520it.crm.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private IExpenseService expenseService;

    @RequestMapping("")
    public String index() {
        return "expense";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(ExpenseQueryObject qo) {
        PageResult result = expenseService.queryPage(qo);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Expense expense) {
        AjaxResult result;
        try {
            expenseService.save(expense);
            result = new AjaxResult("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"保存失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result;
        try {
            expenseService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"删除失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Expense expense) {
        AjaxResult result;
        try {
            expenseService.update(expense);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"更新失败");
        }
        return result;
    }

    @RequestMapping("/audit")
    @ResponseBody
    public AjaxResult audit(Long id) {
        AjaxResult result;
        try {
            expenseService.audit(id);
            result = new AjaxResult("审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"审核失败");
        }
        return result;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id) {
        AjaxResult result;
        try {
            expenseService.remove(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"删除失败");
        }
        return result;
    }



}
