package com._520it.crm.web.controller;

import com._520it.crm.domain.Contact;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ContactQueryObject;
import com._520it.crm.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by joker on 2017/11/9.
 */
@Controller
@RequestMapping("contact")
public class ContactController {

    @Autowired
    IContactService contactService;

    @RequestMapping("")
    public String contact(){
        //跳转到contact.jsp
        return "contact";
    }

    //主要联系人的列表页面
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ContactQueryObject qo){
        PageResult pageResult = null;
        pageResult = contactService.query(qo);
        return pageResult;
    }

    @RequestMapping("listAll")
    @ResponseBody
    public List<Contact> listAll(){
        return contactService.selectAll();
    }



    //表单保存
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Contact contact){
        AjaxResult ajaxResult = null;

        try {
            contactService.insert(contact);
            ajaxResult = new AjaxResult(true,"保存成功");
        }catch (Exception e){
            e.printStackTrace();;
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }
    //表单编辑
    @RequestMapping("edit")
    @ResponseBody
    public AjaxResult edit(Contact contact){
        AjaxResult ajaxResult = null;
        try {
            contactService.updateByPrimaryKey(contact);
            ajaxResult = new AjaxResult(true,"编辑成功");
        }catch (Exception e){
            e.printStackTrace();;
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }
    //表单删除
    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult ajaxResult = null;
        try {
            contactService.deleteByPrimaryKey(id);
            ajaxResult = new AjaxResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();;
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }
}