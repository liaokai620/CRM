package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.List;

import com._520it.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.SystemMenu;
import com._520it.crm.mapper.SystemMenuMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemMenuService;
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Autowired
	private SystemMenuMapper systemMenuMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return systemMenuMapper.deleteByPrimaryKey(id);
	}

	public int insert(SystemMenu record) {
		return systemMenuMapper.insert(record);
	}

	public SystemMenu selectByPrimaryKey(Long id) {
		return systemMenuMapper.selectByPrimaryKey(id);
	}

	public List<SystemMenu> selectAll() {
		return systemMenuMapper.selectAll();
	}

	public int updateByPrimaryKey(SystemMenu record) {
		return systemMenuMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = systemMenuMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SystemMenu> result = systemMenuMapper.queryPageDataResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<SystemMenu> queryTree() {
		return systemMenuMapper.queryTree();
	}

	@Override
	public List<SystemMenu> queryForRole() {
		return systemMenuMapper.queryTree();
	}

	@Override
	public List<Long> queryMenuIdsListForRole(Long roleId) {
		return systemMenuMapper.systemMenuMapper(roleId);
	}

	@Override
	public List<SystemMenu> indexMenu() {
		List<SystemMenu> userTree = systemMenuMapper.queryTree();
		Employee current = (Employee) SecurityUtils.getSubject().getPrincipal();
		if(!current.isAdmin()){
			List<Long> ids = systemMenuMapper.queryEmployeeMenuIdList(current.getId());
			filterMenu(userTree,ids);
		}
		return userTree;
	}

	private void filterMenu(List<SystemMenu> userTree, List<Long> ids) {
		SystemMenu menu;
		for(int i=userTree.size()-1;i>=0;i--){
			menu = userTree.get(i);
			if(!ids.contains(menu.getId())){
				userTree.remove(i);
			}else{
				if(menu.getChildren()!=null && menu.getChildren().size()>0){
					filterMenu(menu.getChildren(),ids);
				}
			}
		}
	}
}
