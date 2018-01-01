package com._520it.crm.service.impl;

import com._520it.crm.domain.Train;
import com._520it.crm.mapper.TrainMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TrainQueryObject;
import com._520it.crm.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by joker on 2017/11/11.
 */
@Service
public class TrainServiceImpl implements ITrainService {

    @Autowired
    TrainMapper trainMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return trainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Train record) {
        return trainMapper.insert(record);
    }

    @Override
    public Train selectByPrimaryKey(Long id) {
        return trainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Train> selectAll() {
        return trainMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Train record) {
        return trainMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(TrainQueryObject qo) {
        int rows = trainMapper.queryForCount(qo);
           if (rows == 0) {
               return new PageResult(0L, Collections.EMPTY_LIST);
           }
        List<Train> result = trainMapper.queryForList(qo);
        return new PageResult((long) rows,result);
    }
}
