$(function(){
	//对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var classroomDatagrid,classroomEditBtnAndQuitBtn,classroomDialog,classroomForm,classroomSearchBtn;
	classroomDatagrid = $("#classroom_datagrid");
	classroomEditBtnAndQuitBtn = $("#classroom_editBtn,#classroom_quitBtn");
	classroomDialog = $("#classroom_dialog");
	classroomForm = $("#classroom_form");
	classroomSearchBtn = $("#searchBtn");
	//数据表格
	classroomDatagrid.datagrid({
		fit:true,
		singleSelect:true,
		pagination:true,
		url:'/classroom/list',
		fitColumns:true,
		toolbar:'#classroom_datagrid_tb',
        pageSize:20,
		columns:[
			[
				{field:'id',align:'center',width:10,title:'编号'},
				{field:'name',align:'center',width:10,title:'名称'},
				{field:'stite',align:'center',width:10,title:'教室地址'},
				{field:'seatNumber',align:'center',width:10,title:'座位数量'},
				{field:'state',align:'center',width:10,title:'状态',formatter:stateFormatter},
			]
		]
	});
	//对话框
	classroomDialog.dialog({
		width:250,
		height:380,
		buttons:'#classroom_dialog_bt',
		closed:true
	});
    classroomSearchBtn.textbox({
		width:230,
		label:"关键字:",
        labelWidth:50,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            classroomDatagrid.datagrid("load",{
            	keyword:keyword
			});
		}
    });

	//对按钮进行统一事件监听
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	
	//方法统一管理起来]
	var cmdObj = {
			add:function(){
				//1.清空表单数据
				classroomForm.form("clear");
				//2.设置对话框的标题
				classroomDialog.dialog("setTitle","新增");
				//3.打开对话框
				classroomDialog.dialog("open");
			},
			edit:function(){
				var rowData = classroomDatagrid.datagrid("getSelected");
				if(rowData){
					//1.清空表单数据
					classroomForm.form("clear");
					//2.设置对话框的标题
					classroomDialog.dialog("setTitle","编辑");
					//3.打开对话框
					classroomDialog.dialog("open");
                    classroomForm.form("load",rowData);//基于同名匹配规则
				}else{
                    $.messager.alert("温馨提示","请选择一条需要修改的数据.","warning");
                }
				
			},
			quit:function(){
				var rowData = classroomDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除该教室吗?",function(yes){
						if(yes){
							$.get("/classroom/delete?id="+rowData.id,function(data){
								if(data.success){
                                    classroomDatagrid.datagrid("reload");
									$.messager.alert("温馨提示",data.msg,"info");
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的教室记录.","warning");
				}
			},
			reload:function(){
				//刷新数据表格
				classroomDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/classroom/update";
				}else{
					url = "/classroom/save";
				}
				classroomForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								classroomDialog.dialog("close");
								classroomDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function(){
                classroomDialog.dialog("close");
			},
			//办理启用教室
       	 useRoom:function(){
				var rowData = classroomDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要启用该教室吗?",function(yes){
						if(yes){
							$.get("/classroom/useRoom?id="+rowData.id,function(data){
								if(data.success){
                                    classroomDatagrid.datagrid("reload");
									$.messager.alert("温馨提示",data.msg,"info");
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要启用的教室.","warning");
				}
			}
	}
});


function stateFormatter(value){
	if(value==1){
		return "<font color='red'>停用</font>";
	}else if(value==0){
		return "<font color='greed'>启用</font>";
	}
}
