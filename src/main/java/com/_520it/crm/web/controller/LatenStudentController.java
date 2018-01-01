package com._520it.crm.web.controller;

import com._520it.crm.domain.Payment;
import com._520it.crm.domain.Student;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentQueryObject;
import com._520it.crm.service.ILatentStudentService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/latenStudent")
public class LatenStudentController {

	@Autowired
	private ILatentStudentService studentService;

	@RequiresPermissions("student:index")
	@PermissionName("潜在学员列表")
	@RequestMapping("")
	public String index(){
		return "latenStudent";
	}

	@RequestMapping("selectByStudentId")
	@ResponseBody
	public Student selectByStudentId(Long studentId){
		return studentService.selectByPrimaryKey(studentId);
	}

	@RequiresPermissions("student:list")
	@PermissionName("潜在学员数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(StudentQueryObject qo){
		PageResult result = studentService.queryPage(qo);
		return result;
	}

	@RequiresPermissions("student:list")
	@PermissionName("潜在学员数据")
	@RequestMapping("/listAll")
	@ResponseBody
	public List<Student> listAll(StudentQueryObject qo){
		return studentService.selectAll(qo);
	}



	@RequiresPermissions("student:save")
	@RequestMapping("/save")
	@ResponseBody
	@PermissionName("潜在学员新增")
	public AjaxResult save(Student student){
		AjaxResult result = null;
		try{
			studentService.insert(student);
			result = new AjaxResult(true,"保存成功");
		}catch (RuntimeException e){
			result = new AjaxResult("保存失败," + e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员!");
		}
		return result;
	}


	@RequiresPermissions("student:update")
	@PermissionName("潜在学员更新")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Student student){
		AjaxResult result = null;
		try{
			studentService.updateByPrimaryKey(student);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员!");
		}
		return result;
	}

	@RequiresPermissions("student:update")
	@PermissionName("潜在学员转正")
	@RequestMapping("/turn")
	@ResponseBody
	public AjaxResult turn(Student student, Payment payment){
		AjaxResult result = null;
		try{
			studentService.turn(student,payment);
			result = new AjaxResult(true,"转正成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("转正失败,请联系管理员!");
		}
		return result;
	}

	@RequestMapping("/queryStudentForIncome")
	@ResponseBody
	public List<Student> queryStudentForIncome(){
		List<Student> result = studentService.queryStudentForIncome();
		return result;
	}


	@RequiresPermissions("student:update")
	@PermissionName("潜在学员移交历史")
	@RequestMapping("/move")
	@ResponseBody
	public AjaxResult move(Student student,String reason,Long targetId){
		AjaxResult result = null;
		try{
			studentService.move(student,reason,targetId);
			result = new AjaxResult(true,"移交成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("移交失败,请联系管理员!");
		}
		return result;
	}

	@RequiresPermissions("student:put")
	@PermissionName("潜在学员放入客户池")
	@RequestMapping("/put")
	@ResponseBody
	public AjaxResult put(Long studentId){
		AjaxResult result = null;
		try{
			studentService.put(studentId);
			result = new AjaxResult(true,"放入客户池成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("放入客户池失败,请联系管理员!");
		}
		return result;
	}

	@RequiresPermissions("student:input")
	@PermissionName("潜在学员导入excel")
	@RequestMapping("/inport")
	@ResponseBody
	public AjaxResult input(MultipartFile excel){
		AjaxResult result = null;
		try{
			studentService.inport(excel);
			result = new AjaxResult(true,"导入成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("导入失败,请重试!");
		}
		return result;
	}

	@RequiresPermissions("student:input")
	@PermissionName("下载excel模板")
	@RequestMapping("/download")
	@ResponseBody
	public AjaxResult download(HttpServletResponse resp, HttpServletRequest req){
		AjaxResult result = null;
		try{
			studentService.download(req,resp);
			result = new AjaxResult(true,"导入成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("导入失败,请重试!");
		}
		return result;
	}
}
