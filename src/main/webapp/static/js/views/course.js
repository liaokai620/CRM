$(function(){
	//对页面中的元素进行抽取.
	//按钮在JS统一进行监听
	var courseDatagrid,courseEditBtnAndQuitBtn,courseDialog,courseForm,courseSearchBtn,courseUploadForm,date,gradeDatagrid;
	courseDatagrid = $("#course_datagrid");
	courseEditBtnAndQuitBtn = $("#course_editBtn,#course_quitBtn");
	courseDialog = $("#course_dialog");
	courseForm = $("#course_form");
	courseSearchBtn = $("#searchBtn");
    courseUploadForm = $("#courseUploadForm");
    date = $("#date");
    gradeDatagrid = $("#grade_datagrid");
	//课程表数据表格
	courseDatagrid.datagrid({
		fit:true,
		singleSelect:true,
		pagination:true,
		url:'/course/list',
		fitColumns:true,
		toolbar:'#course_datagrid_tb',
        pageSize:30,
        columns:[
			[
				{field:'id',align:'center',width:10,title:'编号'},
				{field:'name',align:'center',width:10,title:'课程名称'},
				{field:'schoolTime',align:'center',width:10,title:'上课时间'},
				{field:'grade',align:'center',width:10,title:'上课班级',formatter:gradeFormatter},
				{field:'classroom',align:'center',width:10,title:'教室',formatter:classroomFormatter},
				{field:'teacher',align:'center',width:10,title:'上课老师',formatter:teacherFormatter},
                {field:'classTeacher',align:'center',width:10,title:'班主任',formatter:csTeacherFormatter},
				{field:'remark',align:'center',width:10,title:'备注'},
				{field:'state',align:'center',width:10,title:'状态',formatter:stateFormatter}
			]
		]
	});

	//班级数据表格
    gradeDatagrid.datagrid({
        fit:true,
        height:500,
        singleSelect:true,
        pagination:true,
        url:'/grade/list',
        fitColumns:true,
        rownumbers:true,
        pageSize:20,
        columns:[
            [
                {field:'name',align:'center',width:10,title:'点击要查询的班级名称'}
            ]
        ],
        onClickRow:function (index, row) {
			var gradeId=row.id;
            courseDatagrid.datagrid("load",{
                gradeId:gradeId
            });

        }
    });

	//对话框
	courseDialog.dialog({
		width:250,
		height:500,
		buttons:'#course_dialog_bt',
		closed:true
	});
    courseSearchBtn.combobox({
		width:230,
		label:"班级:",
        labelWidth:50,
        prompt:"选择查询的班级",
        limitToList:true,
        buttonText:'快查',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            courseDatagrid.datagrid("load",{
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

	//日历选择时间;
    date.calendar({
        width:300,
        onSelect: function(date){
        	//转换日历选择的参数
           var newDate= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();

            courseDatagrid.datagrid("load",{
                newDate:newDate
            });
        }
    });






    //方法统一管理起来]
	var cmdObj = {
			add:function(){
				//1.清空表单数据
				courseForm.form("clear");
				//2.设置对话框的标题
				courseDialog.dialog("setTitle","新增课程");
				//3.打开对话框
				courseDialog.dialog("open");
			},
			edit:function(){
				var rowData = courseDatagrid.datagrid("getSelected");
				if(rowData){
					//1.清空表单数据
					courseForm.form("clear");
					//2.设置对话框的标题
					courseDialog.dialog("setTitle","编辑课程");
					//3.打开对话框
					courseDialog.dialog("open");
                    //回显班主任
                    if(rowData.classTeacher){

                    	rowData["classTeacher.id"] = rowData.classTeacher.id;
					}
                    //回显上课老师
                    if(rowData.teacher){

                    	rowData["teacher.id"] = rowData.teacher.id;
					}

					//回显班级
                    if(rowData.grade){
                    	rowData["grade.id"] = rowData.grade.id;
					}
					//回显班级
                    if(rowData.grade){
                    	rowData["grade.id"] = rowData.grade.id;
					}

					//回显教室
                    if(rowData.classroom){
                    	rowData["classroom.id"] = rowData.classroom.id;
					}
                    courseForm.form("load",rowData);//基于同名匹配规则
				}else{
                    $.messager.alert("温馨提示","请选择一条需要修改的数据.","warning");
                }
				
			},
			quit:function(){
				var rowData = courseDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除该课程吗?",function(yes){
						if(yes){
							$.get("/course/delete?id="+rowData.id,function(data){
								if(data.success){
                                    courseDatagrid.datagrid("reload");
									$.messager.alert("温馨提示",data.msg,"info");
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的课程记录.","warning");
				}
			},
			reload:function(){

				//刷新数据表格
				courseDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/course/update";
				}else{
					url = "/course/save";
				}
				courseForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								courseDialog.dialog("close");
								courseDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function(){
                courseDialog.dialog("close");
			},
		    //快查的函数;
		    query:function(){
				//获取选择的条件id数据;
				var classroomId = $("[name='classroomId']").val();
				var gradeId = $("[name='gradeId']").val();
				var teacherId = $("[name='teacherId']").val();
				var beginTime = $("[name='beginTime']").val();
				var endTime = $("[name='endTime']").val();
				if(!classroomId && !gradeId&& !teacherId){

					$.messager.alert("温馨提示","请至少选择一个查询项.","warning");
					return;
				}

                //将查询条件赋值给CourseQueryObject的对应属性;
				courseDatagrid.datagrid("load",{
                    classroomId:classroomId,
                    gradeId:gradeId,
                    teacherId:teacherId,
                    beginTime:beginTime,
                    endTime:endTime
                });



			},
		    //时间查询;
       		 queryDate:function(){
				var beginTime = $("[name='beginTime']").val();
				var endTime = $("[name='endTime']").val();
				if(!beginTime && !endTime){

					$.messager.alert("温馨提示","请至少选择一个日期条件.","warning");
					return;
				}
                //将查询条件赋值给CourseQueryObject的对应属性;
				courseDatagrid.datagrid("load",{
                    beginTime:beginTime,
                    endTime:endTime
                });

			},
	}
});

//状态
function stateFormatter(value){
	if(value==1){
		return "<font color='red'>已上</font>";
	}else if(value==0){
		return "<font color='greed'>未上</font>";
	}
}

//教室
function classroomFormatter(value){
    if(value){
        return value.name;
    }
    return value;
}

//班主任
function teacherFormatter(value){
    if(value){
        return value.username;
    }
    return "<font color='red'>未分配老师</font>";
}
//班主任
function csTeacherFormatter(value){
    if(value){
    	console.log(value)
        return value.username;
    }
    return "<font color='red'>未分配班主任</font>";
}

//班级
function gradeFormatter(value){
    if(value){
        return value.name;
    }
    return value;
}

