$(function () {
    var queryholidayDataGrid, searchNameBtn, searchStartBtn, searchEndBtn, searchStateBtn,
        queryholidayDialog, queryholidayForm;
    queryholidayDataGrid = $("#queryholiday_dataGrid");
    searchNameBtn = $("#searchnameBtn");
    searchStartBtn = $("#searchstartBtn");
    searchEndBtn = $("#searchendBtn");
    searchStateBtn = $("#searchStateBtn");
    queryholidayDialog = $("#queryholiday_dialog");
    queryholidayForm = $("#queryholiday_form");

    queryholidayDataGrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/queryholiday/list',
        fitColumns: true,
        toolbar: '#tb',
        columns: [
            [
                {field: 'employee', align: 'center', width: 10, formatter: nameFormatter, title: '申请人'},
                {field: 'starttime', align: 'center', width: 10, title: '起始时间'},
                {field: 'endtime', align: 'center', width: 10, title: '结束时间'},
                {field: 'queryresult', align: 'center', width: 10, title: '请假原因'},
                {field: 'querytype', align: 'center', width: 10, title: '请假类型', formatter: queryFormatter},
                {field: 'state', align: 'center', width: 10, title: '状态', formatter: stateFormatter},
                {field: 'auditor', align: 'center', width: 10, title: '审核人'}
            ]
        ]
    });
    //对话框
    queryholidayDialog.dialog({
        width: 450,
        height: 480,
        buttons: '#bt',
        closed: true
    });
    searchNameBtn.textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 80,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    searchStartBtn.datetimebox({
        width: 230,
        label: "开始时间:",
        labelWidth: 80,
        prompt: "请输入搜索开始时间",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    searchEndBtn.datetimebox({
        width: 230,
        label: "结束时间:",
        labelWidth: 80,
        prompt: "请输入搜索结束时间",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    searchStateBtn.combobox({
        width: 130,
        labelWidth: 80,
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    function searchComit() {
        var keyword = $(searchNameBtn).val();
        var startTime = $(searchStartBtn).val();
        var endTime = $(searchEndBtn).val();
        var state = $(searchStateBtn).val();
        queryholidayDataGrid.datagrid("load", {
            name: keyword,
            beginDate: startTime,
            endDate: endTime,
            state: state
        });
    }

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
            queryholidayForm.form("clear");
            //2.设置对话框的标题
            queryholidayDialog.dialog("setTitle", "新增");
            //3.打开对话框
            queryholidayDialog.dialog("open");
        },
        edit: function () {
            queryholidayDataGrid
            var rowData = queryholidayDataGrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                queryholidayForm.form("clear");
                //2.设置对话框的标题
                queryholidayDialog.dialog("setTitle", "新增");
                //3.打开对话框
                queryholidayDialog.dialog("open");
                //特殊数据的处理
                if (rowData.employee) {
                    rowData["employee.id"] = rowData.employee.id;
                }
                //4.回显数据
                queryholidayForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                /* $.post("/role/queryRoleIdListForEmployeeForm?queryholidayId=" + rowData.id, function (data) {
                 $("#roleId").combobox("setValues", data);
                 }), "json";*/

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }

        },
        quit: function () {
            var rowData = queryholidayDataGrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您已经检查无误了么", function (yes) {
                    if (yes) {
                        $.get("/queryholiday/quit?id=" + rowData.id, function (data) {
                            if (data.success) {
                                queryholidayDataGrid.datagrid("reload");
                                $.messager.alert("温馨提示", data.msg, "info");
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
        print: function () {
            CreateFormPage("请假条打印", $("#queryholiday_dataGrid"));
        },
        reload: function () {
            //刷新数据表格
            queryholidayDataGrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/queryholiday/update";
            } else {
                url = "/queryholiday/save";
            }
            queryholidayForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            queryholidayDialog.dialog("close");
                            queryholidayDataGrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            queryholidayDialog.dialog("close");
        },
        out: function () {
            location.href = "excel/outQueryHoliday";
        },
        download: function () {
            location.href = "excel/outQueryHoliday?download=1";
        },
        upload: function () {
            $("#upload").form("submit",{
                url: "/queryholiday/inQueryHoliday",
                onSubmit:function () {
                },
                success: function (data) {
                    data = $.parseJSON(data)
                    $.messager.alert("温馨提示",data.msg,"info",function () {
                        queryholidayDataGrid.datagrid("reload")
                    })
                }
            })
        }
    };

});


function nameFormatter(value, record, index) {
    if (value.realname) {
        return value.realname;
    }
    return value;
}

function queryFormatter(value, record, index) {
    if (value == "病假") {
        return "<font color='#a9a9a9'>病假</font>";
    } else if (value == "事假") {
        return "<font color='red'>事假</font>";
    }
}

function stateFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='green'>已确认</font>";
    } else if (value == 1) {
        return "<font color='red'>未确认</font>";
    }
}
