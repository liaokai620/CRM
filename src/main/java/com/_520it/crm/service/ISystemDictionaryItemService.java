package com._520it.crm.service;

import com._520it.crm.domain.SystemDictionaryItem;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
public interface ISystemDictionaryItemService {

    void save(SystemDictionaryItem item);

    List<SystemDictionaryItem> listItemByParentId(Long parentId);

    void saveItem(SystemDictionaryItem item);

    void editItem(SystemDictionaryItem item);

    void deleteItem(Long id);

    List<SystemDictionaryItem> queryStarForSchool();

    List<SystemDictionaryItem> queryNatureForSchool();
}
