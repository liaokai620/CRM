package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.SendCode;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
    private IEmployeeService employeeService;

	//主页布局
	@RequestMapping("/main")
	public String main(){
		return "main";
	}


	//主页显示的首页布局
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	//做测试bootstrap框架用
	@RequestMapping("/test")
	public String test(){
		return "test";
	}


	//主页显示的首页布局
	@RequestMapping("/signIn")
	public String signIn(){
		return "signIn";
	}


	//跳转未开发提示的页面
	@RequestMapping("/undeveloped")
	public String undeveloped(){
		return "undeveloped";
	}


	//跳转未开发提示的页面
	@RequestMapping("/uploadOK")
	public String uploadOK(){
		return "uploadOK";
	}


	//跳转聊天室页面
	@RequestMapping("/chat")
	public String chat(){
		return "chat";
	}




    @RequestMapping("/password")
    public String password() {
        return "password";
    }


    @RequestMapping("/changePassword")
    @ResponseBody
    public String  changePassword(String phone) {
        try {
            //发送短信进行验证
            return SendCode.code(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/change")
    @ResponseBody
    public AjaxResult change(String password) {
        try {
            //进行修改密码的操作
            employeeService.changePassword(((Employee) SecurityUtils.getSubject().getPrincipal()).getId(), password);
            return new AjaxResult("修改成功,请重新登录");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult( false,"修改失败" + e.getMessage());
        }

    }

}
