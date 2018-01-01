$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var permissionDatagrid;
	permissionDatagrid = $("#permission_datagrid");
	/*
	 * 初始化数据表格 
	 */
	permissionDatagrid.datagrid({
		url:"/permission/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pageSize:20,
		pageList:[10,20,30],
		pagination:true,
		fitColumns:true,
		toolbar:'#permission_datagrid_tb',
		columns:[
			[
            	{field:'name',width:10,align:'center',title:'名称'},
                {field:'resource',width:10,align:'center',title:'表达式'}
			]
		]
	});
	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			load:function(){
				$.messager.confirm("温馨提示","您确定需要重新加载权限吗吗？",function(yes){
					if(yes){
						$.get("/permission/load",function(data){
							if(data.success){
								$.messager.alert("温馨提示",data.msg,"info",function(){
									permissionDatagrid.datagrid("reload");
								});
							}else{
								$.messager.alert("温馨提示",data.msg,"error");
							}
						},"json")
					}
				});
			}
	}
});
