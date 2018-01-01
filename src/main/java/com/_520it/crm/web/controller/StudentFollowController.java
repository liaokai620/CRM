package com._520it.crm.web.controller;

import com._520it.crm.domain.StudentFollow;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentFollowQueryObject;
import com._520it.crm.service.IStudentFollowService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/studentFollow")
public class StudentFollowController {
	@Autowired
	private IStudentFollowService studentFollowService;

	@RequiresPermissions("studentFollow:index")
	@PermissionName("学员跟踪列表")
	@RequestMapping("")
	public String index(){

		return "studentFollow";
	}


	@RequiresPermissions("studentFollow:list")
	@PermissionName("学员跟踪数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(StudentFollowQueryObject qo ){
		PageResult result = studentFollowService.query(qo);
		return result;
	}
	@RequiresPermissions("studentFollow:save")
	@RequestMapping("/save")
	@ResponseBody
	@PermissionName("学员跟踪新增")
	public AjaxResult save(StudentFollow studentFollow){
		AjaxResult result = null;
		try{
			studentFollowService.insert(studentFollow);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员!");
		}
		return result;
	}
	@RequiresPermissions("studentFollow:update")
	@PermissionName("学员跟踪更新")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(StudentFollow studentFollow){
		AjaxResult result = null;
		try{
			studentFollowService.updateByPrimaryKey(studentFollow);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员!");
		}
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long followId){
		AjaxResult result = null;
		try{
			studentFollowService.deleteByPrimaryKey(followId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员!");
		}
		return result;
	}

	@RequestMapping("/audit")
	@ResponseBody
	public AjaxResult audit(StudentFollow follow){
		AjaxResult result = null;
		try{
			studentFollowService.audit(follow);
			result = new AjaxResult(true,"审核成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("审核失败,请联系管理员!");
		}
		return result;
	}


	@RequestMapping("/listAll")
	@ResponseBody
	public List<StudentFollow> listAll(){
		List<StudentFollow> result = studentFollowService.selectAll();
		return result;
	}
}
