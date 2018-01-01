package com._520it.crm.service;

import com._520it.crm.domain.Train;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TrainQueryObject;

import java.util.List;

/**
 * Created by joker on 2017/11/11.
 */
public interface ITrainService {
    int deleteByPrimaryKey(Long id);

    int insert(Train record);

    Train selectByPrimaryKey(Long id);

    List<Train> selectAll();

    int updateByPrimaryKey(Train record);

    PageResult query(TrainQueryObject qo);
}
