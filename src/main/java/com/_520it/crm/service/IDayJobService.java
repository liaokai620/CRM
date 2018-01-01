package com._520it.crm.service;

import com._520it.crm.domain.Dayjob;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DayJobQueryObject;

import java.util.List;

/**
 * 当天任务
 *
 * @author MrHou
 * @date 2017/11/9.
 */
public interface IDayJobService {
    /**
     * 删除一个任务
     *
     * @param id
     * @return
     */

    int deleteByPrimaryKey(Long id);

    /**
     * 增加一个任务
     *
     * @param record
     * @return
     */
    int insert(Dayjob record);

    /**
     * 查询一个任务
     *
     * @param id
     * @return
     */
    Dayjob selectByPrimaryKey(Long id);

    /**
     * 查询所有任务
     *
     * @return
     */
    List<Dayjob> selectAll();

    /**
     * 更新一个任务
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Dayjob record);


    PageResult result(DayJobQueryObject qo);

    /**
     * 标记成功
     *
     * @param ids
     */
    void markSuccess(Long[] ids);

    /**
     * 标记失败
     * @param ids
     */
    void markFailed(Long[] ids);

    void editDescription(Dayjob dayjob);
}
