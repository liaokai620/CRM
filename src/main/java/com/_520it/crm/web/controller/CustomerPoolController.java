package com._520it.crm.web.controller;

import com._520it.crm.domain.Student;
import com._520it.crm.query.StudentQueryObject;
import com._520it.crm.service.ILatentStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 2017/11/11.
 *
 * @author NO_ONE
 *         描述:潜在客户池
 */
@Controller
@RequestMapping("customerPool")
public class CustomerPoolController {

    @Autowired
    private ILatentStudentService studentService;
    @RequestMapping("")
    public String index(){
        return "customerPool";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Student> list(StudentQueryObject qo){
        return studentService.queryForCustomerPool(qo);
    }
}
/**
 * 错误 :
 * 总结 :
 */