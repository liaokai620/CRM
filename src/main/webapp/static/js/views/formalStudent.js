$(function () {
    $("#formalStudent_datagrid_tooggle_tb").toggle('slow');
    var formalStudentDatagrid, formalStudentDialog, formalStudentForm, formalStudentViewDialog,
        formalStudentTurnDialog, formalStudentTurnForm, formalStudentCheckoutDialog, formalStudentCheckoutForm,
        formalStudentSearchDialog, formalStudentViewForm;
    formalStudentDatagrid = $("#formalStudent_datagrid");
    formalStudentDialog = $("#formalStudent_dialog");
    formalStudentSearchDialog = $("#formalStudentSearch_dialog");
    formalStudentForm = $("#formalStudent_form");
    formalStudentViewDialog = $("#formalStudent_view_dialog");
    formalStudentViewForm = $("#formalStudent_view_form");
    formalStudentTurnDialog = $("#formalStudent_turn_dialog");
    formalStudentTurnForm = $("#formalStudent_turn_form");
    formalStudentCheckoutDialog = $("#formalStudent_checkout_dialog");
    formalStudentCheckoutForm = $("#formalStudent_checkout_form");
    //数据表格
    formalStudentDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/formalStudent/list',
        fitColumns: true,
        toolbar: '#formalStudent_datagrid_tb',
        columns: [
            [
                {field: 'name', align: 'center', width: 10, title: '学生姓名', formatter: studentnameFormatter},
                {field: 'salesman', align: 'center', width: 10, title: '营销人员', formatter: salesmanFormatter},
                {field: 'totalAmount', align: 'center', width: 10, title: '总学费'},
                {field: 'prepaid', align: 'center', width: 10, title: '已缴...'},
                {field: 'arrearage', align: 'center', width: 10, title: '待缴...'},
                {field: 'state', align: 'center', width: 10, title: '缴费状态', formatter: stateFormatter},
                {field: 'paymentType', align: 'center', width: 10, title: '付款方式'},
                {field: 'enrolTime', align: 'center', width: 10, title: '入学时间'},
                {field: 'qq', align: 'center', width: 10, title: 'QQ', formatter: studentqqFormatter},
                {field: 'tel', align: 'center', width: 10, title: '联系电话', formatter: studenttelFormatter},
                {field: 'grade', align: 'center', width: 10, title: '所在班级', formatter: gradeFormatter}
            ]
        ]
    });
    //对话框
    formalStudentDialog.dialog({
        width: 280,
        height: 330,
        buttons: '#formalStudent_dialog_bt',
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
    formalStudentViewDialog.dialog({
        width: 750,
        height: 320,
        buttons: '#formalStudent_view_dialog_btn',
        closed: true
    });

    //转班窗口
    formalStudentTurnDialog.dialog({
        width: 250,
        height: 220,
        buttons: '#formalStudent_turn_dialog_btn',
        closed: true
    });

    //校验窗口
    formalStudentCheckoutDialog.dialog({
        width: 270,
        height: 180,
        title: '确认',
        buttons: '#formalStudent_checkout_dialog_btn',
        closed: true
    });

    //方法统一管理起来]
    var cmdObj = {
        add: function () {
            //1.清空表单数据
            formalStudentForm.form("clear");
            //2.设置对话框的标题
            formalStudentDialog.dialog("setTitle", "新增");
            //3.打开对话框
            formalStudentDialog.dialog("open");
        },
        edit: function () {
            var rowData = formalStudentDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                formalStudentForm.form("clear");
                //2.设置对话框的标题
                formalStudentDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                formalStudentDialog.dialog("open");
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
                formalStudentForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?formalStudentId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }, "json");

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        view: function () {
            var rowData = formalStudentDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                formalStudentViewForm.form("clear");
                //2.设置对话框的标题
                formalStudentViewDialog.dialog("setTitle", "查看");
                //特殊数据的处理
                if (rowData.student) {
                    rowData["student.id"] = rowData.student.id;
                    rowData["student.qq"] = rowData.student.qq;
                    rowData["student.tel"] = rowData.student.tel;
                    rowData["student.name"] = rowData.student.name;
                }
                if (rowData.grade) {
                    rowData["grade.id"] = rowData.grade.id;
                    rowData["grade.name"] = rowData.grade.name;
                }
                if (rowData.cheapWay) {
                    rowData["cheapWay.id"] = rowData.cheapWay.id;
                    rowData["cheapWay.name"] = rowData.cheapWay.name;
                }
                if (rowData.salesman) {
                    rowData["salesman.id"] = rowData.salesman.id;
                    rowData["salesman.realname"] = rowData.salesman.realname;
                }
                console.log(rowData);
                //4.回显数据
                formalStudentViewForm.form("load", rowData);//基于同名匹配规则
                //3.打开对话框
                formalStudentViewDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择一条需要查看的数据.", "warning");
            }
        },
        reload: function () {
            //刷新数据表格
            formalStudentDatagrid.datagrid("reload");
        },
        formalStudent_datagrid_search: function () {
            var studentId = $("[name='studentId']").val();
            var stateId = $("[name='stateId']").val();
            var paymentType = $("[name='paymentType']").val();
            var salesmanId = $("[name='salesmanId']").val();
            formalStudentDatagrid.datagrid("load", {
                studentId: studentId,
                stateId: stateId,
                paymentType: paymentType,
                salesmanId: salesmanId
            })
        },
        cancel: function () {
            formalStudentTurnDialog.dialog("close");
            formalStudentCheckoutDialog.dialog("close");
            formalStudentDialog.dialog("close");
            formalStudentViewDialog.dialog("close");
        },
        showAll: function () {
            $("#formalStudent_datagrid_tooggle_tb :input").val("");
            formalStudentDatagrid.datagrid("load", {rows: '[]'});
        },
        toogle: function () {
            $("#formalStudent_datagrid_tooggle_tb").toggle('slow');
        },
        remove: function () {
            var row = formalStudentDatagrid.datagrid("getSelected");
            if (row) {
                $.messager.confirm("温馨提示", "确定要删除这条数据吗?", function (yes) {
                    if (yes) {
                        $.post('/formalStudent/delete?id=' + row.id, function (data) {
                            if (data.success) {
                                $.messager.after("温馨提示", data.msg, "info");
                            }
                        }, "json")
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的数据", "info");
            }
        },
        turnDialog: function () {
            var rowData = formalStudentDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                formalStudentTurnForm.form("clear");
                //2.设置对话框的标题
                formalStudentTurnDialog.dialog("setTitle", "转班");
                //特殊数据的处理
                if (rowData.student) {
                    rowData["student.name"] = rowData.student.name;
                }
                if (rowData.grade) {
                    rowData["grade.name"] = rowData.grade.name;
                }
                //4.回显数据
                formalStudentTurnForm.form("load", rowData);
                //3.打开对话框
                formalStudentTurnDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择一条数据.", "warning");
            }
        },
        turn: function () {
            formalStudentTurnForm.form("submit", {
                url: '/formalStudent/turn',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        formalStudentTurnDialog.dialog("close");
                        formalStudentDatagrid.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }
            })
        },
        disappearDialog: function () {
            var row = formalStudentDatagrid.datagrid("getSelected");
            if (row) {
                $.messager.confirm("温馨提示", "确定要把该学生休学吗?", function (yes) {
                    if (yes) {
                        formalStudentCheckoutForm.form("clear");
                        formalStudentCheckoutForm.form("load", row);
                        formalStudentCheckoutDialog.dialog("open");
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
            }
        },
        disappear: function () {
            formalStudentCheckoutForm.form("submit", {
                url: '/formalStudent/disappear',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        formalStudentCheckoutDialog.dialog("close");
                        formalStudentDatagrid.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }
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

function stateFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='red'>未缴清</font>";
    } else if (value == 1) {
        return "<font color='green'>已缴清</font>";
    }
}

//导出excel
function exportExcel() {
    var rows = $("#formalstudent_datagrid").datagrid("getRows");

    for (var i = 0; i < rows.length; i++) {
        if (rows[i].enrolTime != null) {
            var unix = rows[i].RetainageDate.replace("/Date(", "").replace(")/", "");
            var un = unix.substring(0, 10);
            var newDate = new Date();
            newDate.setTime(un * 1000);

            rows[i].RetainageDate = newDate.toLocaleString();
        }

        //移除不要的字段
        delete rows[i].state;

    }
    var bodyData = JSON.stringify(rows);  //转成json字符串

    //替换中文标题
    var a = bodyData.replace(/name/g, "学生姓名").replace(/salesman/g, "营销人员").replace(/totalAmount/g, "总学费")
        .replace(/prepaid/g, "已缴").replace(/arrearage/g, "待缴").replace(/paymentType/g, "付款方式").replace(/enrolTime/g, '入学时间')
        .replace(/ContractNo/g, "合同编号").replace(/tel/g, "联系电话").replace(/grade/g, "所在班级");


    var postData = {
        data: a
    };

    $.ajax({
        type: "POST",
        url: "ExportExcel",
        data: postData,
        success: function (data) {
            if (data == "1") {
                layer.msg("操作成功,文件在桌面！", {
                    icon: 6,
                    time: 2000
                });
            } else if (data == "-1") {
                layer.msg("操作失败！", {icon: 2});
            }
        }
    });
}