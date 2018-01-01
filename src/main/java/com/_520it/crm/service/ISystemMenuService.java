package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.SystemMenu;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ISystemMenuService {
	int deleteByPrimaryKey(Long id);
    int insert(SystemMenu record);
    SystemMenu selectByPrimaryKey(Long id);
    List<SystemMenu> selectAll();
    int updateByPrimaryKey(SystemMenu record);
	PageResult queryPage(QueryObject qo);
	List<SystemMenu> queryTree();
	List<SystemMenu> queryForRole();
	List<Long> queryMenuIdsListForRole(Long roleId);
	List<SystemMenu> indexMenu();
}
