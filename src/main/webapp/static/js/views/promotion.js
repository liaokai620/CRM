$(function () {
    var promotionDatagrid, promotionEditBtnAndQuitBtn, promotionDialog, promotionForm,
        promotionSearchDialog, promotionSearchForm,promotionViewForm,promotionViewDialog;
    promotionDatagrid = $("#promotion_datagrid");
    promotionDialog = $("#promotion_dialog");
    promotionSearchDialog = $("#promotionSearch_dialog");
    promotionForm = $("#promotion_form");
    promotionSearchForm = $("#promotionSearch_form");
    promotionViewDialog = $("#promotion_view_dialog");
    promotionViewForm = $("#promotion_view_form");
    //数据表格
    promotionDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/promotion/list',
        fitColumns: true,
        toolbar: '#promotion_datagrid_tb',
        columns: [
            [
                {field: 'name', align: 'center', width: 10, title: '学生姓名', formatter: studentnameFormatter},
                {field: 'qq', align: 'center', width: 10, title: 'QQ', formatter: studentqqFormatter},
                {field: 'tel', align: 'center', width: 10, title: '联系电话', formatter: studenttelFormatter},
                {field: 'payment', align: 'center', width: 10, title: '总学费', formatter: paymentFormatter},
                {field: 'account', align: 'center', width: 10, title: '销售流水'},
                {field: 'tip', align: 'center', width: 10, title: '其他费用'},
                {field: 'promotiontime', align: 'center', width: 10, title: '升班/留级时间'},
                {field: 'beforegrade', align: 'center', width: 10, title: '以前班级', formatter: beforegradeFormatter},
                {field: 'aftergrade', align: 'center', width: 10, title: '流入班级', formatter: aftergradeFormatter},
                {field: 'salesman', align: 'center', width: 10, title: '营销人员', formatter: salesmanFormatter},
                {field: 'state', align: 'center', width: 10, title: '当前状态',formatter: stateFormatter}
            ]
        ]
    });
    //对话框
    promotionDialog.dialog({
        width: 360,
        height: 300,
        buttons: '#promotion_dialog_bt',
        closed: true
    });

    //对按钮进行统一事件监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    //查看窗口
    promotionViewDialog.dialog({
        width: 700,
        height: 230,
        buttons: '#promotion_view_dialog_btn',
        closed: true
    });

    //方法统一管理起来]
    var cmdObj = {
        add: function () {
            //1.清空表单数据
            promotionForm.form("clear");
            //2.设置对话框的标题
            promotionDialog.dialog("setTitle", "新增");
            //3.打开对话框
            promotionDialog.dialog("open");
        },
        edit: function () {
            var rowData = promotionDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                promotionForm.form("clear");
                //2.设置对话框的标题
                promotionDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                promotionDialog.dialog("open");
                //特殊数据的处理
                if (rowData.student) {
                    rowData["student.id"] = rowData.student.id;
                }
                if (rowData.aftergrade) {
                    rowData["aftergrade.id"] = rowData.aftergrade.id;
                }
                //4.回显数据
                promotionForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?promotionId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }, "json");

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        remove: function () {
            var rowData = promotionDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定要删除该数据吗?", function (yes) {
                    if (yes) {
                        $.get("/promotion/delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info");
                                promotionDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择一条需要删除的数据.", "warning");
            }
        },
        view: function () {
            var rowData = promotionDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                promotionViewForm.form("clear");
                //2.设置对话框的标题
                promotionViewDialog.dialog("setTitle", "查看");
                //特殊数据的处理
                if (rowData.student) {
                    rowData["student.qq"] = rowData.student.qq;
                    rowData["student.tel"] = rowData.student.tel;
                    rowData["student.name"] = rowData.student.name;
                }
                if (rowData.beforegrade) {
                    rowData["beforegrade.name"] = rowData.beforegrade.name;
                }
                if (rowData.aftergrade) {
                    rowData["aftergrade.name"] = rowData.aftergrade.name;
                }
                if (rowData.salesman) {
                    rowData["salesman.realname"] = rowData.salesman.realname;
                }
                console.log(rowData);
                //4.回显数据
                promotionViewForm.form("load", rowData);//基于同名匹配规则
                //3.打开对话框
                promotionViewDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择一条需要查看的数据.", "warning");
            }
        },
        reload: function () {
            //刷新数据表格
            promotionDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/promotion/update";
            } else {
                url = "/promotion/save";
            }
            promotionForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            promotionDialog.dialog("close");
                            promotionDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        highsearch: function () {
            promotionSearchForm.form("submit", {
                url: '/promotion/list',
                success: function (data) {
                    data = $.parseJSON(data);
                    promotionSearchDialog.dialog("close");
                    promotionDatagrid.datagrid("loadData",data);
                }
            });
        },
        cancel: function () {
            promotionViewDialog.dialog("close");
            promotionDialog.dialog("close");
            promotionSearchDialog.dialog("close");
        },
        search: function () {
            //1.清空表单数据
            promotionSearchForm.form("clear");
            //2.设置对话框的标题
            promotionSearchDialog.dialog("setTitle", "高级查询");
            //3.打开对话框
            promotionSearchDialog.dialog("open");
        },
        showAll:function(){
            $("#searchBtn").textbox("clear");
            promotionDatagrid.datagrid("load",{rows:'[]'});
        },
        promotion_datagrid_search:function(){
            var keyword = $("[name='keyword']").val();
            var beforegradeId = $("[name='beforegradeId']").val();
            var salesmanId = $("[name='salesmanId']").val();
            promotionDatagrid.datagrid("load",{
                keyword:keyword,
                beforegradeId:beforegradeId,
                salesmanId:salesmanId
            })
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

function paymentFormatter(value, record, index) {
    if (value) {
        return value.totalAmount;
    }
    return value;
}

function beforegradeFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}

function aftergradeFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}

function salesmanFormatter(value, record, index) {
    console.log(value);
    if (value) {
        return value.realname;
    }
    return "";
}

function stateFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='green'>留级</font>";
    } else if (value == 1) {
        return "<font color='red'>升班</font>";
    }
}



