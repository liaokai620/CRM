package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
	int deleteByPrimaryKey(Long id);
    int insert(Employee record);
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee record);
	PageResult queryPage(EmployeeQueryObject qo);
	void quit(Long id);

    Employee queryForUsername(String username);

    /**
     * 查询所有角色是老师的员工
     * @return
     */
    List<Employee> queryListTeacher();

    List<Employee> querySalesmanList();

    List<Employee> queryFinanceList();

    List<Employee> listAllSalesMan();

    List<Employee> listAllAdvisor();

    List<Employee> selectAllConsulting();

    void changePassword(Long id, String password);

    /**
     * 用于页面的校验操作
     * @param username
     * @param password
     * @return
     */
    Employee checkout(String username, String password);
}
