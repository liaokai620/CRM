$(function () {
    //对页面中的元素进行抽取.
    //方法太凌乱,希望统一管理
    //按钮在JS统一进行监听
    var examDatagrid, examDialog, examForm;
    examDatagrid = $("#examDatagrid");
    examDialog = $("#examDialog");
    examForm = $("#examForm");
    //数据表格
    examDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/exam/list',
        fitColumns: true,
        toolbar: '#dataGridToolbar',
        toolbar: "#examToolbar",
        columns: [[
            {align: 'center', width: 10, title: '学员信息', colspan: 5},
            {field: 'sn', align: 'center', width: 10, title: '考试编号', rowspan: 2},
            {field: 'examTime', align: 'center', width: 10, title: '考试时间', rowspan: 2},
            {field: 'result', align: 'center', width: 10, title: '考试结果', rowspan: 2},
            {field: 'resolver', align: 'center', width: 10, title: '处理人', rowspan: 2, formatter: employeeFormatter},
        ], [
            {field: 'name', align: 'center', width: 8, title: '姓名'},
            {field: 'salesman', align: 'center', width: 8, title: '营销人员', formatter: salesmanFormatter},
            {field: 'qq', align: 'center', width: 8, title: 'QQ',},
            {field: 'tel', align: 'center', width: 8, title: '电话'},
            {field: 'wantGrade', align: 'center', width: 8, title: '意向班级', formatter: wantGradeFormatter},
        ]],
    });
    //对话框
    examDialog.dialog({
        width: 400,
        height: 380,
        buttons: '#dialogbuttons',
        closable: true,
        closed: true
    });
    //方法统一管理起来]
    var cmdObj = {
        add: function () {
            //1.清空表单数据
            examForm.form("clear");
            //2.设置对话框的标题
            examDialog.dialog("setTitle", "新增考试信息");

            //3.打开对话框
            examDialog.dialog("open");
        },
        edit: function () {
            var rowData = examDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                examForm.form("clear");
                //2.设置对话框的标题
                examDialog.dialog("setTitle", "新增");
                //3.打开对话框
                examDialog.dialog("open");
                //特殊数据的处理
                if (rowData.salesman) {
                    rowData["salesman.id"] = rowData.salesman.id;
                }
                //4.回显数据
                examForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?examId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }), "json";

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }

        },
        reload: function () {
            //刷新数据表格
            examDatagrid.datagrid("reload");
        },
        saveItem: function () {
            var url = "/exam/save";
            if ($("[name='id']").val()) {
                url = "/exam/update";
            }
            examForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            examDialog.dialog("close");
                            examDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        remove: function () {
            var rowData = examDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定删除该数据吗?", function (yes) {
                    if (yes) {
                        $.post("/exam/delete", {examId: rowData.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    examDatagrid.datagrid("reload")
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        cancel: function () {
            examDialog.dialog("close");
        },
        result:function (){
            var current = examDatagrid.datagrid("getSelected");
            if(current == null){
                 $.messager.alert("温馨提示","请选择要处理的考试成绩","warning");
                 return;
            }
            $.messager.prompt("温馨提示","请输入处理结果",function (data){
                $.post("/exam/result",{result:data,id:current.id},function (data){
                    if(data.success){
                         $.messager.alert("温馨提示",data.msg,"info",function (){
                             examDatagrid.datagrid("reload")
                         });
                    }else{
                         $.messager.alert("温馨提示",data.msg,"error");
                    }
                },"json")
            })
        }
    }

    $("a[data-cmd != null]"
    ).on("click", function () {
        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    })
});

function searcher(value) {
    $("#examDatagrid").datagrid("reload",{
        keyword:value
    });
}

function employeeFormatter(value) {
    if (value) {
        return value.username
    }
    return "";
}


function wantGradeFormatter(value) {
    if (value) {
        return value.name
    }
    return "";
}

function salesmanFormatter(value) {
    if (value) {
        return value.username;
    }
    return "";
}
