$(function () {
    //对页面中的元素进行抽取.
    //方法太凌乱,希望统一管理
    //按钮在JS统一进行监听
    var historyDatagrid;
    historyDatagrid = $("#historyDatagrid");
    //数据表格
    historyDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/history/list',
        fitColumns: true,
        toolbar: "#historyToolbar",
        columns: [[
            {field: 'name', align: 'center', width: 10, title: '学生姓名'},
            {field: 'qq', align: 'center', width: 10, title: 'QQ',},
            {field: 'tel', align: 'center', width: 10, title: '联系方式'},
            {field: 'date', align: 'center', width: 10, title: '日期',},
            {field: 'reason', align: 'center', width: 10, title: '原因', },
            {field: 'beforeOwner', align: 'center', width: 10, title: '原拥有者', formatter: targetFormatter},
            {field: 'targetOwner', align: 'center', width: 10, title: '移交目标', formatter: beforeFormatter},
        ]],
    });
    //方法统一管理起来]
    var cmdObj = {
        reload: function () {
            historyDatagrid.datagrid("options").url = "/history/list";
            //刷新数据表格
            historyDatagrid.datagrid("reload");
        },
        showAll: function () {
            historyDatagrid.datagrid({
                pagination : false
            })
            historyDatagrid.datagrid("options").url = "/history/listAll";
            historyDatagrid.datagrid("reload")
        },
    }

    $("a[data-cmd != null]"
    ).on("click", function () {
        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    })
});

function searcher(value) {
    $("#historyDatagrid").datagrid("reload",{
        keyword:value
    });
}



function targetFormatter(value) {
    if (value) {
        return value.username
    }
    return "";
}

function beforeFormatter(value) {
    if (value) {
        return value.username
    }
    return "";
}

