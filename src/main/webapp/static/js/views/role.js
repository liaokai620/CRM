$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var roleDatagrid,roleDialog,roleForm,allPermissions,selfPermissions,roleMenuTree;
	roleDatagrid = $("#role_datagrid");
	roleDialog = $("#role_dialog");
	roleForm = $("#role_form");
	allPermissions = $("#allPermissions")
	selfPermissions = $("#selfPermissions");
	roleMenuTree = $("#role_menuTree");
	/*
	 * 初始化树组件
	 * 
	 */
	roleMenuTree.tree({
		url:'/systemMenu/queryForRole',
		checkbox:true
	});
	/*
	 * 初始化数据表格 
	 */
	roleDatagrid.datagrid({
		url:"/role/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#role_datagrid_tb',
		columns:[
			[
            	{field:'sn',width:10,align:'center',title:'编号'},
                {field:'name',width:10,align:'center',title:'名称'}
			]
		],
		onClickRow:function(rowIndex, rowData){
			//修改面板的标题头
			$("#role_menuTitle").panel("setTitle","角色["+rowData.name+"]的菜单");
			//需要回显该角色的菜单./
			//获取所有的菜单的集合
			var menuData = roleMenuTree.tree("getRoots");
			//HR有的节点
			//[1,6,25]--->List<Long>
			//需要做同步的操作需要使用$.ajax
			var menuIds = $.ajax({
				  url: "/systemMenu/queryMenuIdsListForRole?roleId="+rowData.id,
				  async: false//同步
			}).responseText;
			menuIds = $.parseJSON(menuIds);
			checkMenuTree(menuData,menuIds);
		}
	});
	function checkMenuTree(menuData,menuIds){
		for(var i=0;i<menuData.length;i++){
			if($.inArray(menuData[i].id,menuIds)>=0){
				roleMenuTree.tree("check",menuData[i].target);
			}else{
				roleMenuTree.tree("uncheck",menuData[i].target);
			}
			var children = roleMenuTree.tree("getChildren",menuData[i].target);
			if(children){
				arguments.callee(children,menuIds);
			}
		}
	}
	/*
	 * 初始化新增/编辑对话框 
	 */
	roleDialog.dialog({
		width:600,
		height:400,
		closed:true,
		buttons:'#role_dialog_bt'
	});
	/*
	 * 初始化数据表格
	 */
	var allData;
	allPermissions.datagrid({
		title:'所有权限',
		url:'/permission/queryPageDataForRoleForm',
		width:250,
		height:250,
		singleSelect:true,
		fitColumns:true,
		rownumbers:true,
		columns:[
		         [
		          	{field:'name',title:'权限名称',width:1,align:'center'}
		          ]
		],
		onDblClickRow:function(rowIndex, rowData){
			selfPermissions.datagrid("appendRow",rowData);
			allPermissions.datagrid("deleteRow",rowIndex);
		},
		onLoadSuccess:function(data){
			allData = jQuery.extend(true,{},data)
		}
	});
	selfPermissions.datagrid({
		title:'自身权限',
		width:250,
		height:250,
		singleSelect:true,
		fitColumns:true,
		singleSelect:true,
		fitColumns:true,
		rownumbers:true,
		columns:[
		         [
		          	{field:'name',title:'权限名称',width:1,align:'center'}
		          ]
		],
		onDblClickRow:function(rowIndex, rowData){
			allPermissions.datagrid("appendRow",rowData);
			selfPermissions.datagrid("deleteRow",rowIndex);
		},
		onLoadSuccess:function(data){
			//获取自身权限的所有id集合
			var ids = $.map(data.rows,function(item){
				return item.id;
			});
			//遍历所有的权限,如果发现id和ids中的数据一样,删除该条记录

			for(var i=(allData.rows.length-1);i>=0;i--){
				if($.inArray(allData.rows[i].id,ids)>=0){
					allPermissions.datagrid("deleteRow",i);
				}
			}
		}
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
			 add:function(){
				//把缓存中所有的数据重新加载allPermission的数据表格中
				allPermissions.datagrid("loadData",allData);
				selfPermissions.datagrid("loadData",{rows:[]})
				roleForm.form("clear");
				roleDialog.dialog("setTitle","新增");
				roleDialog.dialog("open");
			},
			edit:function(){
				var rowData = roleDatagrid.datagrid("getSelected");
				if(rowData){
					//把缓存中所有的数据重新加载allPermission的数据表格中
					allPermissions.datagrid("loadData",allData);
					selfPermissions.datagrid("loadData",{rows:[]});
					selfPermissions.datagrid("options").url = "/permission/selectPageForRoleFormByRoleId?roleId="+rowData.id;
					selfPermissions.datagrid("load");
					roleForm.form("clear");
					roleDialog.dialog("setTitle","编辑");
					roleDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					roleForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = roleDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/role/delete?roleId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										roleDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的数据!","warining");
				}
			},
			reload:function(){
				roleDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/role/update"
				}else{
					url = "/role/save";
				}
				roleForm.form("submit",{
					url:url,
					onSubmit:function(param){
						//获取selfPermissions所有的记录
						var rows = selfPermissions.datagrid("getRows");
						for(var i=0;i<rows.length;i++){
							param["permissions["+i+"].id"] = rows[i].id
						}
					},
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								roleDialog.dialog("close");
								roleDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				roleDialog.dialog("close");
			},
			menusave:function(){
				var rowData = roleDatagrid.datagrid("getSelected");
				if(rowData){
					//让用户确认是否需要添加菜单
					$.messager.confirm("温馨提示","您确定需要给角色["+rowData.name+"]添加如下菜单吗?",function(yes){
						if(yes){
							var ids = [];
							//获取所有被勾选的菜单的id集合
							var checkedNodes = roleMenuTree.tree("getChecked");
							var indeterminateNodes = roleMenuTree.tree('getChecked', 'indeterminate');
							$.each(checkedNodes,function(index,item){
								ids.push(item.id);
							});
							$.each(indeterminateNodes,function(index,item){
								ids.push(item.id);
							});
							//需要把菜单的集合和对应需要保存的角色ID传递到后台.
							$.post("/role/addMenu",{ids:ids,roleId:rowData.id},function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info");
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json");
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择右侧需要添加菜单的角色!","warning");
				}
			}
	}
});
