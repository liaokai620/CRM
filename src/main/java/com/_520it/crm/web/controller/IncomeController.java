package com._520it.crm.web.controller;

import com._520it.crm.domain.Income;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.IncomeQueryObject;
import com._520it.crm.service.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IIncomeService incomeService;

    @RequestMapping("")
    public String index() {
        return "income";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(IncomeQueryObject qo) {
        PageResult result = incomeService.queryPage(qo);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Income income) {
        AjaxResult result;
        try {
            incomeService.save(income);
            result = new AjaxResult("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "保存失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result;
        try {
            incomeService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "删除失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Income income) {
        AjaxResult result;
        try {
            incomeService.update(income);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "更新失败");
        }
        return result;
    }

    @RequestMapping("/total")
    @ResponseBody
    public Income total(){
        Income result = incomeService.total();
        return result;
    }

    @RequestMapping("/recheck")
    @ResponseBody
    public AjaxResult recheck(Long id) {
        AjaxResult result;
        try {
            incomeService.recheck(id);
            result = new AjaxResult("复核成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "复核失败");
        }
        return result;
    }

    @RequestMapping("/backrecheck")
    @ResponseBody
    public AjaxResult backrecheck(Long id) {
        AjaxResult result;
        try {
            incomeService.backrecheck(id);
            result = new AjaxResult("取消成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "取消失败");
        }
        return result;
    }

    @RequestMapping("/audit")
    @ResponseBody
    public AjaxResult audit(Long id) {
        AjaxResult result;
        try {
            incomeService.audit(id);
            result = new AjaxResult("审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "审核失败");
        }
        return result;
    }
}
