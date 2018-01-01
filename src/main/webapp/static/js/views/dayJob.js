$(function () {

    var dayJob_datagrid = $("#dayJob_datagrid");
    var dayJob_datagrid_btns = $("#dayJob_datagrid_btns");
    var dayJob_dialog = $("#dayJob_dialog");
    var dayJobForm = $("#dayJobForm");
    var dayJob_dialogDeal = $("#dayJob_dialogDeal");
    var dealDescriptionForm = $("#dealDescriptionForm");
    var tiemSearchBtn = $("#searchBtn")
    var queryForm = $("#queryForm")
    var editDescriptionBtn = $("#editDescriptionBtn")
    dayJob_datagrid.datagrid({
        title: '当日任务',
        fit: true,
        fitColumns: true,
        url: '/dayJob/list',
        pagination: true,
        toolbar: '#dayJob_datagrid_btns',
        singleSelect: true,
        columns: [
            [
                {field:"ck", checkbox:"true"},
                {field: 'inputTime', title: '日期', width: 10},
                {field: 'handler', title: '负责人', width: 10, formatter: handlerFormatter},
                {field: 'jobDescription', title: '任务描述', width: 10},
                {field: 'dealDescription', title: '处理描述', width: 10},
                {field: 'state', title: '状态', width: 10, formatter: stateFormatter},
            ]
        ],
      onClickRow: function (rowIndex, rowData) {
            //判断当前记录中的状态的值.
            if (rowData.state != 0) {


                editDescriptionBtn.linkbutton("disable");
            } else {
                //启用按钮
                editDescriptionBtn.linkbutton("enable");
            }
        },
        onDblClickCell:function(index,field,value){
            if('jobDescription'==field){
                $.messager.alert("任务描述",value,"info")

            }else if('dealDescription'==field){
                $.messager.alert("处理描述",value,"info")
            }
        },
        onLoadSuccessP:function(){
            dayJob_datagrid.datagrid("selectRow[0]").attr("style", "display:none;");
        }

    })


    dayJob_dialog.dialog({
        title: '新增',
        width: 300,
        height: 300,
        closed: true,
        buttons: '#dayJob_dialog_btns',

    })
    dayJob_dialogDeal.dialog({
        title: '描述修改',
        width: 300,
        height: 300,
        closed: true,
        buttons: '#dayJob_dialogDeal_btns'
    })

    /*统一管理*/
    var comObj = {
        /*添加任务*/
        add: function () {
            dayJobForm.form("clear")
            dayJob_dialog.dialog("open")
        },
        /*保存任务*/
        save: function () {
            var url;
            var dayJobId = $("[name='id']").val();
            if (dayJobId) {
                url = "/dayJob/update";
            } else {
                url = "/dayJob/save";
            }
            dayJobForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data)
                    console.log(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            dayJob_dialog.dialog("close");
                            dayJob_datagrid.datagrid("reload")
                        });
                    } else {
                        $.messager.alert("温馨提示", "保存失败!", function () {
                            dayJob_dialog.dialog("close");
                        })
                    }
                }
            })
        },
        /*编辑任务*/
        edit: function () {

            var row = dayJob_datagrid.datagrid("getSelected")
            if (row == null) {
                $.messager.alert("温馨提示", "请选择条任务", "info")
                return;
            }
            dayJobForm.form("clear")
            dayJob_dialog.dialog("open")
            if (row.handler) {
                row["handler.id"] = row.handler.id
            }
            dayJobForm.form('load', row
            );
        },
        /*删除任务*/
        delete: function () {
            dayJob_datagrid,dayJob_datagrid("singleSelect","true")
            var row = dayJob_datagrid.datagrid("getSelected")
            if (row == null) {
                $.messager.alert("温馨提示", "请选择条任务", "info")
                return;
            }
            $.messager.confirm("温馨提示", "确认要删除吗?", function (yes) {
                if (yes) {
                    $.post("dayJob/delete", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "删除成功", "info", function () {
                                dayJob_dialog.dialog("close");
                                dayJob_datagrid.datagrid("reload");
                            })
                        } else {
                            $.messager.alert("温馨提示", "删除失败", "info")
                        }

                    })

                }
            })

        },
        /*设置完成状态部门领导权限*/
        markSuccess: function () {

            var rows = dayJob_datagrid.datagrid("getSelections")
            if (rows == null) {
                $.messager.alert("温馨提示", "请选至少择条一条任务", "info")
                return;
            }
            var ids= [];
            for (var i = 0; i < rows.length; i++) {
                var id=rows[i].id;
                ids.push(id);
            }
            console.log("我的ids"+ids)

            $.messager.confirm("温馨提示", "确认要标记完成吗?", function (yes) {
                if (yes) {
                    $.post("/dayJob/markSuccess", {"ids": ids}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "标记成功", "info", function () {
                                dayJob_dialog.dialog("close");
                                dayJob_datagrid.datagrid("reload");
                            })
                        } else {
                            $.messager.alert("温馨提示", "标记失败", "info")
                        }

                    }, "json")

                }
            })

        },
        /*标记失败状态部门领导权限*/
        markFailed: function () {

            var rows= dayJob_datagrid.datagrid("getSelections")
            if (rows == null) {
                $.messager.alert("温馨提示", "请选择至少一条任务", "info")
                return;
            }
            var ids=[];
            for(i=0 ;i<rows.length;i++){
                ids.push(rows[i].id);
            }
            $.messager.confirm("温馨提示", "确认要标记完成吗?", function (yes) {
                if (yes) {
                    $.post("/dayJob/markFailed", {"ids": ids}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "标记成功", "info", function () {
                                dayJob_dialog.dialog("close");
                                dayJob_datagrid.datagrid("reload");
                            })
                        } else {
                            $.messager.alert("温馨提示", "标记失败", "info")
                        }
                    }, "json")
                }
            })
        },
        /*修改任务描述,员工权限*/
        editDescription: function () {
            var row = dayJob_datagrid.datagrid("getSelected")
            if (row == null) {
                $.messager.alert("温馨提示", "请选择条任务", "info")
                return;
            }
            dealDescriptionForm.form("clear");
            dayJob_dialogDeal.dialog("open");
            console.log(row);
            dealDescriptionForm.form('load', row
            );


        },
        saveDeal: function () {
            dealDescriptionForm.form("submit", {
                url: '/dayJob/editDescription',
                success: function (data) {
                    data = $.parseJSON(data)
                    console.log(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            dayJob_dialogDeal.dialog("close");
                            dayJob_datagrid.datagrid("reload")
                        });
                    } else {
                        $.messager.alert("温馨提示", "保存....失败!", function () {
                            dayJob_dialogDeal.dialog("close");
                        })
                    }
                }
            })
        },
        /*取消方法*/
        cancelDeal: function () {
            dayJob_dialogDeal.dialog("close");
        },
        /*查询*/
        search: function () {
            dayJob_datagrid.datagrid('load', {
                queryTime: $("[name='queryTime']").val(),
                querHandlerId: $("[name='querHandlerId']").val()

            });


        }


    }
    /*统一监听*/
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        comObj[cmd]();
    })


})
/*负责人显示格式*/
function handlerFormatter(value) {
    if (value) {
        return value.username;
    } else {
        return "未分配";
    }
}
/*状态显示格式*/
function stateFormatter(value) {
    if (value == -1) {
        return "<font color='red'>失败</font>"
    } else if (value == 0) {
        return "<font color='green'>初始</font>"
    } else if (value == 1) {
        return "<font color='green'>成功</font>"
    }

}