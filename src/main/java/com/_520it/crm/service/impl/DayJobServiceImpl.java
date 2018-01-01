package com._520it.crm.service.impl;

import com._520it.crm.domain.Dayjob;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.DayjobMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DayJobQueryObject;
import com._520it.crm.service.IDayJobService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author MrHou
 * @date 2017/11/9.
 */
@Service
public class DayJobServiceImpl implements IDayJobService {
    @Autowired
    private DayjobMapper dayjobMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dayjobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Dayjob record) {
        if (record != null) {
            record.setInputTime(new Date());
        }
        return dayjobMapper.insert(record);
    }

    @Override
    public Dayjob selectByPrimaryKey(Long id) {
        return dayjobMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dayjob> selectAll() {
        return dayjobMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Dayjob record) {
        return dayjobMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult result(DayJobQueryObject qo) {
        Subject subject = SecurityUtils.getSubject();
        Employee current = (Employee) subject.getPrincipal();
        if (!current.isAdmin()) {
            if (!subject.hasRole("部门经理")) {
                qo.setCurrentId(current.getId());
            } else {
                qo.setDeptId(current.getDept().getId());
            }
        }
        Long total = dayjobMapper.total(qo);
        if (total <= 0) {
            return new PageResult(0L, Collections.emptyList());
        }
        List<Dayjob> queryForList = dayjobMapper.queryForList(qo);
        PageResult result = new PageResult(total, queryForList);
        return result;

    }

    @Override
    public void markSuccess(Long[] ids) {
        dayjobMapper.markSuccess(ids);
    }

    @Override
    public void markFailed(Long[] ids) {
        dayjobMapper.markFailed(ids);
    }

    @Override
    public void editDescription(Dayjob dayjob) {
        dayjobMapper.editDescription(dayjob);
    }
}
