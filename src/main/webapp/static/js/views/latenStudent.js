$(function () {
    var studentDatagrid, studentDialog, studentForm, keyword, showDialog, showForm, turnDialog, turnForm,
        searchDialog, moveDialog, moveForm, examDialog, examForm;
    studentForm = $("#student_form");
    studentDialog = $("#student_dialog");
    studentDatagrid = $("#student_datagrid");
    keyword = $("#keyword");
    showDialog = $("#showDialog");
    showForm = $("#showForm");
    searchDialog = $("#searchDialog");
    moveDialog = $("#moveDialog");
    moveForm = $("#moveForm");
    examForm = $("#examForm");
    examDialog = $("#examDialog");
    turnDialog = $("#turnDialog");
    turnForm = $("#turnForm");
    studentDatagrid.datagrid({
        url: "/latenStudent/list",
        fit: true,
        toolbar: "#datagrid_toolbar",
        fitColumns: true,
        rownumbers: true,
        tools: "#tools",
        pagination: true,
        singleSelect: true,
        columns: [[
            {field: "name", title: "姓名", width: 10, align: "center"},
            {field: "salesman", title: "销售人员", width: 10, align: "center", formatter: employeeFormatter},
            {field: "meetingTime", title: "约访日期", width: 10, align: "center"},
            {field: "nextFollowTime", title: "下次跟进时间", width: 10, align: "center"},
            {field: "qq", title: "QQ", width: 10, align: "center"},
            {field: "tel", title: "电话", width: 10, align: "center"},
            {field: "school", title: "学校", width: 10, align: "center", formatter: schoolFormatter},
            {field: "wantLevel", title: "意向等级", width: 10, align: "center", formatter: wantLevelFormatter},
            {field: "wantSchool", title: "意向学校", width: 10, align: "center", formatter: wantSchoolFormatter},
            {field: "intro", title: "备注", width: 10, align: "center"},
            {field: "clientType", title: "客户类型", width: 10, align: "center", formatter: clientTypeFormatter},
            {field: "status", title: "状态", width: 10, align: "center", formatter: statusFormatter}
        ]]
    });

    var cmdObj = {
        add: function () {

            //刷新表单
            var current = $("#salesmanId").textbox('getValue');
            studentForm.form("clear");
            //设置表单标题
            studentDialog.dialog("setTitle", "学员添加");
            $("[data-cmd='sure']").hide();
            $("[data-cmd='no']").show();
            $("[data-cmd='reset']").show();
            $("[data-cmd='save']").show();
            $("[data-cmd='cancel']").hide();
            $("[data-cmd='search']").hide();
            $("#salesmanId").textbox("setValue", current);
            $("#inputUserId").textbox("setValue", current);
            //打开窗口
            studentDialog.dialog("open");
        },
        save: function () {
            var url = "/latenStudent/save";
            //获取id
            console.log($("[name = id]").val());
            if ($("[name=id]").val() > 0) {
                url = "/latenStudent/update";
            }
            studentForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            studentDialog.dialog("close");
                            studentDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            })
        },
        reload: function () {
            studentDatagrid.datagrid("reload");
        },
        cancel: function () {
            studentDialog.dialog("close");
        },
        reset: function () {
            studentForm.form("clear");
        },
        edit: function () {
            var current = studentDatagrid.datagrid("getSelected");
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
            studentForm.form("clear");
            $.post("/latenStudent/selectByStudentId", {studentId: current.id}, function (current) {
                //设置字段
                if (current.education) {
                    current["education.id"] = current.education.id;
                }
                if (current.wantGrade) {
                    current["wantGrade.id"] = current.wantGrade.id;
                }
                if (current.clientType) {
                    current["clientType.id"] = current.clientType.id;
                }

                if (current.salesman) {
                    current["salesman.name"] = current.salesman.name;
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
                    current["inputUser.name"] = current.inputUser.name;
                }
                studentForm.form("load", current);
                studentDialog.dialog("setTitle", "学员编辑");
                //查询出对应的数据信息
                studentDialog.dialog("open");
            });
        },
        view: function () {
            var current = studentDatagrid.datagrid("getSelected");
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
            $.post("/latenStudent/selectByStudentId", {studentId: current.id}, function (current) {
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
                if (current.wantClass) {
                    current["wantClass.id"] = current.wantClass.id;
                }
                if (current.clientType) {
                    current["clientType.id"] = current.clientType.id;
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
                studentDialog.dialog({
                    buttons: "#viewButtons"
                })
                studentForm.form("load", current);
                studentDialog.dialog("open", true);
            }, "json")
        },
        highSearch: function () {
            studentForm.form("clear");
            studentDialog.dialog("setTitle", "潜在学员高级查询条件");
            $("[data-cmd='sure']").hide();
            $("[data-cmd='cancel']").hide();
            $("[data-cmd='reset']").show();
            $("[data-cmd='save']").hide();
            $("[data-cmd='no']").show();
            $("[data-cmd='search']").show();
            studentDialog.dialog("open");
        },
        no: function () {
            studentDialog.dialog("close");
        },
        turn: function () {
            var current = studentDatagrid.datagrid("getSelected");
            if (current == null) {
                $.messager.alert("温馨提示", "请选择要转正的学员", "warning");
                return;
            }
            turnForm.form("clear");
            turnForm.form("load", current);
            turnDialog.dialog("open");
        },
        sure: function () {
            $.messager.confirm("温馨提示", "确定要转正该学员吗?", function (yes) {
                if (yes) {
                    studentForm.form("submit", {
                        url: "/latenStudent/turn",
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    turnDialog.dialog("close");
                                    studentDatagrid.datagrid("reload");
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
            var current = studentDatagrid.datagrid("getSelected");
            if (!current) {
                $.messager.alert("温馨提示", "请选择要移交的学员信息", "warning");
                return;
            }
            moveForm.form("clear");
            moveForm.form("load", current);
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
                                    moveDialog.dialog("close");
                                    studentDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }
                    })
                }
            });
        },
        moveCancel: function () {
            moveDialog.dialog("close");
        },
        turnSave: function () {
            $.messager.confirm("温馨提示", "确定进行转正操作吗?", function (yes) {
                if (yes) {
                    turnForm.form("submit", {
                        url: "/latenStudent/turn",
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    turnDialog.dialog("close");
                                    studentDatagrid.datagrid("reload")
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }
                    })
                }
            });
        },
        examLogin: function () {
            examForm.form("clear");
            examDialog.dialog("open");
        },
        examSave: function () {
            examForm.form("submit", {
                url: "/exam/save",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            examDialog.dialog("close");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error")
                    }
                }
            })
        },
        put: function () {
            var current = studentDatagrid.datagrid("getSelected");
            if (current == null) {
                $.messager.alert("温馨提示", "请选择要放入客户池的学员", "warning");
                return;
            }
            $.messager.confirm("温馨提示", "确定将该学员移交到客户池吗?", function (yes) {
                if (yes) {
                    $.post("/latenStudent/put", {studentId: current.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg, "info", function () {
                                studentDatagrid.datagrid("reload")
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg, "info")
                        }
                    }, "json")
                }
            });
        },
        batchEdit: function () {
            console.log($(this).val());
        },
        print: function () {
            CreateFormPage("潜在学员打印", studentDatagrid);
        },
        export: function () {
            window.location.href = "/excel/exportForStudent";
        },

    }

    $("#input").filebox({
        onChange: function () {
            $("#inportForm").form("submit",{
                url:"/latenStudent/inport",
                success: function (data){
                    data = $.parseJSON(data);
                    if(data.success){
                         $.messager.alert("温馨提示",data.msg,"info",function (){
                            studentDatagrid.datagrid("reload");
                         });
                    }else{
                         $.messager.alert("温馨提示",data.msg,"error");
                    }
                }
            })
        }
    })

    $("a[data-cmd != null]").on("click", function () {
        //调用对应的方法
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    studentDialog.dialog({
        width: 1000,
        height: 550,
        closed: true,
        closable: true,
        buttons: "#dialogButtons"
    });
    moveDialog.dialog({
        title: "移交操作",
        width: 500,
        height: 230,
        buttons: "#moveButtons",
        closable: true,
        closed: true
    });
    examDialog.dialog({
        title: "考试登记",
        width: 400,
        height: 380,
        buttons: "#examButtons",
        closable: true,
        closed: true
    });
    turnDialog.dialog({
        title: "学员转正",
        width: 1000,
        height: 550,
        closed: true,
        closable: true,
        buttons: "#turnButtons"
    });
    //快速查询
    $("#query_btn").combobox({
        labelWidth:"70",
        width:"120",
        onChange:function (oldValue) {

            var url = "/latenStudent/list";
            var options = studentDatagrid.datagrid("options");
            options.url= url;
            options.queryParams = {
                queryId:oldValue
            };
            studentDatagrid.datagrid('load');
        }
    })
});

function dosearch(value) {
    $("#student_datagrid").datagrid("load", {
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
function wantLevelFormatter(value) {
    if (value) {
        return value.name;
    }
}
function wantSchoolFormatter(value) {
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
    if (value == 1) {
        return "正式学员";
    } else if (status == -1) {
        return "客户池";
    } else {
        return "潜在客户";
    }
}
function batchChange() {
    console.log(arguments);
}