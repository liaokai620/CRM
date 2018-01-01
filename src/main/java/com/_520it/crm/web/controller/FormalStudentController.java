package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Payment;
import com._520it.crm.domain.Student;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.FormalStudentQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IFormalStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/formalStudent")
public class FormalStudentController {

    @Autowired
    private IFormalStudentService formalStudentService;

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("")
    public String index(){
        return "/formalStudent";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(FormalStudentQueryObject qo){
        PageResult result = formalStudentService.queryPage(qo);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Payment payment){
        AjaxResult result = null;
        try {
            formalStudentService.update(payment);
            result = new AjaxResult("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping("/queryFormalStudent")
    @ResponseBody
    public List<Student> queryStudentForExpense(){
        List<Student> result = formalStudentService.queryFormalStudent();
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try {
            formalStudentService.delete(id);
            result = new AjaxResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping("/turn")
    @ResponseBody
    public AjaxResult turn(Payment payment){
        AjaxResult result = null;
        try {
            formalStudentService.turn(payment);
            result = new AjaxResult("转班成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping("/disappear")
    @ResponseBody
    public AjaxResult checkout(Long id, String username, String password){
        AjaxResult result = null;
        try {
            formalStudentService.disappear(id);
            Employee user = employeeService.checkout(username,password);
            if(user != null){
                result = new AjaxResult("ok");
            }else{
                result = new AjaxResult(false,"密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
