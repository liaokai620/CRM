package com._520it.crm.mapper;

import com._520it.crm.domain.SystemDictionaryItem;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    SystemDictionaryItemMapper selectByPrimaryKey(Long id);

    List<SystemDictionaryItemMapper> selectAll();

    int updateByPrimaryKey(SystemDictionaryItemMapper record);

    List<SystemDictionaryItem> listItemByParentId(Long parentId);

    /**删除数据字典的时候把明细也删除了
     *
     * @param parentId
     */
    void deleteItemByParentId(Long parentId);

    void saveItem( SystemDictionaryItem item);

    void editItem(SystemDictionaryItem item);

    List<SystemDictionaryItem> queryStarForSchool(long starId);

    List<SystemDictionaryItem> queryNatureForSchool(long natueId);
}