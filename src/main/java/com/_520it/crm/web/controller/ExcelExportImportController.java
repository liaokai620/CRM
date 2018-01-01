package com._520it.crm.web.controller;

import com._520it.crm.domain.Queryholiday;
import com._520it.crm.domain.Salary;
import com._520it.crm.domain.Student;
import com._520it.crm.query.StudentQueryObject;
import com._520it.crm.service.ILatentStudentService;
import com._520it.crm.service.IQueryholidayService;
import com._520it.crm.service.ISalaryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/11
 */
@Controller
@RequestMapping("/excel")
public class ExcelExportImportController {

    @Autowired
    private ISalaryService       salaryService;
    @Autowired
    private ILatentStudentService studentService;
    @Autowired
    private IQueryholidayService queryholidayService;

    @RequestMapping("/outSalary")
    public void outSalary(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Salary> list = salaryService.selectAll();
        HSSFWorkbook wb = salaryService.export(list);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename=salary.xls");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    @RequestMapping("/exportForStudent")
    public void exportExcelForStudent( HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Student> students = studentService.queryPage(new StudentQueryObject()).getRows();
        HSSFWorkbook wb = studentService.export(students);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename=student.xls");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("/outQueryHoliday")
    public void outQueryHoliday(String download, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (download != null) {
            HSSFWorkbook wb = queryholidayService.export(Collections.EMPTY_LIST);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=queryHoliday.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } else {

            List<Queryholiday> list = queryholidayService.selectAll();
            HSSFWorkbook wb = queryholidayService.export(list);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=queryHoliday.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
    }
}

