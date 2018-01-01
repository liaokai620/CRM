package com._520it.crm.service;

import com._520it.crm.domain.YunFile;

import java.util.List;

/**
 * @author MrHou
 * @date 2017/11/11.
 */
public interface IYunFileService {
    int deleteByPrimaryKey(Long id);

    int insert(YunFile record);

    YunFile selectByPrimaryKey(Long id);

    List<YunFile> selectAll();

    int updateByPrimaryKey(YunFile record);


}
