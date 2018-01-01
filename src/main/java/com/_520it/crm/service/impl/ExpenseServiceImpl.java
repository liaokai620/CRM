package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Expense;
import com._520it.crm.mapper.ExpenseMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IExpenseService;
import com._520it.crm.util.OrderNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    @Autowired
    private ExpenseMapper expenseMapper;

    @Override
    public void save(Expense expense) {
        //设置出纳人员为当前登录用户
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        expense.setTeller(currentUser);
        //设置自动生成订单号
        OrderNumber instance = OrderNumber.getInstance();
        expense.setBillnumber(instance.getNumber());
        expenseMapper.insert(expense);
    }

    @Override
    public void update(Expense expense) {
        expenseMapper.updateByPrimaryKey(expense);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = expenseMapper.queryCount(qo);
        if(count <= 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Expense> list = expenseMapper.queryList(qo);
        return new PageResult(count,list);
    }

    @Override
    public void audit(Long id) {
        expenseMapper.audit(id);
    }

    @Override
    public void remove(Long id) {
        expenseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void delete(Long id) {
        expenseMapper.deleteByPrimaryKey(id);
    }

}
