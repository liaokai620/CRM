package com._520it.crm.web.controller;

import com._520it.crm.query.ExpenseReportQueryObject;
import com._520it.crm.service.IExpenseReportService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/11.
 */
@Controller
@RequestMapping("expenseReport")
public class ExpenseReportController {

    @Autowired
    private IExpenseReportService service;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("groupByMap", ExpenseReportQueryObject.expenseMap);
        return "expenseReport";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Map<String, Object>> list(ExpenseReportQueryObject qo) {
        List<Map<String, Object>> result = null;
        result = service.expenseList(qo);
        return result;
    }

    @RequestMapping("/reportByPie")
    public String reportByPie(ExpenseReportQueryObject qo, Model model) {
        List<Map<String, Object>> list = service.expenseList(qo);
        List<String> listName = new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();

        BigDecimal max = BigDecimal.ZERO; //默认最大值是0
        for (Map<String, Object> map : list) {
            listName.add((String) map.get("groupByType"));
            Map<String, Object> row = new HashMap<>();
            BigDecimal amount = (BigDecimal) map.get("totalMoney");

            row.put("name", map.get("groupByType"));
            row.put("value", map.get("totalMoney"));
            data.add(row);

            if (amount.compareTo(max)>=0){
                max = amount ;
            }

        }
        model.addAttribute("listNames", JSON.toJSONString(listName));
        model.addAttribute("data", JSON.toJSONString(data));
        model.addAttribute("max", max);
        return "reportByPie";
    }
}
