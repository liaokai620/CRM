package com._520it.crm.service.impl;

import com._520it.crm.domain.YunFile;
import com._520it.crm.mapper.YunFileMapper;
import com._520it.crm.service.IYunFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrHou
 * @date 2017/11/11.
 */
@Service
public class YunFileServiceImpl implements IYunFileService {
    @Autowired
    private YunFileMapper yunFileMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return yunFileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YunFile record) {
        return yunFileMapper.insert(record);
    }

    @Override
    public YunFile selectByPrimaryKey(Long id) {
        return yunFileMapper.selectByPrimaryKey(id
        );
    }

    @Override
    public List<YunFile> selectAll() {
        return yunFileMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(YunFile record) {
        return yunFileMapper.updateByPrimaryKey(record);
    }
}
