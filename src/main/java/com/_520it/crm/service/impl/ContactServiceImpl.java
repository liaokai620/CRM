package com._520it.crm.service.impl;

import com._520it.crm.domain.Contact;
import com._520it.crm.mapper.ContactMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ContactQueryObject;
import com._520it.crm.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by joker on 2017/11/9.
 */
@Service

public class ContactServiceImpl implements IContactService {
    @Autowired
    ContactMapper contactMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return contactMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Contact record) {
        return contactMapper.insert(record);
    }

    @Override
    public Contact selectByPrimaryKey(Long id) {
        return contactMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Contact> selectAll() {
        return contactMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Contact record) {
        return contactMapper.updateByPrimaryKey(record);
    }

    //分页
    @Override
    public PageResult query(ContactQueryObject qo) {
        int rows = contactMapper.queryForCount(qo);
           if (rows == 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
           }
        List<Contact> result = contactMapper.queryForList(qo);
        return new PageResult((long) rows,result);
    }
}
