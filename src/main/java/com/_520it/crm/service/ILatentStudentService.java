package com._520it.crm.service;

import com._520it.crm.domain.Payment;
import com._520it.crm.domain.Student;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentQueryObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ILatentStudentService {
	int deleteByPrimaryKey(Long id);
    int insert(Student student);
    Student selectByPrimaryKey(Long id);
    List<Student> selectAll(StudentQueryObject qo);
    int updateByPrimaryKey(Student student);

    PageResult queryPage(StudentQueryObject qo);

    void turn(Student student, Payment payment);

    void move(Student student,String reason,Long targetOwnerId);

    List<Student> queryForCustomerPool(StudentQueryObject qo);

    /**
     * 下载student的模板
     * @param req
     * @param resp
     */
    void download(HttpServletRequest req, HttpServletResponse resp)throws Exception;

    List<Student> queryStudentForIncome();

    void put(Long  studentId);

    HSSFWorkbook export(List<Student> students);

    void inport(MultipartFile excel) throws IOException;
}
