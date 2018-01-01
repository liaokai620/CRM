package com._520it.crm.service;


import com._520it.crm.domain.Expense;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IExpenseService {

    /**
     * 保存
     *
     * @param expense
     */
    void save(Expense expense);

    /**
     * 更新
     *
     * @param expense
     */
    void update(Expense expense);

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

    /**
     * 支出审核
     * @param id
     */
    void audit(Long id);

    /**
     * 支出删除
     * @param id
     */
    void remove(Long id);
}
