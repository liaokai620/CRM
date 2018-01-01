package com._520it.crm.web.controller;

import com._520it.crm.query.SaleQueryObject;
import com._520it.crm.service.ISalePerformanceService;
import com._520it.crm.util.PermissionName;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/10.
 */

@Controller
@RequestMapping("/performance")
public class SalePerformanceController {

    @Autowired
    private ISalePerformanceService service;

    @RequestMapping("")
    @RequiresPermissions("performance:index")
    @PermissionName("销售业绩报表首页")
    public String index() {

        return "performance";
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("performance:list")
    @PermissionName("销售业绩报表数据")
    public List<Map<String, Object>> list(SaleQueryObject qo) {
        List<Map<String, Object>> result = null;
        result = service.queryForSale(qo);
        return result;
    }

    @RequestMapping("/saleChartByPie")
    @RequiresPermissions("performance:saleChartByBar")
    @PermissionName("销售业绩报表查看")
    public String saleChartByBar(Model model, SaleQueryObject qo) {
        List<Map<String, Object>> listSaleMan = service.queryListSaleMan(qo);
        List<Map<String, Object>> data = service.queryListSaleMan(qo);
        List<String> listName = new ArrayList<>();

        Long max = 0L;//最大值默认是零
        for (Map<String, Object> row : listSaleMan) {
            listName.add((String) row.get("saleMan"));
            Map<String, Object> map = new HashMap<>();
            data.add(map);

            Long amount = (Long) row.get("studentNum");

            map.put("name", row.get("saleMan"));
            map.put("value", row.get("studentNum"));
            if (amount >= max) {
                max = amount;
            }

        }
        model.addAttribute("saleMan", JSON.toJSONString(listName));
        model.addAttribute("data", JSON.toJSONString(data));
        model.addAttribute("max", max);
        return "saleChartByPie";
    }


}
