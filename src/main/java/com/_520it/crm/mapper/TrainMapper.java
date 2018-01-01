package com._520it.crm.mapper;

import com._520it.crm.domain.Train;
import com._520it.crm.query.TrainQueryObject;

import java.util.List;

public interface TrainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Train record);

    Train selectByPrimaryKey(Long id);

    List<Train> selectAll();

    int updateByPrimaryKey(Train record);

    int queryForCount(TrainQueryObject qo);

    List<Train> queryForList(TrainQueryObject qo);
}