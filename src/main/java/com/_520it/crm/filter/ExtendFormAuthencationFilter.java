package com._520it.crm.filter;

import com._520it.crm.service.ISystemMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 2017/11/8.
 *
 * @author NO_ONE
 *         描述:
 */
@Component
public class ExtendFormAuthencationFilter extends FormAuthenticationFilter {

    @Autowired
    private ISystemMenuService systemMenuService;
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //登录成功响应JSON数据回去--->{success:true,msg:'登录成功'}
        //response.getWriter().wirte("{success:true,msg:'登录成功'}".getBytes());
        HttpServletRequest  req  = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        response.setContentType("text/html;charset=UTF-8");
        String msg = " {\"success\":true,\"msg\":\"登录成功\"}";
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(msg);
        SecurityUtils.getSubject().getSession().setAttribute("userMenu", systemMenuService.indexMenu());
        resp.sendRedirect("/main");
        return false;
    }

    @ResponseBody
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        //登录失败响应JSON数据回去----->{success:false,msg:''}
        //response.getWriter().wirte("{success:false,msg:''};
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号不存在\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号被锁定\"}");
            }else if("AuthenticationException".equals(message)){
                out.println("{\"success\":false,\"msg\":\"认证失败\"}");
            }else {
                out.println("{\"success\":false,\"msg\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
/**
 * 错误 :
 * 总结 :
 */