package com._520it.crm.service.impl;

import com._520it.crm.domain.Disappear;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.DisappearMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IDisappearService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DisappearServiceImpl implements IDisappearService {

    @Autowired
    private DisappearMapper disappearMapper;

    @Override
    public void save(Disappear disappear) {
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        disappear.setInputuser(currentUser);
        disappearMapper.insert(disappear);
    }

    @Override
    public void update(Disappear disappear) {
        disappearMapper.updateByPrimaryKey(disappear);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = disappearMapper.queryCount(qo);
        if(count <= 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Disappear> list = disappearMapper.queryList(qo);
        return new PageResult(count,list);
    }

    @Override
    public void delete(Long id) {
        disappearMapper.deleteByPrimaryKey(id);
    }

}
