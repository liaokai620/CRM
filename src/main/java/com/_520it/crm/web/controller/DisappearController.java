package com._520it.crm.web.controller;

import com._520it.crm.domain.Disappear;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DisappearQueryObject;
import com._520it.crm.service.IDisappearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/disappear")
public class DisappearController {

    @Autowired
    private IDisappearService disappearService;

    @RequestMapping("")
    public String index() {
        return "disappear";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(DisappearQueryObject qo) {
        PageResult result = disappearService.queryPage(qo);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Disappear disappear) {
        System.out.println(disappear.getRefund());
        AjaxResult result;
        try {
            disappearService.save(disappear);
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
            disappearService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"删除失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Disappear disappear) {
        AjaxResult result;
        try {
            disappearService.update(disappear);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"更新失败");
        }
        return result;
    }



}
