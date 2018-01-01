$(function () {
    //对页面中的元素进行抽取.
    //方法太凌乱,希望统一管理
    //按钮在JS统一进行监听
    var followDatagrid, followDialog, followForm,auditDialog,auditForm;
    followDatagrid = $("#followDatagrid");
    followDialog = $("#followDialog");
    followForm = $("#followForm");
    auditDialog = $("#auditDialog")
    auditForm = $("#auditForm")
    //数据表格
    followDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/studentFollow/list',
        fitColumns: true,
        toolbar: '#dataGridToolbar',
        toolbar: "#followToolbar",
        columns: [[
            {field: 'time', align: 'center',width: 10, title: '时间', },
            {field: 'student', align: 'center', width: 10, title: '客户', formatter: studentFormatter},
            {field: 'consultant', align: 'center', width: 10, title: '咨询人员', formatter: employeeFormatter},
            {field: 'nextMeetingTime', align: 'center', width: 10, title: '下次访问时间', },
            {field: 'order', align: 'center', width: 10, title: '跟进目的',},
            {field: 'wantLevel', align: 'center', width: 10, title: '意向程度', formatter: wantLevelFormatter},
            {field: 'qq', align: 'center', width: 10, title: 'QQ',},
            {field: 'tel', align: 'center', width: 10, title: '联系电话'},
            {field: 'consultantWay', align: 'center', width: 10, title: '咨询方式',},
            {field: 'digest', align: 'center', width: 10, title: '摘要',},
        ]],
    });



    //对话框
    followDialog.dialog({
        title: "学员跟踪",
        width: 1000,
        height: 500,
        buttons: '#dialogbuttons',
        closable: true,
        closed: true
    });

    auditDialog.dialog({
        title:"客户跟踪审核",
        width:1000,
        height: 450,
        closable: true,
        closed: true,
        buttons: '#auditButttons',
    })
    //方法统一管理起来]
    var cmdObj = {
        add: function () {

            //1.清空表单数据
            followForm.form("clear");
            //2.设置对话框的标题
            followDialog.dialog("setTitle", "新增学员跟踪信息");

            $("#latenStudent").combobox({
                onChange: function (){
                    var id = $("#latenStudent").combobox("getValue");
                    $.post("/latenStudent/selectByStudentId",{studentId:id},function (data){
                        data.id = null;
                        data["id"] = null;
                        data["salesman.id"] =data.salesman.id
                        if(data.school){
                            data["school.id"] =data.school.id
                        }
                        if(data.wantLevel){
                            data["wantLevel.id"] =data.wantLevel.id
                        }
                        if(data.wantClass){
                            data["wantClass.id"] =data.wantClass.id
                        }

                        followForm.form("load",data);
                    },"json")
                }
            })

            //3.打开对话框
            followDialog.dialog("open");
        },
        edit: function () {
            var rowData = followDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                followForm.form("clear");
                //2.设置对话框的标题
                followDialog.dialog("setTitle", "编辑学员跟踪信息");
                //3.打开对话框
                followDialog.dialog("open");
                //特殊数据的处理
                if (rowData.consultant) {
                    rowData["consultant.id"] = rowData.consultant.id;
                }
                if (rowData.student) {
                    rowData["student.id"] = rowData.student.id;
                }
                if (rowData.wantLevel) {
                    rowData["wantLevel.id"] = rowData.wantLevel.id;
                }
                rowData['id'] = rowData.id;
                console.log(rowData);
                //4.回显数据
                followForm.form("load", rowData);//基于同名匹配规则
                followDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择一条需要编辑的数据.", "warning");
            }

        },
        reload: function () {
            //刷新数据表格
            followDatagrid.datagrid("reload");
        },
        save : function () {
            var url = "/studentFollow/save";
            if ($("[name='id']").val()) {
                url = "/studentFollow/update";
            }
            followForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            followDialog.dialog("close");
                            followDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        remove: function () {
            var rowData = followDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "确定删除该数据吗?", function (yes) {
                    if (yes) {
                        $.post("/studentFollow/delete", {followId: rowData.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    followDatagrid.datagrid("reload")
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
            followDialog.dialog("close");
        },
        audit: function (){
            var current = followDatagrid.datagrid("getSelected");
            if(!current){
                 $.messager.alert("温馨提示","请选择要审核的跟踪记录","warninig");
                return;
            }
            auditForm.form("clear");
            if(current.level){
                current["level.id"] = current.level.id;
            }
            if(current.consultant){
                current["consultant.username"] = current.consultant.username;
            }
            if(current.student){
                current["student.name"] = current.student.name;
            }
            auditForm.form("load",current);

            auditDialog.dialog("open");
        },
        doAudit: function (){
            auditForm.form("submit",{
                url:"/studentFollow/audit",
                success: function(data){
                    data = $.parseJSON(data)
                    if(data.success){
                         $.messager.alert("温馨提示",data.msg,"info",function (){
                             auditDialog.dialog("close");
                             followDatagrid.datagrid("reload");
                         });
                    }else{
                         $.messager.alert("温馨提示",data.msg,"error")
                    }
                }
            })
        },
    }

    $("a[data-cmd != null]"
    ).on("click", function () {
        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    })

    $("[name='student.id']").combobox({
        onChange: function (newValue){
            $.post("/latenStudent/selectByStudentId",{studentId:newValue},function (data){
                if(data.wantLevel){
                    data["wantLevel.id"] = data.wantLevel.id
                }
                if(data.wantClass){
                    data["wantClass.id"] = data.wantClass.id
                }
                followForm.form("load",data);
            },"json")
        }
    })
});

function searcher(value) {
    $("#followDatagrid").datagrid("reload",{
        keyword:value
    });
}

function studentFormatter(value) {
    if (value) {
        return value.name
    }
    return "";
}

function wantLevelFormatter(value) {
    if (value) {
        return value.name
    }
    return "";
}

function employeeFormatter(value) {
    if (value) {
        return value.username;
    }
    return "";
}
