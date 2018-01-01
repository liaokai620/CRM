package com._520it.crm.web.controller;

import com._520it.crm.domain.Exam;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ExamQueryObject;
import com._520it.crm.service.IExamService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	private IExamService examService;

	@RequiresPermissions("exam:index")
	@PermissionName("考试成绩列表")
	@RequestMapping("")
	public String index(){
		return "exam";
	}


	@RequiresPermissions("exam:list")
	@PermissionName("考试成绩数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(ExamQueryObject qo){
		PageResult result = examService.queryPage(qo);
		return result;
	}

	@RequiresPermissions("exam:save")
	@RequestMapping("/save")
	@ResponseBody
	@PermissionName("考试成绩新增")
	public AjaxResult save(Exam exam){
		AjaxResult result = null;
		try{
			examService.insert(exam);
			result = new AjaxResult(true,"登记成功,可以在考试管理中查看");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("登记失败,请联系管理员!");
		}
		return result;
	}

	@RequiresPermissions("exam:update")
	@PermissionName("考试成绩更新")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Exam exam){
		AjaxResult result = null;
		try{
			examService.updateByPrimaryKey(exam);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员!");
		}
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long examId){
		AjaxResult result = null;
		try{
			examService.deleteByPrimaryKey(examId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员!");
		}
		return result;
	}

	@RequestMapping("/result")
	@ResponseBody
	public AjaxResult result(String result,long id){
		try{
			examService.result(result,id);
			return  new AjaxResult(true,"处理成功");
		}catch(Exception e){
			e.printStackTrace();
			return new AjaxResult("处理失败,请联系管理员!");
		}
	}

	@RequestMapping("/listAll")
	@ResponseBody
	public List<Exam> listAll(){
		List<Exam> result = examService.selectAll();
		return result;
	}


}
