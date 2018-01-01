package com._520it.crm.service;


import com._520it.crm.domain.Promotion;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IPromotionService {

    /**
     * 保存
     *
     * @param promotion
     */
    void save(Promotion promotion);

    /**
     * 更新
     *
     * @param promotion
     */
    void update(Promotion promotion);

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
