package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.*;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PromotionServiceImpl implements IPromotionService {

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void save(Promotion promotion) {
        Grade    grade    = gradeMapper.selectByStudentId(promotion.getStudent().getId());
        Employee employee = employeeMapper.selectByStudentId(promotion.getStudent().getId());
        Payment  payment   = paymentMapper.selectByStudentId(promotion.getStudent().getId());
        promotion.setBeforegrade(grade);
        promotion.setSalesman(employee);
        promotion.setPayment(payment);
        promotionMapper.insert(promotion);
    }

    @Override
    public void update(Promotion promotion) {
        promotionMapper.updateByPrimaryKey(promotion);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = promotionMapper.queryCount(qo);
        if(count <= 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Promotion> list = promotionMapper.queryList(qo);
        return new PageResult(count,list);
    }


    @Override
    public void delete(Long id) {
        promotionMapper.deleteByPrimaryKey(id);
    }

}
