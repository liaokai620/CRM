package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.*;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.query.SalaryQueryObject;
import com._520it.crm.service.ISalaryService;
import com._520it.crm.util.DateUtil;
import com._520it.crm.util.RevenueUtil;
import com.sun.tools.corba.se.idl.AttributeEntry;
import freemarker.core.ArithmeticEngine;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
@Service
public class SalaryServiceImpl implements ISalaryService {

    @Autowired
    private SalaryMapper       salaryMapper;
    @Autowired
    private PaymentMapper      paymentMapper;
    @Autowired
    private QueryholidayMapper queryholidayMapper;
    @Autowired
    private AttendanceMapper   attendanceMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Salary record) throws Exception {

        Employee employee = record.getEmployee();
        BigDecimal commission = BigDecimal.ZERO;                       //提成
        List<Payment> payments = paymentMapper.selectbyPrimaryEmployeeId(employee.getId());
        for (Payment payment : payments) {
            if (payment != null && payment.getState() == 1) {
                BigDecimal totalAmount = payment.getTotalAmount();
                commission = totalAmount.multiply(new BigDecimal(0.10)); //提成率
                commission = commission.add(commission);
            }
        }
        record.setCommission(commission);
        //判断是否有全勤奖金
        BigDecimal alldaysalary = BigDecimal.ZERO;
        List<Queryholiday> queryholidays = queryholidayMapper.selectbyPrimaryEmployee(employee.getId());
        BigDecimal basesalary = record.getBasesalary();
        if (queryholidays.size() == 0) {
            alldaysalary = record.getAlldaysalary();                 //全勤奖金
        } else {
            for (Queryholiday queryholiday : queryholidays) {
                BigDecimal sss = basesalary.divide(new BigDecimal(20));
                basesalary = basesalary.subtract(sss);

            }
        }
        record.setBasesalary(basesalary);
        record.setAlldaysalary(alldaysalary);
        //罚款
        BigDecimal fine = BigDecimal.ZERO;
        BigDecimal fines = new BigDecimal(30);

        Date startTime = DateUtil.getStart(record.getSalarytime());
        Date endTime = DateUtil.getMonthPlus(startTime);

        List<Attendance> attendances = attendanceMapper.selectbyPrimaryEmployee(employee.getId(), startTime, endTime);
        for (Attendance attendance : attendances) {
            if (attendance.getState() == 1) {
                fine = fine.add(fines);
            }
        }
        record.setFine(fine);
        record.setState(1);
        BigDecimal subsidy = record.getSubsidy();                   //补贴
        BigDecimal fivesalary = record.getFivesalary();             //五险一金
        BigDecimal sum = basesalary.add(alldaysalary.add(commission.add(subsidy)));
        BigDecimal totalsalary = sum.subtract(fine).subtract(fivesalary);
        record.setTotalsalary(totalsalary);
        RevenueUtil revenueUtil = new RevenueUtil();                //税收工具类
        revenueUtil.setTotalSalary(totalsalary.doubleValue());
        BigDecimal afterSalary = BigDecimal.valueOf(revenueUtil.getAfterSalary());
        record.setAftersalary(afterSalary);
        List<Salary> salarys = salaryMapper.selectByPrimarytime(startTime, endTime,employee.getId());
        if (salarys.size() == 0) {
            return salaryMapper.insert(record);
        } else {
            return -1;
        }
    }

    @Override
    public Salary selectByPrimaryKey(Long id) {
        return salaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Salary> selectAll() {
        return salaryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Salary record) {
        BigDecimal basesalary = record.getBasesalary();             //基本工资
        BigDecimal alldaysalary = record.getAlldaysalary();         //全勤奖金
        BigDecimal commission = record.getCommission();             //提成
        BigDecimal subsidy = record.getSubsidy();                   //补贴
        BigDecimal fine = record.getFine();                         //罚款
        BigDecimal fivesalary = record.getFivesalary();             //五险一金
        BigDecimal sum = basesalary.add(alldaysalary.add(commission.add(subsidy)));
        BigDecimal totalsalary = sum.subtract(fine).subtract(fivesalary);
        record.setTotalsalary(totalsalary);
        RevenueUtil revenueUtil = new RevenueUtil();                //税收工具类
        revenueUtil.setTotalSalary(totalsalary.doubleValue());
        BigDecimal afterSalary = BigDecimal.valueOf(revenueUtil.getAfterSalary());
        record.setAftersalary(afterSalary);
        return salaryMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(SalaryQueryObject qo) {
        Long count = salaryMapper.count(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<?> results = salaryMapper.queryForList(qo);
        return new PageResult(count, results);
    }

    @Override
    public void quit(Long id) {
        salaryMapper.changeState(id, 0);
    }

    @Override
    public HSSFWorkbook export(List<Salary> list) {
        String[] excelHeader = {" 员工姓名 ", " 基本工资 ", " 全勤奖金 ", " 补贴  ", "五险一金", " 罚款  ", "提成  ", "总工资  ", " 税后工资 ", " 年终奖 ", "    日期   ", "状态"};
        // 单元格列宽
        int[] excelHeaderWidth = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Salary");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
// 设置居中样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
// 设置列宽度（像素）
        for (int i = 0; i < excelHeaderWidth.length; i++) {
            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);
        }

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Salary salary = list.get(i);
            row.createCell(0).setCellValue(salary.getEmployee().getRealname());
            row.createCell(1).setCellValue(String.valueOf(salary.getBasesalary()));
            row.createCell(2).setCellValue(String.valueOf(salary.getAlldaysalary()));
            row.createCell(3).setCellValue(String.valueOf(salary.getSubsidy()));
            row.createCell(4).setCellValue(String.valueOf(salary.getFivesalary()));
            row.createCell(5).setCellValue(String.valueOf(salary.getFine()));
            row.createCell(6).setCellValue(String.valueOf(salary.getCommission()));
            row.createCell(7).setCellValue(String.valueOf(salary.getTotalsalary()));
            row.createCell(8).setCellValue(String.valueOf(salary.getAftersalary()));
            row.createCell(9).setCellValue(String.valueOf(salary.getYearendsalary()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date date = salary.getSalarytime();
            String format = simpleDateFormat.format(date);
            row.createCell(10).setCellValue(format);
            row.createCell(11).setCellValue("已经确认无误");
        }
        return wb;
    }
}
