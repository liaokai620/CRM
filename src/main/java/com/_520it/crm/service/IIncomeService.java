package com._520it.crm.service;


import com._520it.crm.domain.Income;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IIncomeService {

    /**
     * 保存
     * @param income
     */
    void save(Income income);

    /**
     * 更新
     * @param income
     */
    void update(Income income);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 审核
     * @param income
     */
    void audit(Income income);

    /**
     * 数据列表
     * @param qo
     * @return
     */
    PageResult queryPage(QueryObject qo);

    /**
     * 统计收款金额
     * @return
     */
    Income total();

    /**
     * 单据复核
     * @param id
     */
    void recheck(Long id);

    /**
     * 取消单据复核
     * @param id
     */
    void backrecheck(Long id);

    /**
     * 单据审核
     * @param id
     */
    void audit(Long id);
}
