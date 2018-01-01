package com._520it.crm.service;

import com._520it.crm.domain.Queryholiday;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.AttendanceQueryObject;
import com._520it.crm.query.QueryHolidayQueryObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/10
 */
public interface IQueryholidayService {

    int deleteByPrimaryKey(Long id);

    int insert(Queryholiday record);

    Queryholiday selectByPrimaryKey(Long id);

    List<Queryholiday> selectAll();

    int updateByPrimaryKey(Queryholiday record);


    PageResult queryPage(QueryHolidayQueryObject qo);

    void quit(Long id);

    HSSFWorkbook export(List<Queryholiday> list);

    void upload(HttpServletRequest request, HttpServletResponse response);

    void insertExl(MultipartFile file,String path) throws Exception;
}
