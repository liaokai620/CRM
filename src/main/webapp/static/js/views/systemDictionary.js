/**
 * Created by Administrator on 2017/11/9.
 */
$(function () {
    var systemDictionary_datagrid, systemDictionary_dialog, systemDictionary_form, current;
    systemDictionary_dialog = $("#systemDictionary_dialog");
    systemDictionary_datagrid = $("#systemDictionary_datagrid");
    systemDictionary_form = $("#systemDictionary_form");

    var item_datagrid, item_form, item_dialog;
    item_datagrid = $("#item_datagrid");
    item_form = $("#item_form");
    item_dialog = $("#item_dialog");

    var rowId;
    //字典目录列表
    systemDictionary_datagrid.datagrid({
        toolbar: "#tb",
        fit: true,
        fitColumns: true,
        pagination:true,
        url: "/dictionary/list",
        rownumbers: true,
        pageSize:20,
        pageList:[10,20,30],
        singleSelect: true,
        columns: [
            [
                {field: 'sn', title: '编号', width: 10},
                {field: 'name', title: '名称', width: 10},
                {field: 'intro', title: '简介', width: 10}
            ]
        ],
        onClickRow: function (index, row) {
            var options = item_datagrid.datagrid("options");
            options.url = "/dictionary/listItemByParentId";
            item_datagrid.datagrid("load", {parentId: row.id});
            current = row;
        }
    });
    //字典明细列表
    item_datagrid.datagrid({
        toolbar: "#item_btn",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        columns: [
            [
                {field: 'parent', title: '字典目录', width: 10, formatter: formatterItem},
                {field: 'name', title: '字典明细名称', width: 10},
                {field: 'intro', title: '字典明细简介', width: 10}
            ]
        ]
    });


    //dialog窗口
    systemDictionary_dialog.dialog({
        height: 200,
        width: 251,
        buttons: "#bt",
        closed: true
    });
    item_dialog.dialog({
        height: 250,
        width: 351,
        buttons: "#item_bt",
        closed: true
    });
    //统一管理功能按钮
    var cmdObj = {
        //新增操作
        add: function () {

            //打开前清除form表单的数据
            systemDictionary_form.form("clear");
            //打开dialog窗口
            systemDictionary_dialog.dialog("open");
            //设置名称
            systemDictionary_dialog.dialog("setTitle", "新增");
        },
        //编辑操作
        edit: function () {
            //判断当前是否选中数据了
            var currentRow = systemDictionary_datagrid.datagrid("getSelected");
            if (!currentRow) {
                $.messager.alert("温馨提示", "请选择你需要编辑的内容", "warning");
                return;
            }
            //打开前清除form表单的数据
            systemDictionary_form.form("clear");
            //打开dialog窗口
            systemDictionary_dialog.dialog("open");
            //设置名称
            systemDictionary_dialog.dialog("setTitle", "编辑");
            //回显操作
            systemDictionary_form.form("load", currentRow);

        },
        //删除操作
        remove: function () {
            //判断当前是否选中数据了
            var currentRow = systemDictionary_datagrid.datagrid("getSelected");
            if (!currentRow) {
                $.messager.alert("温馨提示", "请选择你需要删除的内容", "warning");
                return;
            }
            $.messager.confirm("温馨提示", "请问你确定需要删除该内容吗?", function (r) {
                if (r) {

                    $.get("/dictionary/delete?id=" + currentRow.id + "", function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg, "info");
                            //刷新页面
                            systemDictionary_datagrid.datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                            systemDictionary_datagrid.datagrid("reload");
                        }
                    }, 'json')
                }
            })
        },
        //刷新操心
        reload: function () {
            systemDictionary_datagrid.datagrid("load");
        },
        //提交操作
        save: function () {
            //判断当前的提交操作是新增还是编辑的
            var url;
            var currentId = $("[name=id]").val();
            if (currentId) {
                url = "/dictionary/edit";
            } else {
                url = "/dictionary/save";
            }
            systemDictionary_form.form('submit', {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);

                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        //关闭窗口
                        systemDictionary_dialog.dialog('close');
                        //刷新页面
                        systemDictionary_datagrid.datagrid('reload');
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                        //关闭窗口
                        systemDictionary_dialog.dialog('close');
                        //刷新页面
                        systemDictionary_datagrid.datagrid('reload');
                    }
                }
            })
        },
        //cancel操作
        cancel: function () {
            systemDictionary_dialog.dialog("close");
        },


        //item的功能操作
        //新增操作
        addItem: function () {
            //需要先选父类
            var currentRow = systemDictionary_datagrid.datagrid("getSelected");
            if (!currentRow) {
                $.messager.alert("温馨提示", "请选择你的字典目录", "warning");
                return;
            }
            //设置名称
            item_dialog.dialog("setTitle", "新增");
            //打开前清除form表单的数据
            item_form.form("clear");
            //打开dialog窗口
            item_dialog.dialog("open");
            $("[name='parent.id']").val(current.id);
            $("#parentName").textbox("setValue", current.name);

        },
        //编辑操作
        editItem: function () {
            var currentRow = systemDictionary_datagrid.datagrid("getSelected");
            if (!currentRow) {
                $.messager.alert("温馨提示", "请选择你的字典目录", "warning");
                return;
            }
            var currentItem = item_datagrid.datagrid("getSelected");
            if (!currentItem) {
                $.messager.alert("温馨提示", "请选择你需要编辑的字典明细内容", "warning");
                return;
            }
            //打开前清除form表单的数据
            item_form.form("clear");
            //打开dialog窗口
            item_dialog.dialog("open");
            $("[name='parent.id']").val(current.id);
            $("#parentName").textbox("setValue", current.name);
            //设置名称
            item_dialog.dialog("setTitle", "编辑");
            //回显
            item_form.form("load", currentItem);
        },
        //删除操作
        removeItem: function () {
            var currentRow = systemDictionary_datagrid.datagrid("getSelected");
            if (!currentRow) {
                $.messager.alert("温馨提示", "请选择你的字典目录", "warning");
                return;
            }
            var currentItem = item_datagrid.datagrid("getSelected");
            if (!currentItem) {
                $.messager.alert("温馨提示", "请选择你需要删除的字典明细内容", "warning");
                return;
            }
            $.messager.confirm("温馨提示", "请问你确定需要删除该内容吗?", function (r) {
                if (r) {

                    $.get("/dictionary/deleteItem?id=" + currentItem.id + "", function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg, "info");
                            //刷新页面
                            item_datagrid.datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                            item_datagrid.datagrid("reload");
                        }
                    }, 'json')
                }
            })
        },
        //刷新功能
        reloadItem: function () {
            item_datagrid.datagrid("load");
        },
        //保存操作
        saveItem: function () {
            //判断当前的提交操作是新增还是编辑的
            var url;
            var currentId = $("#itemId").val();
            if (currentId) {
                url = "/dictionary/editItem";
            } else {
                url = "/dictionary/saveItem";
            }
            item_form.form('submit', {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);

                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        //关闭窗口
                        item_dialog.dialog('close');
                        //刷新页面
                        item_datagrid.datagrid('reload');
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                        //关闭窗口
                        item_dialog.dialog('close');
                        //刷新页面
                        item_datagrid.datagrid('reload');
                    }
                }
            })
        },
        //cancel操作
        cancel: function () {
            item_dialog.dialog("close");
        }
    };
    //统一绑定事件
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    })

    //关键字查询
    $("#dic_textbox").textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 50,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: function () {
            var keyword = $(this).val();
            systemDictionary_datagrid.datagrid("load", {
                keyword: keyword
            });
        }
    });
});
function formatterItem(value) {
    if (value) {
        return value.name;
    }
}