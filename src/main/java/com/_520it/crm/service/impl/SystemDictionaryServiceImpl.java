package com._520it.crm.service.impl;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.mapper.SystemDictionaryItemMapper;
import com._520it.crm.mapper.SystemDictionaryMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SystemDictionaryQueryObject;
import com._520it.crm.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Autowired
    private SystemDictionaryItemMapper itemMapper;

    @Override
    public List<SystemDictionary> listAll() {
        return systemDictionaryMapper.selectAll();
    }

    @Override
    public PageResult queryPage(SystemDictionaryQueryObject qo) {
        Long count = systemDictionaryMapper.queryForCount(qo);

        if (count == 0) {
            return new PageResult(0L,Collections.EMPTY_LIST);
        }
        List<?> list = systemDictionaryMapper.queryForList(qo);

        return new PageResult(count, list);
    }

    @Override
    public void save(SystemDictionary sd) {
        systemDictionaryMapper.insert(sd);
    }

    @Override
    public void edit(SystemDictionary sd) {
        systemDictionaryMapper.updateByPrimaryKey(sd);
    }

    @Override
    public void delete(Long id) {
        itemMapper.deleteItemByParentId(id);
        //删除前,把明细也删了
        systemDictionaryMapper.delete(id);
    }
}
