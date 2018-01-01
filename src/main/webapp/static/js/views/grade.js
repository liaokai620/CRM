$(function(){
	//对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var gradeDatagrid,gradeEditBtnAndQuitBtn,gradeDialog,gradeForm,gradeSearchBtn,teacherForm,teacherDialog;
	gradeDatagrid = $("#grade_datagrid");
	gradeEditBtnAndQuitBtn = $("#grade_editBtn,#grade_quitBtn");
	gradeDialog = $("#grade_dialog");
	gradeForm = $("#grade_form");
	gradeSearchBtn = $("#searchBtn");
	teacherForm = $("#grade_teacherForm");
    teacherDialog = $("#grade_teacherDialog");
	//数据表格
	gradeDatagrid.datagrid({
		fit:true,
		singleSelect:true,
		pagination:true,
		url:'/grade/list',
		fitColumns:true,
		toolbar:'#grade_datagrid_tb',
        pageSize:20,
		columns:[
			[
				{field:'id',align:'center',width:10,title:'编号'},
				{field:'name',align:'center',width:10,title:'班级名称'},
				{field:'number',align:'center',width:10,title:'班级人数'},
				{field:'beginTime',align:'center',width:10,title:'开班时间'},
				{field:'classTeacher',align:'center',width:10,title:'班主任',formatter:teacherFormatter},
				{field:'course',align:'center',width:10,title:'课程',formatter:courseFormatter},
				{field:'state',align:'center',width:10,title:'状态',formatter:stateFormatter}
			]
		]
	});
	//对话框
	gradeDialog.dialog({
		width:250,
		height:380,
		buttons:'#grade_dialog_bt',
		closed:true
	});
	//分配班主任的对话框
    teacherDialog.dialog({
		width:250,
		height:200,
		buttons:'#grade_dialog_allot',
		closed:true
	});
    gradeSearchBtn.textbox({
		width:230,
		label:"关键字:",
        labelWidth:50,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            gradeDatagrid.datagrid("load",{
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
				gradeForm.form("clear");
				//2.设置对话框的标题
				gradeDialog.dialog("setTitle","新增");
				//3.打开对话框
				gradeDialog.dialog("open");
			},
			edit:function(){
				var rowData = gradeDatagrid.datagrid("getSelected");
				if(rowData){
					//1.清空表单数据
					gradeForm.form("clear");
					//2.设置对话框的标题
					gradeDialog.dialog("setTitle","新增");
					//3.打开对话框
					gradeDialog.dialog("open");
                    //特殊数据的处理
                    if(rowData.classTeacher){

                    	rowData["classTeacher.id"] = rowData.classTeacher.id;
					}
                    if(rowData.course){

                    	rowData["course.id"] = rowData.course.id;
					}
                    gradeForm.form("load",rowData);//基于同名匹配规则
				}
				
			},
			quit:function(){
				var rowData = gradeDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除该班级吗?",function(yes){
						if(yes){
							$.get("/grade/delete?id="+rowData.id,function(data){
								if(data.success){
                                    gradeDatagrid.datagrid("reload");
									$.messager.alert("温馨提示",data.msg,"info");
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的班级记录.","warning");
				}
			},
			reload:function(){
				//刷新数据表格
				gradeDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/grade/update";
				}else{
					url = "/grade/save";
				}
				gradeForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								gradeDialog.dialog("close");
								gradeDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});

			},
			cancel:function(){
                gradeDialog.dialog("close");
                teacherDialog.dialog("close");
			},
        	allot:function(){
                var rowData = gradeDatagrid.datagrid("getSelected");
                if(rowData){
                    teacherForm.form("submit",{
                        url:"/grade/allot",
                        success:function(data){
                            data = $.parseJSON(data);
                            if(data.success){
                                //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                                $.messager.alert("温馨提示",data.msg,"info",function(){
                                    teacherDialog.dialog("close");
                                    gradeDatagrid.datagrid("reload");
                                });
                            }else{
                                $.messager.alert("温馨提示",data.msg,"error");
                            }
                        }
                    });
                }
            },
        	//分配班主任
        	allotTeacher:function () {
                var rowData = gradeDatagrid.datagrid("getSelected");
                if(rowData) {
                    //1.清空表单数据
                    teacherForm.form("clear");
                    //2.设置对话框的标题
                    teacherDialog.dialog("setTitle", '为' + rowData.name + '分配班主任');
                  	//回显班主任
                    if(rowData.classTeacher){
                        rowData["classTeacher.id"] = rowData.classTeacher.id;
                    }
                    teacherForm.form("load",rowData);//基于同名匹配规则
                    //3.打开对话框
                    teacherDialog.dialog("open");
                }else{
                    $.messager.alert("温馨提示","请选择需要分配班主任的班级.","warning");
                }
        	},
        	//办理结业
                graduate:function(){
                    var rowData = gradeDatagrid.datagrid("getSelected");
                    if(rowData){
                        $.messager.confirm("温馨提示","您确定需要给该班级办理结业吗?",function(yes){
                            if(yes){
                                $.get("/grade/graduate?id="+rowData.id,function(data){
                                    if(data.success){
                                        gradeDatagrid.datagrid("reload");
                                        $.messager.alert("温馨提示",data.msg,"info");
                                    }else{
                                        $.messager.alert("温馨提示",data.msg,"error");
                                    }
                                },"json")
                            }
                        });
                    }else{
                        $.messager.alert("温馨提示","请选择需要办理结业的班级.","warning");
                    }
                }
	}
});


function stateFormatter(value){
	if(value==1){
		return "<font color='red'>已结业</font>";
	}else if(value==0){
		return "<font color='greed'>未毕业</font>";
	}
}

//班主任
function teacherFormatter(value){
    if(value){
        return value.username;
    }
    return "<font color='red'>未分配班主任</font>";
}

//课程
function courseFormatter(value){
    if(value){
        return value.name;
    }
    return value;
}


