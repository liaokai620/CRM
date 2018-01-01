package com._520it.crm.web.controller;

import com._520it.crm.domain.Attendance;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.AttendanceQueryObject;
import com._520it.crm.service.IAttendanceService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
@Controller
@RequestMapping("attendance")
public class AttendanceController {
    
    @Autowired
    private IAttendanceService attendanceService;

    @RequiresPermissions("attendance:index")
    @PermissionName("考勤列表")
    @RequestMapping("")
    public String index(){
        System.out.println("in......."+attendanceService);
        return "attendance";
    }
    @RequiresPermissions("attendance:list")
    @PermissionName("考勤数据")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(AttendanceQueryObject qo){
        PageResult result = attendanceService.queryPage(qo);
        return result;
    }

    @RequiresPermissions("attendance:save")
    @PermissionName("考勤保存")
    @RequestMapping("/save")
    public String save(Attendance attendance){
        AjaxResult result = null;
        try{
            attendanceService.insert(attendance);
        }catch(Exception e){
            e.printStackTrace();
            return "attendanceNO";
        }
        return "attendanceOK";
    }
}
