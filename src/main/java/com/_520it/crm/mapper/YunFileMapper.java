package com._520it.crm.mapper;

import com._520it.crm.domain.YunFile;
import java.util.List;

public interface YunFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YunFile record);

    YunFile selectByPrimaryKey(Long id);

    List<YunFile> selectAll();

    int updateByPrimaryKey(YunFile record);
}