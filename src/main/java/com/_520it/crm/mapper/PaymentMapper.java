package com._520it.crm.mapper;

import com._520it.crm.domain.Payment;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    Payment selectByPrimaryKey(Long id);

    List<Payment> selectAll();

    int updateByPrimaryKey(Payment record);

    Long queryCount(QueryObject qo);

    List<Payment> queryList(QueryObject qo);

    void turnGrade(@Param("paymentId") Long paymentId, @Param("gradeId") Long gradeId);

    List<Payment>  selectbyPrimaryEmployeeId(Long EmpId);

    Payment selectByStudentId(Long studentId);
}