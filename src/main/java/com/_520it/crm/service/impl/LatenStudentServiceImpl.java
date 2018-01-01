package com._520it.crm.service.impl;


import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Payment;
import com._520it.crm.domain.Student;
import com._520it.crm.domain.TurnOverHistory;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.mapper.PaymentMapper;
import com._520it.crm.mapper.StudentMapper;
import com._520it.crm.mapper.TurnOverHistoryMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentQueryObject;
import com._520it.crm.service.ILatentStudentService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class LatenStudentServiceImpl implements ILatentStudentService {
    @Autowired
    private StudentMapper         studentMapper;
    @Autowired
    private EmployeeMapper        employeeMapper;
    @Autowired
    private PaymentMapper         paymentMapper;
    @Autowired
    private TurnOverHistoryMapper historyMapper;

    public int deleteByPrimaryKey(Long id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    public int insert(Student student) {
        //先判断是否有相同的电话和qq信息
        //查询出所有的学员的qq和电话
        List<Student> students = this.selectAll(new StudentQueryObject());
        for (Student stu : students) {
            if (stu.getQq().equals(student.getQq())) {
                throw new RuntimeException("当前对应QQ  " + student.getQq() + "  的学员已经存在");
            } else if (stu.getTel().equals(student.getTel())) {
                throw new RuntimeException("当前对应电话  " + student.getTel() + "  的学员已经存在");
            }
        }

        //设置录入时间
        student.setInputTime(new Date());
        student.setTurn(false);
        //设置录入人和营销人为当前用户
        Employee current = (Employee) SecurityUtils.getSubject().getPrincipal();
        student.setInputUser(current);
        student.setSalesman(current);
        return studentMapper.insert(student);
    }

    public Student selectByPrimaryKey(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    public List<Student> selectAll(StudentQueryObject qo) {
        return studentMapper.queryList(qo);
    }

    @Override
    public void move(Student student, String reason, Long targetOwnerId) {
        Employee target = employeeMapper.selectByPrimaryKey(targetOwnerId);
        //获取当前登录主体
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        student.setInputUser(target);
        studentMapper.setStatus(10L, student.getId());
        TurnOverHistory history = new TurnOverHistory();
        history.setBeforeOwner(currentUser);
        history.setDate(new Date());
        history.setName(student.getName());
        history.setQq(student.getQq());
        history.setReason(reason);
        history.setTargetOwner(target);
        history.setTel(student.getTel());
        historyMapper.insert(history);
    }

    @Override
    public void turn(Student student, Payment payment) {
        student.setStatus(1);
        student.setTurn(true);
        studentMapper.turn(student);
        payment.setStudent(student);
        BigDecimal totalAmount = payment.getTotalAmount();
        BigDecimal prepaid     = payment.getPrepaid();
        BigDecimal arrearage   = totalAmount.subtract(prepaid);
        payment.setArrearage(arrearage);
        if(payment.getPrepaid().compareTo(payment.getTotalAmount()) >= 0){
            payment.setState(Payment.STATE_YES);
        }
        paymentMapper.insert(payment);
    }

    @Override
    public List<Student> queryStudentForIncome() {
        return studentMapper.queryStudentForIncome();
    }

    @Override
    public PageResult queryPage(StudentQueryObject qo) {
        //高级查询,控制只能查看属于自己的潜在学员
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        qo.setCurrentUserId(employee.getId());

        long total = studentMapper.queryCount(qo);
        if (total <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Student> result = studentMapper.queryList(qo);
        return new PageResult(total, result);
    }


    public int updateByPrimaryKey(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void inport(MultipartFile excel) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(excel.getInputStream());
        HSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        HSSFRow row = null;
        Student student = null;
        for (int i = 1; i <= lastRowNum; i++) {
            row = sheet.getRow(i);
            student = new Student();
            student.setName((String) getCellValue(row.getCell(0)));
            student.setTel((String) getCellValue(row.getCell(1)));
            student.setMeetingTime((Date) getCellValue(row.getCell(2)));
            student.setNextFollowTime((Date) getCellValue(row.getCell(3)));
            student.setQq((String) getCellValue(row.getCell(4)));
            student.setIntro((String) getCellValue(row.getCell(7)));
            this.insert(student);
        }

    }

    private Object getCellValue(Cell cell) {
        // Alternatively, get the value and format it yourself
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    @Override
    public HSSFWorkbook export(List<Student> students) {
        String[] headers = new String[]{"姓名", "电话", "约访日期", "下次跟进时间"
                , "QQ", "意向等级", "意向学校", "备注", "客户类型", "状态",};
        int[] columnLength = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow r = sheet.createRow(0);
        HSSFCellStyle style = workbook.createCellStyle();
        //设置样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);//垂直居中
        //
        for (int i = 0; i < columnLength.length; i++) {
            sheet.setColumnWidth(i, 32 * columnLength[i]);
        }

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = r.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            r = sheet.createRow(i + 1);
            r.createCell(0).setCellValue(student.getName());
            r.createCell(1).setCellValue(student.getTel());
            if (student.getMeetingTime() != null) {
                r.createCell(2).setCellValue(student.getMeetingTime().toLocaleString());
            }
            if (student.getNextFollowTime() != null) {
                r.createCell(3).setCellValue(student.getNextFollowTime().toLocaleString());
            }
            r.createCell(4).setCellValue(student.getQq());
            if (student.getWantLevel() != null) {
                r.createCell(5).setCellValue(student.getWantLevel().getName());
            }
            if (student.getWantSchool() != null) {
                r.createCell(6).setCellValue(student.getWantSchool().getName());
            }
            r.createCell(7).setCellValue(student.getIntro());
            if (student.getClientType() != null) {
                r.createCell(8).setCellValue(student.getClientType().getName());
            }
            if (student.getStatus() == 0) {
                r.createCell(9).setCellValue("潜在学员");
            } else if (student.getStatus() == 1) {
                r.createCell(9).setCellValue("正式学员");
            } else {
                r.createCell(9).setCellValue("客户池");
            }
        }
        return workbook;
    }

    @Override
    public void put(Long studentId) {
        studentMapper.putIntoPool(studentId);
    }

    @Override
    public List<Student> queryForCustomerPool(StudentQueryObject qo) {
        return studentMapper.queryForCustomerPool(qo);
    }

    /**
     * 文件导出操作
     *
     * @param req
     * @param response
     * @throws Exception
     */
    @Override
    public void download(HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置文件的名称
        String filename = new String("文件模板.xls".getBytes("UTF-8"), "ISO-8859-1");
        //设置文件下载的响应头
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        //创建一个excel文件
        String realPath = req.getSession().getServletContext().getRealPath("/WEB-INF/download/studentTemplate.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(realPath));
        workbook.write(response.getOutputStream());
    }
}
