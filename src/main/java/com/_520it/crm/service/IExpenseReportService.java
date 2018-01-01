package com._520it.crm.service;

import com._520it.crm.query.ExpenseReportQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/11.
 */
public interface IExpenseReportService {

    List<Map<String,Object>> expenseList(ExpenseReportQueryObject qo);
}
