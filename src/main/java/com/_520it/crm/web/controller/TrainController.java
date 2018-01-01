package com._520it.crm.web.controller;

import com._520it.crm.domain.Train;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TrainQueryObject;
import com._520it.crm.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by joker on 2017/11/11.
 */
@Controller
@RequestMapping("train")
public class TrainController {
    @Autowired
    ITrainService trainService;


    @RequestMapping("list")
    @ResponseBody
    public PageResult list(TrainQueryObject qo,Long schoolId){
        PageResult pageResult = null;
        qo.setSchoolId(schoolId);
        pageResult = trainService.query(qo);
        return pageResult;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Train train){
        AjaxResult ajaxResult = null;
        try{
            trainService.insert(train);
            ajaxResult = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,"保存失败");
        }
        return ajaxResult;
    }
    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Train train){
        AjaxResult ajaxResult = null;
        try{
            trainService.updateByPrimaryKey(train);
            ajaxResult = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,"保存失败");
        }
        return ajaxResult;
    }
    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Long trainId){
        AjaxResult ajaxResult = null;
        try{
            trainService.deleteByPrimaryKey(trainId);
            ajaxResult = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,"保存失败");
        }
        return ajaxResult;
    }
}
