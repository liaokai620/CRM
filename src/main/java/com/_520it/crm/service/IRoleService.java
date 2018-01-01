package com._520it.crm.service;
import java.util.ArrayList;
import java.util.List;
import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IRoleService {
	int deleteByPrimaryKey(Long id);
    int insert(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);
	PageResult queryPage(QueryObject qo);
	List<Long> queryRoleIdListForEmployeeForm(Long employeeId);
	void addMenu(ArrayList<Long> ids, Long roleId);

    List<Role> queryForEmployee(Long id);
}
