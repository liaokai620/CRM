package com._520it.crm.service.impl;

import com._520it.crm.mapper.SalePerformanceMapper;
import com._520it.crm.query.SaleQueryObject;
import com._520it.crm.service.ISalePerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/10.
 */
@Service
public class SalePerformanceServiceImpl implements ISalePerformanceService {

    @Autowired
    private SalePerformanceMapper mapper;

    @Override
    public List<Map<String, Object>> queryForSale(SaleQueryObject qo) {

        return mapper.queryForSale(qo);
    }

    @Override
    public List<Map<String, Object>> queryListSaleMan(SaleQueryObject qo) {
        return mapper.queryListSaleMan(qo);
    }
}
