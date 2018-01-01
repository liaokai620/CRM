$(function () {
    var incomeDatagrid, incomeDialog, incomeForm, incomeSearchBtn, incomeTotalDialog, incomeTotalForm,
        incomeSearchDialog, incomeSearchForm;
    incomeDatagrid = $("#income_datagrid");
    incomeDialog = $("#income_dialog");
    incomeSearchDialog = $("#incomeSearch_dialog");
    incomeForm = $("#income_form");
    incomeSearchForm = $("#incomeSearch_form");
    incomeSearchBtn = $("#searchBtn");
    incomeTotalDialog = $("#income_total_dialog");
    incomeTotalForm = $("#income_total_form");
    //数据表格
    incomeDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/income/list',
        fitColumns: true,
        toolbar: '#income_datagrid_tb',
        columns: [
            [
                {field: 'student', align: 'center', width: 10, title: '学员', formatter: studentFormatter},
                {field: 'incometime', align: 'center', width: 10, title: '收款时间'},
                {field: 'incomeamount', align: 'center', width: 10, title: '收款金额'},
                {field: 'grade', align: 'center', width: 10, title: '班级', formatter: gradeFormatter},
                {field: 'subject', align: 'center', width: 10, title: '学科'},
                {field: 'payee', align: 'center', width: 10, title: '收款人', formatter: payeeFormatter},
                {field: 'incometype', align: 'center', width: 10, title: '收款方式'},
                {field: 'billnumber', align: 'center', width: 10, title: '单据号码'},
                {field: 'salesman', align: 'center', width: 10, title: '营销人员', formatter: salesmanFormatter},
                {field: 'remark', align: 'center', width: 10, title: '备注'},
                {field: 'state', align: 'center', width: 10, title: '状态',formatter:stateFormatter}
            ]
        ],
        onClickRow:function(){
            var row = incomeDatagrid.datagrid("getSelected");
            if(row){
                $("#income_recheckBtn,#income_backBtn,#income_auditBtn,#income_editBtn").linkbutton("enable");
                if(row.state == 0){
                    $("#income_backBtn,#income_auditBtn").linkbutton("disable");
                }else if(row.state == 1){
                    $("#income_recheckBtn").linkbutton("disable");
                }else if(row.state == 2){
                    $("#income_recheckBtn,#income_backBtn,#income_editBtn").linkbutton("disable");
                }
            }
        }
    });

    //新增编辑弹框
    incomeDialog.dialog({
        width: 480,
        height: 240,
        buttons: '#income_dialog_bt',
        closed: true
    });

    //统计弹框
    incomeTotalDialog.dialog({
        width: 290,
        height: 190,
        buttons: '#income_total_dialog_btn',
        closed: true
    });

    //高级查询弹窗
    incomeSearchDialog.dialog({
        width: 480,
        height: 280,
        buttons: '#incomeSearch_dialog_buttons',
        closed: true
    });

    //按钮过滤


    incomeSearchBtn.textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 50,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: function () {
            var keyword = $(this).val();
            incomeDatagrid.datagrid("load", {
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
            incomeForm.form("clear");
            //2.设置对话框的标题
            incomeDialog.dialog("setTitle", "新增");
            //3.打开对话框
            incomeDialog.dialog("open");
        },
        edit: function () {
            var rowData = incomeDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                incomeForm.form("clear");
                //2.设置对话框的标题
                incomeDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                incomeDialog.dialog("open");
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
                incomeForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?incomeId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }, "json");

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }

        },
        view: function () {
            var rowData = incomeDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                incomeForm.form("clear");
                //2.设置对话框的标题
                incomeDialog.dialog("setTitle", "编辑");
                //3.打开对话框
                incomeDialog.dialog("open");
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
                incomeForm.form("load", rowData);//基于同名匹配规则
            } else {
                $.messager.alert("温馨提示", "请选择一条需要查看的数据.", "warning");
            }
        },
        reload: function () {
            //刷新数据表格
            incomeDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/income/update";
            } else {
                url = "/income/save";
            }
            incomeForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            incomeDialog.dialog("close");
                            incomeDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            incomeTotalDialog.dialog("close");
            incomeDialog.dialog("close");
            incomeSearchDialog.dialog("close");
        },
        search: function () {
            //1.清空表单数据
            incomeSearchForm.form("clear");
            //2.设置对话框的标题
            incomeSearchDialog.dialog("setTitle", "高级查询");
            //3.打开对话框
            incomeSearchDialog.dialog("open");
        },
        total: function () {
            $.post('/income/total',function(data){
                if(data){
                    incomeTotalForm.form("clear");
                    incomeTotalDialog.dialog("setTitle", "统计");
                    incomeTotalForm.form("load", data);
                    incomeTotalDialog.dialog("open");
                }
            },"json");
        },
        recheck:function(){
            var row = incomeDatagrid.datagrid("getSelected");
            if(row){
                $.messager.confirm("温馨提示","确定要复核该笔单据吗?",function(yes){
                    if(yes){
                        $.post('/income/recheck?id='+row.id, function(data){
                            if(data.success){
                                $.messager.alert("温馨提示",data.msg,"info");
                                incomeDatagrid.datagrid("reload");
                            }
                        })
                    }
                })
            }else{
                $.messager.alert("温馨提示","请选择一条数据","warning");
            }
        },
        backrecheck:function(){
            var row = incomeDatagrid.datagrid("getSelected");
            if(row){
                $.messager.confirm("温馨提示","确定要取消复核该笔单据吗?",function(yes){
                    if(yes){
                        $.post('/income/backrecheck?id='+row.id, function(data){
                            if(data.success){
                                $.messager.alert("温馨提示",data.msg,"info");
                                incomeDatagrid.datagrid("reload");
                            }
                        })
                    }
                })
            }else{
                $.messager.alert("温馨提示","请选择一条数据","warning");
            }
        },
        audit:function(){
            var row = incomeDatagrid.datagrid("getSelected");
            if(row){
                $.messager.confirm("温馨提示","确定要审核该笔单据吗?",function(yes){
                    if(yes){
                        $.post('/income/audit?id='+row.id, function(data){
                            if(data.success){
                                $.messager.alert("温馨提示",data.msg,"info");
                                incomeDatagrid.datagrid("reload");
                            }
                        })
                    }
                })
            }else{
                $.messager.alert("温馨提示","请选择一条数据","warning");
            }
        }
    }
});

function gradeFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}

function payeeFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
}

function salesmanFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
}

function studentFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}

function stateFormatter(value,record,index){
    if(value==0){
        return "<font color='blue'>待复核</font>";
    }else if(value==1){
        return "<font color='#ff1493'>已复核</font>";
    }else if(value==2){
        return "<font color='green'>已审核</font>";
    }
}