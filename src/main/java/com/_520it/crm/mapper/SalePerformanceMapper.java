package com._520it.crm.mapper;

import com._520it.crm.query.SaleQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/10.
 */
public interface SalePerformanceMapper {

    List<Map<String,Object>> queryForSale (SaleQueryObject qo);

    List<Map<String, Object>> queryListSaleMan(SaleQueryObject qo);
}
