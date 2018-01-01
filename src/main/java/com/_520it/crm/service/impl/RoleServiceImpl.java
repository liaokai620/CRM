package com._520it.crm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.RoleMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public int insert(Role record) {
		int count = roleMapper.insert(record);
		//处理中间表的关系
		for(Permission p:record.getPermissions()){
			roleMapper.insertRelation(record.getId(),p.getId());
		}
		return count;
	}

	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	public int updateByPrimaryKey(Role record) {
		int count = roleMapper.updateByPrimaryKey(record);
		//删除外键关系
		roleMapper.deleteRelation(record.getId());
		//新增新的外键关系
		//处理中间表的关系
		for(Permission p:record.getPermissions()){
			roleMapper.insertRelation(record.getId(),p.getId());
		}
		return count;
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = roleMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Role> result = roleMapper.queryPageDataResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<Long> queryRoleIdListForEmployeeForm(Long employeeId) {
		return roleMapper.queryRoleIdListForEmployeeForm(employeeId);
	}

	@Override
	public List<Role> queryForEmployee(Long empId) {
		return roleMapper.queryForEmployee(empId);
	}

	@Override
	public void addMenu(ArrayList<Long> ids, Long roleId) {
		//把中间表的关系删除
		roleMapper.deleteMenuRelation(roleId);
		//循环ids集合,往中间表插入关系
		for(Long id:ids){
			roleMapper.insertMeneRelation(id,roleId);
		}
	}
}
