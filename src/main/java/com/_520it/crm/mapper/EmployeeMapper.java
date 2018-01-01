package com._520it.crm.mapper;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee login(@Param("username") String username, @Param("password") String password);

    Long queryPageCount(EmployeeQueryObject qo);

    List<Employee> queryPageData(EmployeeQueryObject qo);

    void changeState(@Param("employeeId") Long id, @Param("employeeState") int employeeStateQuit);

    void insertRelation(@Param("roleId") Long roleId, @Param("employeeId") Long employeeId);

    List<Permission> queryPermissionByEmployeeId(Long employeeId);

    void deleteRelation(Long id);

    Employee queryForUsername(String username);

    /**
     * 查询所有角色是老师的员工
     * @return
     */
    List<Employee> queryListTeacher();

    /**
     * 查询所有销售部门人员
     * @return
     */
    List<Employee> querySalesmanList();

    /**
     * 查询所有财务部门员工
     * @return
     */
    List<Employee> queryFinanceList();

    List<Employee> listAllSalesMan();

    List<Employee> listAllAdvisor();

    /**
     * 根据学生id查询营销人员
     * @param studentId
     * @return
     */
    Employee selectByStudentId(Long studentId);

    List<Employee> selectAllConsulting();

    void changePassword(@Param("employeeId") Long employeeId, @Param("password")String password);

    Employee selectByRealname(String realname);

    Employee checkout(@Param("username") String username, @Param("password") String password);
}