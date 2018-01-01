package com._520it.crm.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2017/11/8.
 *
 * @author NO_ONE
 *         描述:
 */
@ControllerAdvice//对所有的控制器做增强操作
public class BaseController {

    @ExceptionHandler(UnauthorizedException.class) //需要处理那些异常
    public void HandlerException (HandlerMethod handlerMethod, HttpServletResponse response)throws Exception{

        response.setCharacterEncoding("utf-8");
        if(handlerMethod.getMethodAnnotation(ResponseBody.class)==null){
            //返回noPermission页面
            response.sendRedirect("/nopermission.jsp");
        }else{
            //{"success":false,"msg":"您没有权限",rows:[]}
            String msg="{\"success\":false,\"msg\":您没有权限\",rows:[]}";
            response.getWriter().write(msg);
        }
    }
}

/**
 * 错误 :
 * 总结 :
 */