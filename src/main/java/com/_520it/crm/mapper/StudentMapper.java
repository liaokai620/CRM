package com._520it.crm.mapper;

import com._520it.crm.domain.Student;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.StudentQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Student student);

    Student selectByPrimaryKey(Long id);

    List<Student> selectAll(QueryObject qo);

    int updateByPrimaryKey(Student student);

    long queryCount(StudentQueryObject qo);

    List<Student> queryList(StudentQueryObject qo);

    void setStatus(@Param("statusId")Long statusId , @Param("id")Long id);

    List<Student> queryStudentForIncome();

    List<Student> queryFormalStudent();

    List<Student> queryForCustomerPool(StudentQueryObject qo);

    void turn(Student student);

    void putIntoPool(Long  studentId);

    void turnGrade(@Param("studentId") Long studentId, @Param("gradeId") Long gradeId);

    void updateGrade(Student student);
}