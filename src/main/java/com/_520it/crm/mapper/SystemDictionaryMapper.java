package com._520it.crm.mapper;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
    /**
     * 字典列表
     * @return
     */
    List<SystemDictionary> selectAll();

    Long queryForCount(SystemDictionaryQueryObject qo);

    List<SystemDictionary> queryForList(SystemDictionaryQueryObject qo);

    void insert(SystemDictionary sd);


    void updateByPrimaryKey(SystemDictionary sd);

    void delete(Long id);

    void selectPrantByParentId(Long parentId);

    void get(Long parentId);
}