package com._520it.crm.web.controller;

import com._520it.crm.domain.Attendance;
import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Queryholiday;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.AttendanceQueryObject;
import com._520it.crm.query.QueryHolidayQueryObject;
import com._520it.crm.service.IAttendanceService;
import com._520it.crm.service.IQueryholidayService;
import com._520it.crm.util.PermissionName;
import com._520it.crm.util.UploadUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
@Controller
@RequestMapping("queryholiday")
public class QueryholidayController {

    @Autowired
    private IQueryholidayService queryholidayService;
    @Autowired
    private ServletContext     servletContext;


    @RequiresPermissions("queryholiday:index")
    @PermissionName("请假条列表")
    @RequestMapping("")
    public String index(){
        System.out.println("in......."+queryholidayService);
        return "queryholiday";
    }
    @RequiresPermissions("queryholiday:list")
    @PermissionName("请假条数据")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(QueryHolidayQueryObject qo){
        PageResult result = queryholidayService.queryPage(qo);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Queryholiday queryholiday){
        AjaxResult result = null;
        try{
            queryholidayService.insert(queryholiday);
            result = new AjaxResult(true,"保存成功!");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员!");
        }
        return result;
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Queryholiday queryholiday){
        AjaxResult result = null;
        try{
            queryholidayService.updateByPrimaryKey(queryholiday);
            result = new AjaxResult(true,"更新成功!");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员!");
        }
        return result;
    }
    @RequestMapping("/quit")
    @ResponseBody
    public AjaxResult quit(Long id){
        AjaxResult result = null;
        try{
            queryholidayService.quit(id);
            result = new AjaxResult(true,"确认成功!");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("确认失败,请联系管理员!");
        }
        return result;
    }

    @RequestMapping("inQueryHoliday")
    @ResponseBody
    public AjaxResult inQueryHoliday(MultipartFile file)
            throws Exception{
        AjaxResult ajaxResult =null;
        try {
            if(file.getSize()>0){
                queryholidayService.insertExl(file,servletContext.getRealPath("/uploads"));
            }
            ajaxResult = new AjaxResult("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }

}
