package com._520it.crm.web.controller;

import com._520it.crm.domain.TurnOverHistory;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TurnOverHistoryQueryObject;
import com._520it.crm.service.ITurnOverHistoryService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/history")
public class TurnOverHistoryController {
	@Autowired
	private ITurnOverHistoryService historyService;

	@RequiresPermissions("history:index")
	@PermissionName("考试成绩列表")
	@RequestMapping("")
	public String index(){
		return "history";
	}


	@RequiresPermissions("history:list")
	@PermissionName("考试成绩数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(TurnOverHistoryQueryObject qo){
		PageResult result = historyService.queryPage(qo);
		return result;
	}

	@RequiresPermissions("history:save")
	@RequestMapping("/save")
	@ResponseBody
	@PermissionName("考试成绩新增")
	public AjaxResult save(TurnOverHistory history){
		AjaxResult result = null;
		try{
			historyService.insert(history);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员!");
		}
		return result;
	}


	@RequestMapping("/listAll")
	@ResponseBody
	public List<TurnOverHistory> listAll(){
		List<TurnOverHistory> result = historyService.selectAll();
		return result;
	}
}
