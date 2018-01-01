package com._520it.crm.mapper;

import com._520it.crm.domain.Permission;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    Long queryPageCount(QueryObject qo);

    List<Permission> queryPageDataResult(QueryObject qo);

    List<Permission> selectPageForRoleFormByRoleId(Long roleId);

    List<Permission> queryForEmployee(Long empId);

}