package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Queryholiday;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.mapper.QueryholidayMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryHolidayQueryObject;
import com._520it.crm.service.IQueryholidayService;
import com._520it.crm.util.DateUtil;
import com._520it.crm.util.FileUploadUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/10
 */
@Service
public class QueryholidayServiceImpl implements IQueryholidayService {

    @Autowired
    private QueryholidayMapper queryholidayMapper;
    @Autowired
    private EmployeeMapper     employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return queryholidayMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Queryholiday record) {
        record.setState(1);
        if (record.getQuerytype().equals(1)) {
            record.setQuerytype("病假");
        } else {
            record.setQuerytype("事假");
        }
        return queryholidayMapper.insert(record);
    }

    @Override
    public Queryholiday selectByPrimaryKey(Long id) {
        return queryholidayMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Queryholiday> selectAll() {
        return queryholidayMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Queryholiday record) {
        return queryholidayMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryHolidayQueryObject qo) {
        Long count = queryholidayMapper.count(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<?> results = queryholidayMapper.queryForList(qo);
        return new PageResult(count, results);
    }

    @Override
    public void quit(Long id) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();

        queryholidayMapper.changeState(id, employee.getRealname(), 0);

    }

    @Override
    public HSSFWorkbook export(List<Queryholiday> list) {
        String[] excelHeader = {" 申请人 ", " 请假时间 ", " 结束时间 ", " 请假原因  ", "请假类型", " 状态  ", "审核人  "};
        // 单元格列宽
        int[] excelHeaderWidth = {100, 250, 250, 300, 100, 100, 100};
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("请假条");
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
        if (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 1);
                Queryholiday queryholiday = list.get(i);
                row.createCell(0).setCellValue(queryholiday.getEmployee().getRealname());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startTime = queryholiday.getStarttime();
                Date endtime = queryholiday.getEndtime();
                String formatStartTime = simpleDateFormat.format(startTime);
                String formatEndTime = simpleDateFormat.format(endtime);
                row.createCell(1).setCellValue(formatStartTime);
                row.createCell(2).setCellValue(formatEndTime);
                row.createCell(3).setCellValue(queryholiday.getQueryresult());
                row.createCell(4).setCellValue(queryholiday.getQuerytype());
                int state = queryholiday.getState();
                String states;
                if (state == 0) {
                    states = "已确认";
                } else {
                    states = "未确认";
                }
                row.createCell(5).setCellValue(states);
                row.createCell(6).setCellValue(queryholiday.getAuditor());
                row.setRowStyle(style);
            }
        }
        return wb;
    }

    @Override
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        FileUploadUtil.upload(request, "请假条");
    }

    @Override
    public void insertExl(MultipartFile file,String path) throws Exception {
        File files = new File(path,"请假条");
        FileUtils.writeByteArrayToFile(files, file.getBytes());
        Workbook workbook = Workbook.getWorkbook(files);
        Sheet sheet = workbook.getSheet(0);
        System.out.println(sheet);

        int rows = sheet.getRows();
        for (int i = 1; i < rows; i++) {
            Cell[] row = sheet.getRow(i);
            Queryholiday queryholiday = new Queryholiday();
            String realname = row[0].getContents();
            //根据员工真实姓名查询员工对象
            Employee empId = employeeMapper.selectByRealname(realname);
            //设置员工对象
            queryholiday.setEmployee(empId);
            //设置开始时间
            String startTime = row[1].getContents();
            queryholiday.setStarttime(DateUtil.getTime(startTime));
            //设置结束时间
            String endTime = row[2].getContents();
            queryholiday.setEndtime(DateUtil.getTime(endTime));
            //设置请假原因
            String queryResult = row[3].getContents();
            queryholiday.setQueryresult(queryResult);
            //设置请假类型
            String queryType = row[4].getContents();
            queryholiday.setQuerytype(queryType);
            //设置状态
            String state = row[5].getContents();
            if (state.equals("已确认")) {

                queryholiday.setState(0);
            }else{
                queryholiday.setState(1);
            }
            //设置审核人
            if(row.length==7){
                String auditor = row[6].getContents();
                queryholiday.setAuditor(auditor);
            }
            queryholidayMapper.insert(queryholiday);
        }
    }
}
