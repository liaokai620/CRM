package com._520it.crm.service.impl;

import com._520it.crm.domain.Disappear;
import com._520it.crm.domain.Payment;
import com._520it.crm.domain.Student;
import com._520it.crm.mapper.DisappearMapper;
import com._520it.crm.mapper.PaymentMapper;
import com._520it.crm.mapper.StudentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.FormalStudentQueryObject;
import com._520it.crm.service.IFormalStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FormalStudentServiceImpl implements IFormalStudentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DisappearMapper disappearMapper;

    @Override
    public void save(Payment payment) {
        paymentMapper.insert(payment);
    }

    @Override
    public void update(Payment payment) {
        paymentMapper.updateByPrimaryKey(payment);
    }

    @Override
    public PageResult queryPage(FormalStudentQueryObject qo) {
        Long count = paymentMapper.queryCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Payment> list = paymentMapper.queryList(qo);
        return new PageResult(count, list);
    }

    @Override
    public List<Student> queryFormalStudent() {
        return studentMapper.queryFormalStudent();
    }

    @Override
    public void delete(Long id) {
        //删除正式学员信息
        Payment payment = paymentMapper.selectByPrimaryKey(id);
        studentMapper.deleteByPrimaryKey(payment.getStudent().getId());
        //删除正式学员的收款信息
        paymentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void turn(Payment payment) {
        Long paymentId = payment.getId();
        Long studentId = payment.getStudent().getId();
        Long gradeId   = payment.getGrade().getId();
        paymentMapper.turnGrade(paymentId, gradeId);
        studentMapper.turnGrade(studentId, gradeId);
    }

    @Override
    public void disappear(Long id) {
        Disappear disappear = disappearMapper.selectByPrimaryKey(id);
        disappearMapper.insert(disappear);
    }
}
