package com._520it.crm.service;

import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IDepartmentService {

    void delete(Long id);

    void save(Department department);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    void update(Department department);

    PageResult queryPage(QueryObject qo);

}
