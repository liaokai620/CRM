package com._520it.crm.service;


import com._520it.crm.domain.Payment;
import com._520it.crm.domain.Student;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.FormalStudentQueryObject;

import java.util.List;

public interface IFormalStudentService {

    /**
     * 保存
     *
     * @param payment
     */
    void save(Payment payment);

    /**
     * 更新
     *
     * @param payment
     */
    void update(Payment payment);

    /**
     * 数据列表
     *
     * @param qo
     * @return
     */
    PageResult queryPage(FormalStudentQueryObject qo);

    /**
     * 所有转正的学员(只有id 姓名)
     *
     * @return
     */
    List<Student> queryFormalStudent();

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 学员转班
     * @param
     */
    void turn(Payment payment);

    /**
     * 学员流失
     * @param id
     */
    void disappear(Long id);
}
