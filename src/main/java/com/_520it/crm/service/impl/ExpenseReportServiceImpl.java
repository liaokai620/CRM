package com._520it.crm.service.impl;

import com._520it.crm.mapper.ExpenseReportMapper;
import com._520it.crm.query.ExpenseReportQueryObject;
import com._520it.crm.service.IExpenseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/11.
 */
@Service
public class ExpenseReportServiceImpl implements IExpenseReportService {

    @Autowired
    private ExpenseReportMapper mapper;

    @Override
    public List<Map<String, Object>> expenseList(ExpenseReportQueryObject qo) {

        return mapper.expenseList(qo);
    }
}
