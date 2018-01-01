package com._520it.crm.service;

import com._520it.crm.domain.Salary;
import com._520it.crm.domain.Student;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.query.SalaryQueryObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
public interface ISalaryService {
    int deleteByPrimaryKey(Long id);

    int insert(Salary record)throws Exception;

    Salary selectByPrimaryKey(Long id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);

    PageResult queryPage(SalaryQueryObject qo);

    void quit(Long id);

    HSSFWorkbook export(List<Salary> list);
}
