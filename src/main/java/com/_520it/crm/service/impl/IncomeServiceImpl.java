package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.mapper.IncomeMapper;
import com._520it.crm.mapper.PaymentMapper;
import com._520it.crm.mapper.StudentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IIncomeService;
import com._520it.crm.util.OrderNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IncomeServiceImpl implements IIncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(Income income) {
        Student student = income.getStudent();
        Employee salesman = employeeMapper.selectByStudentId(student.getId());
        income.setSalesman(salesman);

        //获取当前登录用户
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        //设置收款人为当前登录用户
        income.setPayee(currentUser);
        //生成订单号
        OrderNumber instance = OrderNumber.getInstance();
        String number = instance.getNumber();
        System.out.println(number);
        income.setBillnumber(number);
        incomeMapper.insert(income);

        student.setGrade(income.getGrade());

        //收款时保存学生表中的班级信息
        studentMapper.updateGrade(student);

        //收款时保存学员的收费相关信息
        BigDecimal incomeamount = income.getIncomeamount();
        String     incometype   = income.getIncometype();
        Grade      grade        = income.getGrade();
        Payment payment = paymentMapper.selectByStudentId(student.getId());
        payment.setPaymentType(incometype);
        payment.setGrade(grade);
        payment.setEnrolTime(new Date());
        payment.setPrepaid(incomeamount.add(payment.getPrepaid()));
        payment.setArrearage(payment.getTotalAmount().subtract(payment.getPrepaid()));
        if(payment.getPrepaid().compareTo(payment.getTotalAmount()) >= 0){
            payment.setState(Payment.STATE_YES);
        }
        paymentMapper.updateByPrimaryKey(payment);
    }

    @Override
    public void update(Income income) {
        incomeMapper.updateByPrimaryKey(income);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = incomeMapper.queryCount(qo);
        if(count <= 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<?> list = incomeMapper.queryList(qo);
        return new PageResult(count,list);
    }

    @Override
    public Income total() {
        List<Income> incomes = incomeMapper.selectAll();
        BigDecimal totalamount = BigDecimal.ZERO;
        int totalcount = 0;
        for (Income income : incomes) {
            if(income.getIncomeamount() != null){
                totalamount = totalamount.add(income.getIncomeamount());
            }
            totalcount ++;
        }
        Income income = new Income();
        income.setTotalamount(totalamount);
        income.setTotalcount(totalcount);
        return income;
    }

    @Override
    public void recheck(Long id) {
        incomeMapper.updataState(Income.STATE_RECHECK,id);
    }

    @Override
    public void backrecheck(Long id) {
        incomeMapper.updataState(Income.STATE_WAIT,id);
    }

    @Override
    public void audit(Long id) {
        incomeMapper.updataState(Income.STATE_AUDIT,id);
    }

    @Override
    public void delete(Long id) {
        incomeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void audit(Income income) {

    }
}
