package com._520it.crm.web.controller;

import com._520it.crm.domain.Promotion;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PromotionQueryObject;
import com._520it.crm.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    
    @Autowired
    private IPromotionService promotionService;
    
    @RequestMapping("")
    public String index(){
        return "promotion";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(PromotionQueryObject qo) {
        PageResult result = promotionService.queryPage(qo);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Promotion promotion) {
        AjaxResult result;
        try {
            promotionService.save(promotion);
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
            promotionService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"删除失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Promotion promotion) {
        AjaxResult result;
        try {
            promotionService.update(promotion);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false,"更新失败");
        }
        return result;
    }



}
