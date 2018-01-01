package com._520it.crm.service;


import com._520it.crm.domain.Disappear;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IDisappearService {

    /**
     * 保存
     *
     * @param disappear
     */
    void save(Disappear disappear);

    /**
     * 更新
     *
     * @param disappear
     */
    void update(Disappear disappear);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 数据列表
     *
     * @param qo
     * @return
     */
    PageResult queryPage(QueryObject qo);


}
