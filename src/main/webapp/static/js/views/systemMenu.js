$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var systemMenuDatagrid,systemMenuDialog,systemMenuForm,systemMenuTree,parentTree,systemMenuDatagridBtn;
	systemMenuDatagrid = $("#systemMenu_datagrid");//子菜单数据表格
	systemMenuDialog = $("#systemMenu_dialog");//子菜单的新增/编辑对话框
	systemMenuForm = $("#systemMenu_form");//子菜单的表单
	systemMenuTree = $("#systemMenuTree");//菜单树
	parentTree = $("#parentTree");//父菜单面板中的父菜单录入框
	parentMenuFrom = $("#parentMenuFrom");//父菜单面板的表单
	systemMenuDatagridBtn =$("#systemMenu_datagrid_tb a");//子菜单数据表格中的按钮.
    systemMenuDatagridBtn.linkbutton("disable");
	/*
	 * 初始化数据表格 
	 */
	systemMenuDatagrid.datagrid({
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#systemMenu_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	systemMenuDialog.dialog({
		width:300,
		height:200,
		closed:true,
		buttons:'#systemMenu_dialog_bt',
		modal:true
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
	 * 初始化菜单树
	 */
	systemMenuTree.tree({
		url:'/systemMenu/queryTree',
		onClick:function(node){
			/**
			 * 1.清空父菜单面板中的表单
			 * 2.获取当前选中元素的父菜单数据，并给node数据添加parent.id属性
			 * 3.将node的数据注入到表单中
			 */
			parentMenuFrom.form("clear");
			var parentNode = systemMenuTree.tree("getParent",node.target);
			if(parentNode)
				node["parent.id"] = parentNode.id
			node["url"] = node.attributes.url;
			parentMenuFrom.form("load",node);
			/**
			 * 1.更改右侧子菜单数据表格中的URL地址,根据当前选中的菜单ID去查询该菜单下面的子菜单
			 * 2.调用子菜单数据表格的load方法重新加载数据
			 */
			systemMenuDatagrid.datagrid("options").url = "/systemMenu/list?parentId="+node.id;
			systemMenuDatagrid.datagrid("load");
			//******************************************************************
			/**
			 * 子菜单数据表格中的按钮默认时禁用的,当左侧选择了对应的父菜单后，将子菜单表格中的按钮点亮
			 */
			systemMenuDatagridBtn.linkbutton("enable");
		},
		onLoadSuccess:function(node,data){
			/**
			 * 1.将菜单树加载的数据复制给父菜单面板中的父菜单下拉选择框。
			 * 目的是,一旦增加或者修改菜单元素,两边的树都保持一致.所有都共用同一份数据.
			 * onLoadSuccess:是当菜单树数据加载完毕后触发的事件
			 */
            data = $.extend( true,[],data);
            console.log(1111);
			parentTree.combotree("loadData",data);
		}
	});
	/**
	 * 定义父菜单面板中的父菜单下拉框组件
	 */
	parentTree.combotree({
		/**
		 * 该方法的目的:避免用户选择了自己或者自己的儿子作为自己的父菜单.逻辑是不对的。（你儿子是你的爸爸？）
		 */
		onClick:function(node){
			//node：我们在父菜单下拉列表选择的节点数据
			//获取到当前元素的Id值
			var id = $("#parentMenuFrom input[name='id']").val();
			//判断在父菜单下拉列表中选择的节点数据是不是自己/自己的子孙？
			//返回结果为booeal,true:表示数据是合理的,false:表示数据是不合理的.
			var result = cmdObj.validateSelectNode(id,node);
			if(!result){
				//如果数据不合理，提示用户.
				 $.messager.show({
					 title:'温馨提示',
					 msg:'不能选中自己/子孙节点作为自己的父菜单！！！',
					 width:300,
					 showType:'fade',
					 timeout:2000,
					 style:{right:'',bottom:''}
				 });
				//因为这个值合理,所以把这个值清楚.
				parentTree.combotree("clear");
			}
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			/**
			 * 判断在父菜单下拉列表中选择的节点数据是不是自己/自己的子孙？
			 * 返回结果为booeal,true:表示数据是合理的,false:表示数据是不合理的.
			 * 使用递归判断.
			 */
			 validateSelectNode:function(id,node){
				 if(id==node.id){
					 return false;
				 }
				 var treeComponent = parentTree.combotree('tree');
				 var parentNode = treeComponent.tree("getParent",node.target);
				 if(parentNode){
					 return arguments.callee(id,parentNode);
				 }
				 return true;
			 },
			 add:function(){
				systemMenuForm.form("clear");
				systemMenuDialog.dialog("setTitle","新增");
				systemMenuDialog.dialog("open");
				/**
				 * 子菜单的新增的时候需要在录入框中显示父菜单是谁,以及需要把父菜单的id放入到表单中.
				 * 如果没有父菜单,那表格中的父菜单文本就显示为"根目录"
				 */
				var treeRowData = systemMenuTree.tree("getSelected");
				if(treeRowData){
					$("#systemMenu_form input[name='parent.id']").val(treeRowData.id);
					$("#systemMenu_form_parentMenu").val(treeRowData.text);
				}else{
					$("#systemMenu_form_parentMenu").val("根目录");
				}
			},
			edit:function(){
				var rowData = systemMenuDatagrid.datagrid("getSelected");
				if(rowData){
					systemMenuForm.form("clear");
					systemMenuDialog.dialog("setTitle","编辑");
					systemMenuDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					systemMenuForm.form("load",rowData);
					/**
					 * 子菜单的编辑的时候需要在录入框中显示父菜单是谁,以及需要把父菜单的id放入到表单中.
					 * 如果没有父菜单,那表格中的父菜单文本就显示为"根目录"
					 */
					var treeRowData = systemMenuTree.tree("getSelected");
					if(treeRowData){
						$("#systemMenu_form input[name='parent.id']").val(treeRowData.id);
						$("#systemMenu_form_parentMenu").val(treeRowData.text);
					}else{
						$("#systemMenu_form_parentMenu").val("根目录");
					}
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = systemMenuDatagrid.datagrid("getSelected");
				if(rowData){
					/**
					 * 在删除之前，如果用户需要删除的菜单下面还有其他的子菜单,这种情况我们是禁止用户删除的.给出提示
					 */
					var node = systemMenuTree.tree('find', rowData.id);
					var children = systemMenuTree.tree("getChildren",node.target);
					if(children.length>0){
						 $.messager.show({
							 title:'温馨提示',
							 msg:'该节点下面含有子节点,不能删除!!!',
							 showType:'fade',
							 timeout:2000,
							 style:{right:'',bottom:''}
						 });
						 return;
					}
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/systemMenu/delete?systemMenuId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										/**
										 * 下面这段代码的目的:
										 * 		在删除后,因为有些菜单已经被删除了.但是systemMenuTree的数据是没有发生改变的.
										 * 		所以我们需要同步一下systemMenuTree最新的数据,而且同步之后应该选择会之前选择节点.
										 * 
										 * 有同学会问:为什么不使用systemMenuTree.tree("reload");方法呢？
										 * 			因为EasyUI中所有组件发送请求都是异步的请求,相当于调用完之后就直接执行后面的代码,
										 * 			我们需要等树加载完毕后才去选中之前选中的菜单.此时使用异步方式就实现不了了.
										 * 			所以我们就自己发送同步的请求,请求之后再把数据加载到systemMenuTree中.
										 */
										var treeDate = $.ajax({
											  url: systemMenuTree.tree("options").url,
											  async: false
										}).responseText;
										treeDate = $.parseJSON(treeDate);
										systemMenuTree.tree("loadData",treeDate);
										var node = systemMenuTree.tree('find', $("#systemMenu_form input[name='parent.id']").val());
										systemMenuTree.tree('select', node.target);
										/**
										 * 子菜单数据表格的刷新
										 */
										systemMenuDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的数据!","warning");
				}
			},
			reload:function(){
				systemMenuDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("#systemMenu_form [name='id']").val();
				if(idVal){
					url = "/systemMenu/update"
				}else{
					url = "/systemMenu/save";
				}
				systemMenuForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								systemMenuDialog.dialog("close");
								/**
								 * 和删除的原理是一致的,都需要自己发送同步的请求加载数据.
								 */
								var treeDate = $.ajax({
									  url: systemMenuTree.tree("options").url,
									  async: false
								}).responseText;
								treeDate = $.parseJSON(treeDate);
								systemMenuTree.tree("loadData",treeDate);
								var node = systemMenuTree.tree('find', $("#systemMenu_form input[name='parent.id']").val());
								systemMenuTree.tree('select', node.target);
								/**
								 * 子菜单数据表格的刷新
								 */
								systemMenuDatagrid.datagrid("reload");

							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				systemMenuDialog.dailog("close");
			},
			parentsave:function(){
				var url;
				var msg;
				var idVal = $("#parentMenuFrom [name='id']").val();
				if(idVal){
					url = "/systemMenu/update";
					msg="您确定需要修改选中菜单吗？";
				}else{
					url = "/systemMenu/save";
					msg="您确定需要新增菜单吗？";
				}
				$.messager.confirm("温馨提示",msg,function(yes){
					if(yes){
						parentMenuFrom.form("submit",{
							url:url,
							success:function(data){
								data = $.parseJSON(data);
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										var treeDate = $.ajax({
											  url: systemMenuTree.tree("options").url,
											  async: false
										}).responseText;
										treeDate = $.parseJSON(treeDate);
										systemMenuTree.tree("loadData",treeDate);
										if(idVal){
											var node = systemMenuTree.tree('find', $("#parentMenuFrom input[name='id']").val());
											systemMenuTree.tree('select', node.target);
										}
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							}
						});
					}
				});
			},
			removeParent:function(){
				$.messager.confirm("温馨提示","您确定清空父元素吗？",function(yes){
					if(yes){
						parentTree.combotree("clear");
					}
				});
			},
			menutreedelete:function(){
				var node = systemMenuTree.tree("getSelected");
				if(node){
					var children = systemMenuTree.tree("getChildren",node.target);
					if(children.length>0){
						 $.messager.show({
							 title:'温馨提示',
							 msg:'该节点下面含有子节点,不能删除!!!',
							 showType:'fade',
							 timeout:2000,
							 style:{right:'',bottom:''}
						 });
						 return;
					}
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/systemMenu/delete?systemMenuId="+node.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										cmdObj.menutreereload();
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的菜单!","warning");
				}
			},
			menutreereload:function(){
				systemMenuTree.tree("reload");
				parentMenuFrom.form("clear");
				systemMenuDatagrid.datagrid("loadData",{rows:[]});
				systemMenuDatagridBtn.linkbutton("disable");
			}
	}
});
function iconFormatter(value,record,index){
	return '<span class="tree-icon tree-file '+value+'"></span>';
}
