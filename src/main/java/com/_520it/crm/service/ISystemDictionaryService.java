package com._520it.crm.service;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SystemDictionaryQueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
public interface ISystemDictionaryService {


    List<SystemDictionary> listAll();

    PageResult queryPage(SystemDictionaryQueryObject qo);

    void save(SystemDictionary sd);

    void edit(SystemDictionary sd);

    void delete(Long id);
}
