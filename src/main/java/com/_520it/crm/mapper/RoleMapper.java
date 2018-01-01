package com._520it.crm.mapper;

import com._520it.crm.domain.Role;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Long queryPageCount(QueryObject qo);

    List<Role> queryPageDataResult(QueryObject qo);

    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    List<Long> queryRoleIdListForEmployeeForm(Long employeeId);

    void insertMeneRelation(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    void deleteMenuRelation(Long roleId);

    List<Role> queryForEmployee(Long empId);

}