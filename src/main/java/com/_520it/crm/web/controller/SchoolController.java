package com._520it.crm.web.controller;

import com._520it.crm.domain.School;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SchoolQueryObject;
import com._520it.crm.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by joker on 2017/11/10.
 */
@Controller
@RequestMapping("school")
public class SchoolController {
    @Autowired
    ISchoolService schoolService;

    @RequestMapping("")
    public  String school(){

        return "school";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SchoolQueryObject qo){
        PageResult pageResult = null;
        pageResult = schoolService.query(qo);
        return pageResult;
    }

    //给主要联系人contact提供列表
    @RequestMapping("querySchoolListForContactForm")
    @ResponseBody
    public List<School> querySchoolListForContactForm(){
        return schoolService.selectAll();
    }

    //给学生contact提供列表
    @RequestMapping("listAll")
    @ResponseBody
    public List<School> listAll(){
        return schoolService.selectAll();
    }


    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(School school){
        AjaxResult ajaxResult = null;
        try {
            schoolService.insert(school);
            ajaxResult = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,"保存失败");
        }
        return ajaxResult;
    }
    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(School school){
        AjaxResult ajaxResult = null;
        try {
            schoolService.updateByPrimaryKey(school);
            ajaxResult = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,"保存失败");
        }
        return ajaxResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult ajaxResult = null;
        try {
            schoolService.deleteByPrimaryKey(id);
            ajaxResult = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,"保存失败");
        }
        return ajaxResult;
    }
}
