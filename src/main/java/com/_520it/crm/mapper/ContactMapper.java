package com._520it.crm.mapper;

import com._520it.crm.domain.Contact;
import com._520it.crm.query.ContactQueryObject;

import java.util.List;

public interface ContactMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Contact record);

    Contact selectByPrimaryKey(Long id);

    List<Contact> selectAll();

    int updateByPrimaryKey(Contact record);

    int queryForCount(ContactQueryObject qo);

    List<Contact> queryForList(ContactQueryObject qo);
}