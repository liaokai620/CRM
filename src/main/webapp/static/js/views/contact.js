/**
 * Created by joker on 2017/11/9.
 */
$(function () {
    //对easyui组件的抽取
    var contactDatagrid = $("#contact_datagrid");
    var contactDialog = $("#contact_dialog_bt");
    var queryDialog = $("#query_dialog");
    var seeDialog = $("#see_dialog");
    var contactForm = $("#contact_form");
    var queryForm = $("#query_form");
    var seeForm = $("#see_form");
    //联系人数据
    contactDatagrid.datagrid({
        fit: true,
        singleSelect: true,
        pagination: true,
        url: '/contact/list',
        fitColumns: true,
        toolbar: '#contact_datagrid_btn',
        columns: [[
            {field: 'id', align: 'center', width: 10, title: '编号'},
            {field: 'name', align: 'center', width: 10, title: '姓名'},
            {field: 'school', align: 'center', width: 10, title: '所属学校', formatter: schoolFormat},
            {field: 'tel', align: 'center', width: 10, title: '电话'},
            {field: 'email', align: 'center', width: 10, title: '邮箱'},
            {field: 'main', align: 'center', width: 10, title: '主要联系人', formatter: main}
        ]]
    });
    //托马斯瑞式高级搜索
    $('#scontact_search').searchbox({
        searcher: function (value, name) {
            contactDatagrid.datagrid("load", {
                keyword: value
            });
        },
        prompt: '请输入值'
    });

    //初始化高级查询弹框
    queryDialog.dialog({
        width: 400,
        height: 230,
        buttons: "#query_dialog_btn",
        closed: true
    });

    //新增编辑弹框
    contactDialog.dialog({
        width: 600,
        height: 500,
        closed: true,
        buttons: "#contact_dialog_btn"
    });
    //新增编辑弹框
    seeDialog.dialog({
        width: 600,
        height: 500,
        closed: true,
    });
    //列表顶部按钮的统一监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    //按钮方法的实现
    var cmdObj = {
        add: function () {
            //设置title
            contactDialog.dialog("setTitle", "新增");
            //清除表单
            contactForm.form("clear");
            //打开弹框
            contactDialog.dialog("open");
        },
        //拿到选中的数据
        edit: function () {
            var rowData = contactDatagrid.datagrid("getSelected");
            if (rowData){
                //设置title
                contactDialog.dialog("setTitle", "编辑");
                //清除表单
                contactForm.form("clear");
                //打开弹框
                contactDialog.dialog("open");
                if (rowData.school) {
                    rowData["school.id"] = rowData.school.id;
                }
                //回显表单数据
                contactForm.form("load", rowData);
            }else{
                $.messager.alert("温馨提示","姑娘,请选择数据","warning");
            }
        },
        delete: function () {
            var rowData = contactDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "翠花你确定要删除吗", function (yes) {
                    if (yes) {

                        $.get("/contact/delete?id=" + rowData.id, function () {
                            contactDatagrid.datagrid("reload");
                        }, "json");
                    }
                });
            }
        },
        reload: function () {
            contactDatagrid.datagrid("reload");
        },
        cancel: function () {
            contactDialog.dialog("close");
        },
        save: function () {
            var url = "/contact/save";
            var val = $(" input[name='id']").val();
            if (val) {
                url = "/contact/edit"
            }
            console.log(url);
            //提交表单
            contactForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            contactDialog.dialog("close");
                            contactDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //高级查询
        query: function () {
            queryDialog.dialog("setTitle", "高级查询");
            //清除表单
            queryForm.form("clear");
            queryDialog.dialog("open");
        },
        query_save: function () {
            var url = "/contact/list";
            queryForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    //关闭弹框
                    queryDialog.dialog("close");
                    //刷新数据
                    contactDatagrid.datagrid("loadData", data);
                }
            });
        },
        query_cancel: function () {
            queryDialog.dialog("close");
        },
        see: function () {
            var rowData = contactDatagrid.datagrid("getSelected");
            console.log(rowData);
            if (rowData) {
                //设置title
                seeDialog.dialog("setTitle", "查看");
                //打开弹框
                seeDialog.dialog("open");
                if (rowData) {
                    rowData["school.id"] = rowData.school.id;
                }
                //回显表单数据
                seeForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择数据", "warning");
            }
        }
    }
});

//学校的格式化
function schoolFormat(value) {
    if (value) {
        return value.name;
    }
}
//是否是主要联系人
function main(value) {
    if (value == 0) {
        return "否";
    } else {
        return "是";
    }
}