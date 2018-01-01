$(function () {
    var expenseDatagrid, expenseEditBtnAndQuitBtn, expenseDialog, expenseForm,
        expenseSearchBtn, expenseSearchDialog, expenseSearchForm, expenseViewDialog, expenseViewForm;
    expenseDatagrid = $("#expense_datagrid");
    expenseEditBtnAndQuitBtn = $("#expense_editBtn,#expense_quitBtn");
    expenseDialog = $("#expense_dialog");
    expenseSearchDialog = $("#expenseSearch_dialog");
    expenseForm = $("#expense_form");
    expenseSearchForm = $("#expenseSearch_form");
    expenseSearchBtn = $("#searchBtn");
    expenseViewDialog = $("#expense__view_dialog");
    expenseViewForm = $("#expense_view_form");
    //数据表格
    expenseDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/expense/list',
        fitColumns: true,
        toolbar: '#expense_datagrid_tb',
        columns: [
            [
                {field: 'expendtime', align: 'center', width: 10, title: '支出时间'},
                {field: 'expendamount', align: 'center', width: 10, title: '支出金额'},
                {field: 'teller', align: 'center', width: 10, title: '出纳人', formatter: tellerFormatter},
                {field: 'person', align: 'center', width: 10, title: '经手人', formatter: personFormatter},
                {field: 'expendtype', align: 'center', width: 10, title: '支出方式'},
                {field: 'expendmode', align: 'center', width: 10, title: '支出类型'},
                {field: 'classify', align: 'center', width: 10, title: '详细分类'},
                {field: 'subject', align: 'center', width: 10, title: '所属学科'},
                {field: 'billnumber', align: 'center', width: 10, title: '单据号码'},
                {field: 'audit', align: 'center', width: 10, title: '状态', formatter: auditFormatter},
                {field: 'note', align: 'center', width: 10, title: '支出备注'}
            ]
        ],
        onClickRow: function (index, row) {
            if (row.audit == 1) {
                $("#expense_deteleBtn,#expense_auditBtn,#expense_editBtn").linkbutton("disable");
            } else {
                $("#expense_deteleBtn,#expense_auditBtn,#expense_editBtn").linkbutton("enable");
            }
        }
    });

    //新增编辑弹窗
    expenseDialog.dialog({
        width: 480,
        height: 280,
        buttons: '#expense_dialog_bt',
        closed: true
    });

    //高级查询弹窗
    expenseSearchDialog.dialog({
        width: 480,
        height: 280,
        buttons: '#expenseSearch_dialog_buttons',
        closed: true
    });

    //查看弹窗
    expenseViewDialog.dialog({
        width: 480,
        height: 280,
        buttons: '#expense_view_dialog_buttons',
        closed: true
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
            expenseForm.form("clear");
            //2.设置对话框的标题
            expenseDialog.dialog("setTitle", "新增");
            //3.打开对话框
            expenseDialog.dialog("open");
        },
        edit: function () {
            var rowData = expenseDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                expenseForm.form("clear");
                //2.设置对话框的标题
                expenseDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                expenseDialog.dialog("open");
                //特殊数据的处理
                if (rowData.teller) {
                    rowData["teller.id"] = rowData.teller.id;
                }
                if (rowData.person) {
                    rowData["person.id"] = rowData.person.id;
                }
                //4.回显数据
                expenseForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?expenseId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }, "json");

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        audit: function () {
            var rowData = expenseDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定需要审核该支出吗?", function (yes) {
                    if (yes) {
                        $.get("/expense/audit?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info");
                                expenseDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要审核的数据.", "warning");
            }
        },
        remove: function () {
            var rowData = expenseDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定需要删除该支出吗?", function (yes) {
                    if (yes) {
                        $.get("/expense/remove?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info");
                                expenseDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的数据.", "warning");
            }
        },
        view: function () {
            var rowData = expenseDatagrid.datagrid("getSelected");
            console.log(rowData);
            if (rowData) {
                expenseViewForm.form("clear");
                expenseViewDialog.dialog("setTitle", "查看");
                if (rowData.student) {
                    rowData["student.name"] = rowData.student.name;
                }
                if (rowData.teller) {
                    rowData["teller.realname"] = rowData.teller.realname;
                }
                if (rowData.grade) {
                    rowData["grade.name"] = rowData.grade.name;
                }
                if (rowData.person) {
                    rowData["person.name"] = rowData.person.name;
                }
                expenseViewForm.form("load", rowData);
                expenseViewDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择一条需要查看的数据.", "warning");
            }
        },
        reload: function () {
            expenseDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/expense/update";
            } else {
                url = "/expense/save";
            }
            expenseForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            expenseDialog.dialog("close");
                            expenseDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        highsearch: function () {
            expenseSearchForm.form("submit", {
                url: '/expense/list',
                success: function (data) {
                    data = $.parseJSON(data);
                    expenseSearchDialog.dialog("close");
                    expenseDatagrid.datagrid("loadData", data);
                }
            });
        },
        cancel: function () {
            expenseDialog.dialog("close");
            expenseViewDialog.dialog("close");
            expenseSearchDialog.dialog("close");
        },
        search: function () {
            //1.清空表单数据
            expenseSearchForm.form("clear");
            //2.设置对话框的标题
            expenseSearchDialog.dialog("setTitle", "高级查询");
            //3.打开对话框
            expenseSearchDialog.dialog("open");
        },
        showAll: function () {
            $("#searchBtn").textbox("clear");
            expenseDatagrid.datagrid("load", {rows: '[]'});
        },
        expense_datagrid_search: function () {
            var keyword = $("[name='keyword']").val();
            expenseDatagrid.datagrid("load", {
                keyword: keyword
            })
        }
    }
});

function tellerFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
}

function personFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}

function auditFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='green'>待审核</font>";
    } else if (value == 1) {
        return "<font color='red'>已审核</font>";
    }
}



