package com._520it.crm.web.controller;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.domain.SystemDictionaryItem;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SystemDictionaryQueryObject;
import com._520it.crm.service.ISystemDictionaryItemService;
import com._520it.crm.service.ISystemDictionaryService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

@Controller
@RequestMapping("/dictionary")
public class SystemDictionaryController {

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    private ISystemDictionaryItemService itemService;

    @RequestMapping("")
    @RequiresPermissions("dictionary:index")
    @PermissionName("数据字典首页")
    public String index(){

        return "systemDictionary";
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("dictionary:list")
    @PermissionName("数据字典数据")
    public PageResult list(SystemDictionaryQueryObject qo){
        PageResult result = null;
        result =systemDictionaryService.queryPage(qo);
        return result;
    }
    @RequestMapping("/listItemByParentId")
    @ResponseBody
    public List<SystemDictionaryItem> listItem( Long parentId){

        List<SystemDictionaryItem> result = null;

        result =itemService.listItemByParentId(parentId);

        return result;
    }


    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("dictionary:save")
    @PermissionName("数据字典新增")
    public AjaxResult save(SystemDictionary sd){
        AjaxResult result = null;
        try{
            systemDictionaryService.save(sd);
            result = new AjaxResult("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"保存失败");
        }
        return result;
    }
    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions("dictionary:edit")
    @PermissionName("数据字典编辑")
    public AjaxResult edit(SystemDictionary sd){
        AjaxResult result = null;
        try{
            systemDictionaryService.edit(sd);
            result = new AjaxResult("编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"编辑失败");
        }
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("dictionary:delete")
    @PermissionName("数据字典删除")
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try{
            systemDictionaryService.delete(id);
            result = new AjaxResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"删除失败");
        }
        return result;
    }
    @RequestMapping("/saveItem")
    @ResponseBody
    public AjaxResult saveItem(SystemDictionaryItem item){
        AjaxResult result = null;
        try{
            itemService.saveItem(item);
            result = new AjaxResult("新增成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"新增失败");
        }
        return result;
    }
    @RequestMapping("/editItem")
    @ResponseBody
    public AjaxResult editItem(SystemDictionaryItem item){
        AjaxResult result = null;
        try{
            itemService.editItem(item);
            result = new AjaxResult("编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"编辑失败");
        }
        return result;
    }
    @RequestMapping("/deleteItem")
    @ResponseBody
    public AjaxResult editItem(Long id){
        AjaxResult result = null;
        try{
            itemService.deleteItem(id);
            result = new AjaxResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"删除失败");
        }
        return result;
    }

    //学校模块查询星级
    @RequestMapping("/queryStarForSchool")
    @ResponseBody
    public List<SystemDictionaryItem> queryStarForSchool(){

        List<SystemDictionaryItem> result = null;

        result =itemService.queryStarForSchool();

        return result;
    }
    //学校模块查询办学性质
    @RequestMapping("/queryNatureForSchool")
    @ResponseBody
    public List<SystemDictionaryItem> queryNatureForSchool(){

        List<SystemDictionaryItem> result = null;

        result =itemService.queryNatureForSchool();

        return result;
    }



}
