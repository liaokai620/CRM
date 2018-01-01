package com._520it.crm.service;

import com._520it.crm.domain.Contact;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ContactQueryObject;

import java.util.List;

/**
 * Created by joker on 2017/11/9.
 */
public interface IContactService {

    int deleteByPrimaryKey(Long id);

    int insert(Contact record);

    Contact selectByPrimaryKey(Long id);

    List<Contact> selectAll();

    int updateByPrimaryKey(Contact record);
    //分页
    PageResult query(ContactQueryObject qo);
}
