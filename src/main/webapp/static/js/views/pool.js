$(function () {
    var poolDatagrid, poolDialog, poolForm, keyword, showDialog, showForm,
        searchForm, searchDialog, moveDialog,moveForm;
    poolForm = $("#poolDorm");
    poolDialog = $("#poolDialog");
    poolDatagrid = $("#poolDatagrid");
    keyword = $("#keyword");
    showDialog = $("#showDialog");
    showForm = $("#showForm");
    searchForm = $("#searchForm");
    searchDialog = $("#searchDialog");
    moveDialog = $("#moveDialog");
    moveForm = $("#moveForm");
    poolDatagrid.datagrid({
        url: "/customerPool/list",
        fit: true,
        toolbar: "#datagrid_toolbar",
        fitColumns: true,
        rownumbers: true,
        pahination: true,
        singleSelect: true,
        columns: [[
            {field: "name", title: "姓名", width: 10, align: "center"},
            {field: "salesman", title: "销售人员", width: 8, align: "center", formatter: employeeFormatter},
            {field: "nextFollowTime", title: "最近跟踪时间", width: 10, align: "center"},
            {field: "qq", title: "QQ", width: 10, align: "center"},
            {field: "tel", title: "电话", width: 10, align: "center"},
            {field: "tel", title: "性别", width: 8, align: "center"},
            {field: "school", title: "学校", width: 10, align: "center", formatter: schoolFormatter},
            {field: "wantClass", title: "意向班级", width: 10, align: "center", formatter: wantClassFormatter},
            {field: "intro", title: "备注", width: 10, align: "center"},
            {field: "clientType", title: "客户类型", width: 8, align: "center", formatter: clientTypeFormatter},
            {field: "status", title: "状态", width: 8, align: "center", formatter: statusFormatter},
            {field: "inputTime", title: "放入时间", width: 8, align: "center"},
        ]]
    })

    var cmdObj = {
        add: function () {
            //刷新表单
            poolForm.form("clear");
            //设置表单标题
            poolDialog.dialog("setTitle", "学员添加");
            $("[data-cmd='sure']").hide();
            $("[data-cmd='no']").show();
            $("[data-cmd='reset']").show();
            $("[data-cmd='save']").show();
            $("[data-cmd='cancel']").hide();
            $("[data-cmd='search']").hide();

            //打开窗口
            poolDialog.dialog("open");
        },
        save: function () {
            var url = "/latenStudent/save";
            //获取id
            console.log($("[name = id]").val());
            if ($("[name=id]").val() > 0) {
                url = "/latenStudent/update";
            }
            poolForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            poolDialog.dialog("close");
                            poolDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            })
        },
        reload: function () {
            poolDatagrid.datagrid("reload");
        },
        cancel: function () {
            poolDialog.dialog("close");
        },
        reset: function () {
            poolForm.form("clear");
        },
        edit: function () {
            var current = poolDatagrid.datagrid("getSelected");
            if (current == null) {
                $.messager.alert("温馨提示", "请选择要编辑的学员信息", "warning");
                return;
            }
            $("[data-cmd='sure']").hide();
            $("[data-cmd='no']").show();
            $("[data-cmd='reset']").show();
            $("[data-cmd='save']").show();
            $("[data-cmd='cancel']").hide();
            $("[data-cmd='search']").hide();
            //清空表单数据
            poolForm.form("clear");
            $.post("/latenStudent/selectByStudentId", {poolId: current.id}, function (current) {
                //设置字段
                if (current.education) {
                    current["education.id"] = current.education.id;
                }
                if (current.wantGrade) {
                    current["wantGrade.id"] = current.wantGrade.id;
                }
                if (current.clienttype) {
                    current["clienttype.id"] = current.clienttype.id;
                }

                if (current.salesman) {
                    current["salesman.id"] = current.salesman.id;
                }
                if (current.wantSchool) {
                    current["wantSchool.id"] = current.wantSchool.id;
                }
                if (current.gender) {
                    current["gender.id"] = current.gender.id;
                }

                if (current.wantLevel) {
                    current["wantLevel.id"] = current.wantLevel.id;
                }

                if (current.otherSalesMan) {
                    current["otherSalesMan.id"] = current.otherSalesMan.id;
                }

                if (current.school) {
                    current["school.id"] = current.school.id;
                }
                if (current.area) {
                    current["area.id"] = current.area.id;
                }
                if (current.wantSubject) {
                    current["wantSubject.id"] = current.wantSubject.id;
                }
                if (current.status) {
                    current["status.id"] = current.status.id;
                }
                if (current.computer) {
                    current["computer.id"] = current.computer.id;
                }
                if (current.comeFrom) {
                    current["comeFrom.id"] = current.comeFrom.id;
                }
                if (current.inputUser) {
                    current["inputUser.id"] = current.inputUser.id;
                }
                poolForm.form("load", current);
                poolDialog.dialog("setTitle", "学员编辑");
                //查询出对应的数据信息
                poolDialog.dialog("open");
            });
        },
        view: function () {
            var current = poolDatagrid.datagrid("getSelected");
            if (current == null) {
                $.messager.alert("温馨提示", "请选择要查看的学员信息", "warning");
                return;
            }
            $("[data-cmd='sure']").hide();
            $("[data-cmd='cancel']").show();
            $("[data-cmd='reset']").hide();
            $("[data-cmd='save']").hide();
            $("[data-cmd='no']").hide();
            $("[data-cmd='search']").hide();
            //清空表单数据
            showForm.form("clear");
            //发送请求去查询数据
            $.post("/latenStudent/selectByStudentId", {poolId: current.id}, function (current) {
                //设置字段
                if (current.education) {
                    current["education.id"] = current.education.id;
                }
                if (current.comeFrom) {
                    current["comeFrom.id"] = current.comeFrom.id;
                }
                if (current.inputUser) {
                    current["inputUser.id"] = current.inputUser.id;
                }
                if (current.wantGrade) {
                    current["wantGrade.id"] = current.wantGrade.id;
                }
                if (current.clienttype) {
                    current["clienttype.id"] = current.clienttype.id;
                }

                if (current.salesman) {
                    current["salesman.id"] = current.salesman.id;
                }
                if (current.wantSchool) {
                    current["wantSchool.id"] = current.wantSchool.id;
                }
                if (current.gender) {
                    current["gender.id"] = current.gender.id;
                }

                if (current.wantLevel) {
                    current["wantLevel.id"] = current.wantLevel.id;
                }

                if (current.otherSalesMan) {
                    current["otherSalesMan.id"] = current.otherSalesMan.id;
                }

                if (current.school) {
                    current["school.id"] = current.school.id;
                }
                if (current.area) {
                    current["area.id"] = current.area.id;
                }
                if (current.wantSubject) {
                    current["wantSubject.id"] = current.wantSubject.id;
                }
                if (current.status) {
                    current["status.id"] = current.status.id;
                }
                if (current.computer) {
                    current["computer.id"] = current.computer.id;
                }
                poolDialog.dialog({
                    buttons: "#viewButtons"
                })
                poolForm.form("load", current);
                poolDialog.dialog("open", true);
            }, "json")
        },
        highSearch: function () {
            poolForm.form("clear");
            poolDialog.dialog("setTitle", "潜在学员高级查询条件");
            $("[data-cmd='sure']").hide();
            $("[data-cmd='cancel']").hide();
            $("[data-cmd='reset']").show();
            $("[data-cmd='save']").hide();
            $("[data-cmd='no']").show();
            $("[data-cmd='search']").show();
            poolDialog.dialog("open");
        },
        no: function () {
            poolDialog.dialog("close");
        },
        search: function () {

        },
        turn: function () {
            var current = poolDatagrid.datagrid("getSelected");
            if (current == null) {
                $.messager.alert("温馨提示", "请选择要转正的学员", "warning");
                return;
            }
            $("[data-cmd='sure']").show();
            $("[data-cmd='no']").show();
            $("[data-cmd='reset']").hide();
            $("[data-cmd='save']").hide();
            $("[data-cmd='cancel']").hide();
            $("[data-cmd='search']").hide();
            //清空表单数据
            poolForm.form("clear");
            $.post("/latenStudent/selectByStudentId", {poolId: current.id}, function (current) {
                //设置字段
                if (current.education) {
                    current["education.id"] = current.education.id;
                }
                if (current.wantGrade) {
                    current["wantGrade.id"] = current.wantGrade.id;
                }
                if (current.clienttype) {
                    current["clienttype.id"] = current.clienttype.id;
                }

                if (current.salesman) {
                    current["salesman.id"] = current.salesman.id;
                }
                if (current.wantSchool) {
                    current["wantSchool.id"] = current.wantSchool.id;
                }
                if (current.gender) {
                    current["gender.id"] = current.gender.id;
                }

                if (current.wantLevel) {
                    current["wantLevel.id"] = current.wantLevel.id;
                }

                if (current.otherSalesMan) {
                    current["otherSalesMan.id"] = current.otherSalesMan.id;
                }

                if (current.school) {
                    current["school.id"] = current.school.id;
                }
                if (current.area) {
                    current["area.id"] = current.area.id;
                }
                if (current.wantSubject) {
                    current["wantSubject.id"] = current.wantSubject.id;
                }
                if (current.status) {
                    current["status.id"] = current.status.id;
                }
                if (current.computer) {
                    current["computer.id"] = current.computer.id;
                }
                if (current.comeFrom) {
                    current["comeFrom.id"] = current.comeFrom.id;
                }
                if (current.inputUser) {
                    current["inputUser.id"] = current.inputUser.id;
                }
                poolForm.form("load", current);
                poolDialog.dialog("setTitle", "学员编辑");
                //查询出对应的数据信息
                poolDialog.dialog("open");
            });
        },
        sure: function () {
            $.messager.confirm("温馨提示", "确定要转正该学员吗?", function (yes) {
                if (yes) {
                    poolForm.form("submit", {
                        url: "/latenStudent/turn",
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    poolDialog.dialog("close");
                                    poolDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }
                    })
                }
            });
        },
        moveTo: function () {
            var current = poolDatagrid.datagrid("getSelected");
            if (!current) {
                $.messager.alert("温馨提示", "请选择要移交的学员信息", "warning");
                return;
            }
            moveForm.form("clear");
            moveForm.form("load",current);
            moveDialog.dialog("open");
        },
        move: function () {
            $.messager.confirm("温馨提示", "确定要进行移交吗?", function (yes) {
                if (yes) {
                    moveForm.form("submit", {
                        url: "/latenStudent/move",
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    moveDialog.dialog("close")
                                    poolDatagrid.datagrid("reload");
                                });
                            } else {
                                 $.messager.alert("温馨提示",data.msg,"error"); 
                            }
                        }
                    })
                }
            });
        },
        moveCancel: function () {
            moveDialog.dialog("close");
        }
    }
    $("a[data-cmd != null]").on("click", function () {
        //调用对应的方法
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    })
    poolDialog.dialog({
        width: 800,
        height: 600,
        closed: true,
        closable: true,
        buttons: "#dialogButtons"
    })
    moveDialog.dialog({
        title: "移交操作",
        width: 500,
        height: 230,
        buttons: "#moveButtons",
        closable:true,
        closed:true
    })
});

function dosearch(value) {
    $("#poolDatagrid").datagrid("load", {
        keyword: value
    })
}

function employeeFormatter(value) {
    if (value) {
        return value.username;
    }
}
function schoolFormatter(value) {
    if (value) {
        return value.name;
    }
}
function wantClassFormatter(value) {
    if (value) {
        return value.name;
    }
}
function clientTypeFormatter(value) {
    if (value) {
        return value.name;
    }
}
function statusFormatter(value) {
    if (value  == -1) {
        return "客户池"
    }
}