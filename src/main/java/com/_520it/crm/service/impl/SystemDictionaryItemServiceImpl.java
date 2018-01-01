package com._520it.crm.service.impl;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.domain.SystemDictionaryItem;
import com._520it.crm.mapper.SystemDictionaryItemMapper;
import com._520it.crm.mapper.SystemDictionaryMapper;
import com._520it.crm.service.ISystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {

    @Autowired
    private SystemDictionaryItemMapper itemMapper;

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public void save(SystemDictionaryItem item) {

    }

    @Override
    public List<SystemDictionaryItem> listItemByParentId(Long parentId) {
        return itemMapper.listItemByParentId(parentId);
    }

    @Override
    public void saveItem(SystemDictionaryItem item) {

        itemMapper.saveItem(item);
    }

    @Override
    public void editItem(SystemDictionaryItem item) {
        itemMapper.editItem(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemMapper.deleteByPrimaryKey(id);
    }

    //学校模块查询星级  -----托马瑞留
    @Override
    public List<SystemDictionaryItem> queryStarForSchool() {
        List<SystemDictionaryItem> list = itemMapper.queryStarForSchool(8L);
        return list;
    }

    //学校模块查询办学性质  -----托马瑞留
    @Override
    public List<SystemDictionaryItem> queryNatureForSchool() {
        List<SystemDictionaryItem> list = itemMapper.queryNatureForSchool(9L);
        return list;
    }
}
