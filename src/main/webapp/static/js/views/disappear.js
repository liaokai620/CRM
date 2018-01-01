$(function () {
    $("#disappear_datagrid_tooggle_tb").toggle('slow');
    var disappearDatagrid, disappearEditBtnAndQuitBtn, disappearDialog, disappearForm, disappearSearchBtn, disappearSearchDialog, disappearSearchForm;
    disappearDatagrid = $("#disappear_datagrid");
    disappearEditBtnAndQuitBtn = $("#disappear_editBtn,#disappear_quitBtn");
    disappearDialog = $("#disappear_dialog");
    disappearSearchDialog = $("#disappearSearch_dialog");
    disappearForm = $("#disappear_form");
    disappearSearchForm = $("#disappearSearch_form");
    disappearSearchBtn = $("#searchBtn");
    //数据表格
    disappearDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/disappear/list',
        fitColumns: true,
        toolbar: '#disappear_datagrid_tb',
        columns: [
            [
                {field: 'name', align: 'center', width: 10, title: '学生姓名', formatter: studentnameFormatter},
                {field: 'qq', align: 'center', width: 10, title: 'QQ', formatter: studentqqFormatter},
                {field: 'tel', align: 'center', width: 10, title: '联系电话', formatter: studenttelFormatter},
                {field: 'toclassdate', align: 'center', width: 10, title: '上课天数'},
                {field: 'grade', align: 'center', width: 10, title: '流失班级', formatter: gradeFormatter},
                {field: 'disappearcause', align: 'center', width: 10, title: '流失原因'},
                {field: 'disappeartime', align: 'center', width: 10, title: '流失时间'},
                {field: 'salesman', align: 'center', width: 10, title: '营销人员', formatter: salesmanFormatter},
                {field: 'inputuser', align: 'center', width: 10, title: '录入人', formatter: inputuserFormatter},
                {field: 'refund', align: 'center', width: 10, title: '是否退款', formatter: tellerFormatter}
            ]
        ]
    });
    //对话框
    disappearDialog.dialog({
        width: 280,
        height: 330,
        buttons: '#disappear_dialog_bt',
        closed: true
    });
    disappearSearchDialog.dialog({
        width: 320,
        height: 280,
        buttons: '#disappearSearch_dialog_buttons',
        closed: true
    });
    disappearSearchBtn.textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 50,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: function () {
            var keyword = $(this).val();
            disappearDatagrid.datagrid("load", {
                keyword: keyword
            });
        }
    });

    //对按钮进行统一事件监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    //方法统一管理起来]
    var cmdObj = {
        add: function () {
            //1.清空表单数据
            disappearForm.form("clear");
            //2.设置对话框的标题
            disappearDialog.dialog("setTitle", "新增");
            //3.打开对话框
            disappearDialog.dialog("open");
        },
        edit: function () {
            var rowData = disappearDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                disappearForm.form("clear");
                //2.设置对话框的标题
                disappearDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                disappearDialog.dialog("open");
                //特殊数据的处理
                if (rowData.student) {
                    rowData["student.id"] = rowData.student.id;
                }
                if (rowData.grade) {
                    rowData["grade.id"] = rowData.grade.id;
                }
                if (rowData.salesman) {
                    rowData["salesman.id"] = rowData.salesman.id;
                }
                if (rowData.refund) {
                    rowData.refund = 'on';
                }
                //4.回显数据
                disappearForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?disappearId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }, "json");

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        audit: function () {
            var rowData = disappearDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定需要审核该支出吗?", function (yes) {
                    if (yes) {
                        $.get("/disappear/audit?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info");
                                disappearDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要离职的员工记录.", "warning");
            }
        },
        remove: function () {
            var rowData = disappearDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定需要删除该支出吗?", function (yes) {
                    if (yes) {
                        $.get("/disappear/remove?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info");
                                disappearDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要离职的员工记录.", "warning");
            }
        },
        view: function () {
            var rowData = disappearDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                disappearForm.form("clear");
                //2.设置对话框的标题
                disappearDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                disappearDialog.dialog("open");
                //特殊数据的处理
                if (rowData.student) {
                    rowData["student.id"] = rowData.student.id;
                }
                if (rowData.payee) {
                    rowData["payee.id"] = rowData.payee.id;
                }
                if (rowData.grade) {
                    rowData["grade.id"] = rowData.grade.id;
                }
                if (rowData.salesman) {
                    rowData["salesman.id"] = rowData.salesman.id;
                }
                //4.回显数据
                disappearForm.form("load", rowData);//基于同名匹配规则
                $.post("/role/queryRoleIdListForEmployeeForm?disappearId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }, "json");

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        reload: function () {
            //刷新数据表格
            disappearDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/disappear/update";
            } else {
                url = "/disappear/save";
            }
            disappearForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            disappearDialog.dialog("close");
                            disappearDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        highsearch: function () {
            disappearSearchForm.form('submit', {
                url: '/disappear/list',
                success: function (data) {
                    data = $.parseJSON(data);
                    disappearDatagrid.datagrid("loadData", data);
                    disappearSearchDialog.dialog("close");
                }
            });
        },
        disappear_datagrid_search: function () {
            var studentId = $("[name='studentId']").val();
            var toclassdate = $("[name='toclassdate']").val();
            var gradeId = $("[name='gradeId']").val();
            var salesmanId = $("[name='salesmanId']").val();
            disappearDatagrid.datagrid("load", {
                studentId: studentId,
                toclassdate: toclassdate,
                gradeId: gradeId,
                salesmanId: salesmanId
            })
        },
        cancel: function () {
            disappearDialog.dialog("close");
            disappearSearchDialog.dialog("close");
        },
        search: function () {
            //1.清空表单数据
            //disappearSearchForm.form("clear");
            //2.设置对话框的标题
            disappearSearchDialog.dialog("setTitle", "高级查询");
            //3.打开对话框
            disappearSearchDialog.dialog("open");
        },
        showAll: function () {
            disappearDatagrid.datagrid("load", {rows: '[]'});
        },
        toogle:function(){
            $("#disappear_datagrid_tooggle_tb").toggle('slow');
        }
    }
});

function studentnameFormatter(value, record, index) {
    if (record) {
        return record.student.name;
    }
    return record;
}

function studentqqFormatter(value, record, index) {
    if (record) {
        return record.student.qq;
    }
    return record;
}

function studenttelFormatter(value, record, index) {
    if (record) {
        return record.student.tel;
    }
    return record;
}

function gradeFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}

function salesmanFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
}

function inputuserFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
}

function tellerFormatter(value, record, index) {
    if (value) {
        return "<font color='green'>是</font>";
    } else {
        return "<font color='red'>否</font>";
    }
}



