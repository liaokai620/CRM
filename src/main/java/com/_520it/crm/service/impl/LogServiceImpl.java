package com._520it.crm.service.impl;

import com._520it.crm.domain.Log;
import com._520it.crm.mapper.LogMapper;
import com._520it.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyhzzz
 * @date 2017/11/7
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;
    @Override
    public void save(Log log) {
        logMapper.insert(log);
    }
}
