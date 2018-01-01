/**
 * Created by joker on 2017/11/9.
 */
$(function () {
    //对easyui组件的抽取
    var schoolDatagrid = $("#school_datagrid");
    var trainDatagrid = $("#school_datagridOfshowTraining");
    var schoolDialog = $("#school_dialog");
    var queryDialog = $("#query_dialog");
    var addTrainDialog = $("#school_dialogOfshowTrainingAdd");
    var seeDialog = $("#see_dialog");
    var trainDialog = $("#school_dialogOfshowTraining");
    var schoolForm = $("#school_form");
    var queryForm = $("#query_form");
    var seeForm = $("#see_form");
    var trainForm = $("#school_showTrainingForm");

    //联系人数据
    schoolDatagrid.datagrid({
        fit: true,
        singleSelect: true,
        pagination: true,
        url: '/school/list',
        fitColumns: true,
        toolbar: '#school_datagrid_btn',
        columns: [[
            {field: 'id', align: 'center', width: 10, title: '编号'},
            {field: 'name', align: 'center', width: 10, title: '学校名字'},
            {field: 'shortname', align: 'center', width: 10, title: '学校简称'},
            {field: 'tel', align: 'center', width: 10, title: '电话'},
            {field: 'deptId', align: 'center', width: 10, title: '部门', formatter: dept},
            {field: 'star', align: 'center', width: 10, title: '星级', formatter: star},
            {field: 'maket', align: 'center', width: 10, title: '营销人员', formatter: maket},
            {field: 'academicid', align: 'center', width: 10, title: '学制', formatter: academicidd}
        ]]
    });
    //初始化弹框
    schoolDialog.dialog({
        width: 600,
        height: 550,
        buttons: "#school_dialog_btn",
        closed: true
    });
    //初始化查看弹框
    seeDialog.dialog({
        width: 600,
        height: 500,
        closed: true
    });
    //初始化高级查询弹框
    queryDialog.dialog({
        width: 400,
        height: 230,
        buttons: "#query_dialog_btn",
        closed: true
    });
    //初始化实训弹框
    trainDialog.dialog({
        width: 700,
        height: 300,
        closed: true,
        onBeforeOpen: function () {
            var selectedData = schoolDatagrid.datagrid("getSelected");
            //实训数据的展示
            trainDatagrid.datagrid({
                fit: true,
                singleSelect: true,
                url: '/train/list?schoolId='+selectedData.id,
                fitColumns: true,
                toolbar: '#train_datagrid_btn',
                columns: [[
                    {field: 'id', align: 'center', width: 10, title: '编号'},
                    {field: 'content', align: 'center', width: 10, title: '实训内容'},
                    {field: 'time', align: 'center', width: 10, title: '实训时间'},
                    {field: 'address', align: 'center', width: 10, title: '实训地址'},
                    {field: 'effect', align: 'center', width: 10, title: '实训效果'},
                    {field: 'info', align: 'center', width: 10, title: '实训备注'}
                ]]
            });
        }
    });

    //实训新增弹框
    addTrainDialog.dialog({
        width: 500,
        height: 200,
        buttons: "#train_dialog_btn",
        closed: true
    });


    //托马斯瑞式高级搜索
    $('#school_search').searchbox({
        searcher: function (value, name) {
            schoolDatagrid.datagrid("load", {
                keyword: value
            });
        },
        prompt: '请输入值'
    });

    /********************a标签的统一监听**************************************/
    //统一按钮点击监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    var cmdObj = {
        add: function () {
            //设置标题
            schoolDialog.dialog("setTitle", "新增");
            //清楚表单数据
            schoolForm.form("clear");
            //打开弹窗
            schoolDialog.dialog("open");
        },
        //编辑
        update: function () {
            //拿到选中行的数据
            var rowData = schoolDatagrid.datagrid("getSelected");
            if (rowData) {
                //设置标题
                schoolDialog.dialog("setTitle", "编辑");
                //清楚表单数据
                schoolForm.form("clear");
                if (rowData) {
                    rowData["maket.id"] = rowData.maket.id;
                    rowData["nature.id"] = rowData.nature.id;
                    rowData["star.id"] = rowData.star.id;
                }
                //回显数据
                schoolForm.form("load", rowData);
                //打开弹窗
                schoolDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择数据", "info");
            }
        },
        //删除
        delete: function () {
            //拿到选中行
            var rowDATA = schoolDatagrid.datagrid("getSelected");
            if (rowDATA) {
                $.messager.confirm("温馨提示", "姑娘你确定删除吗", function (yes) {
                    if (yes){
                        $.get("/school/delete?id=" + rowDATA.id, function () {
                            //删除成功后 刷新列表
                            schoolDatagrid.datagrid("reload");
                        },"json");
                    }
                });
            }
        },
        //刷新
        reload: function () {
            schoolDatagrid.datagrid("reload");
        },
        //提交表单
        save: function () {
            var url = "/school/save";
            if ($("input[name='id']").val()) {
                url = "/school/update"
            }
            /******************弹框高级查询 数据刷新******************************/
            //表单提交
            schoolForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            //关闭弹框
                            schoolDialog.dialog("close");
                            //刷新
                            schoolDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }
            });
        },

        //取消
        cancel: function () {
            schoolDialog.dialog("close");
        },
        //高级查询
        query: function () {
            queryDialog.dialog("setTitle", "高级查询");
            //清除表单
            queryForm.form("clear");
            queryDialog.dialog("open");
        },
        query_save: function () {
            var url = "/school/list";
            queryForm.form("submit", {
                url: url,
                success:function (data) {
                    data = $.parseJSON(data);
                    queryDialog.dialog("close");
                    schoolDatagrid.datagrid("loadData",data);
                }
            });
        },
        query_cancel: function () {
            queryDialog.dialog("close");
        },
        //查看
        see: function () {
            //拿到选中行的数据
            var rowData = schoolDatagrid.datagrid("getSelected");
            if (rowData) {
                //设置标题
                seeDialog.dialog("setTitle", "查看");
                if (rowData) {
                    rowData["maket.id"] = rowData.maket.id;
                    rowData["nature.id"] = rowData.nature.id;
                    rowData["star.id"] = rowData.star.id;
                }
                //回显数据
                seeForm.form("load", rowData);
                //打开弹窗
                seeDialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择数据", "info");
            }
        },
        //实训相关
        train: function () {
            var selectedData = schoolDatagrid.datagrid("getSelected");
            if (selectedData) {
                trainDialog.dialog("setTitle", "实训信息");
                trainDialog.dialog("open");
            }else{
                $.messager.alert("温馨提示","请选择数据","info");
            }
        },
        train_add: function () {
            addTrainDialog.dialog("setTitle", "新增");
            trainForm.form("clear");
            addTrainDialog.dialog("open");
        },
        train_update: function () {
            var trainData = trainDatagrid.datagrid("getSelected");
            console.log(trainData);
            if (trainData){
                addTrainDialog.dialog("setTitle", "编辑");
                trainForm.form("clear");
                //回显数据
                trainForm.form("load",trainData);
                addTrainDialog.dialog("open");
            }else {
                $.messager.alert("温馨提示","翠花!请选择数据","info");
            }
        },
        train_save: function () {
            var selectedData = schoolDatagrid.datagrid("getSelected");
            var id = selectedData.id;
            var url = "/train/save"
            if ($("#trainingID").val()) {
                url = "/train/update"
            }
            //提交表单
            trainForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //关闭唐狂
                        addTrainDialog.dialog("close");
                        //刷星表格
                        trainDatagrid.datagrid("reload");
                    }
                },
                //将schoolId作为表单内容
                onSubmit:function (param) {
                        param.schoolId=id
                }
            })
        },
        train_cancel: function () {
            addTrainDialog.dialog("close");
        },
        train_delete: function () {
            var trainData = trainDatagrid.datagrid("getSelected");
            if (trainData){
                $.messager.confirm("温馨提示","姑娘!你确定要删除它吗?",function () {
                   //发送ajax请求删除
                    $.get("/train/delete?trainId="+trainData.id,function () {
                        //关闭弹框
                        addTrainDialog.dialog("close");
                        //刷新数据
                        trainDatagrid.datagrid("reload");
                    },"json")
                });
            }
        },
        train_reload: function () {
            trainDatagrid.datagrid("reload");
        }
    }
});

function dept(value) {
    if (value == 1) {
        return "教育部";
    }
    if (value == 2) {
        return "工信部";
    }
    if (value == 3) {
        return "国防部";
    }
}

function star(value) {
    if (value) {
        return value.name;
    }
}

function maket(value) {
    if (value) {
        return value.realname;
    }
}

function academicidd(value) {
    if (value == 2) {
        return "两年";
    }
    if (value == 4) {
        return "四年";
    }
    if (value == 5) {
        return "五年(3+2)";
    }
}
