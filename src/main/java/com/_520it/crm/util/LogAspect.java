package com._520it.crm.util;

import com._520it.crm.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lyhzzz
 * @date 2017/11/7
 */
@Component
public class LogAspect {

    @Autowired
    private ILogService service;

    public void writeLog(JoinPoint joinPoint){
//        System.out.println("保存日志......");
//        Employee current = (Employee) SecurityUtils.getSubject().getPrincipal();
//        Log      log     = new Log();
//        log.setOpTime(new Date());
//        log.setOpUser(current);
//        String function=joinPoint.getTarget().getClass().getName()+":"+joinPoint.getSignature().getName();
//        log.setFunction(function);
//        log.setParams(JSON.toJSONString(joinPoint.getArgs()));
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        log.setOpIp(request.getRemoteAddr());
//        service.save(log);
    }
}
