/**
 * Created by Administrator on 2017/11/10.
 */
$(function () {
    var sale_datagrid, sale_textbox;
    sale_datagrid = $("#sale_datagrid");
    sale_textbox = $("#sale_textbox");
    //datagrid
    sale_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: "#tb",
        singleSelect: true,
        url: "/performance/list",
        rownumbers:true,
        columns: [
            [
                {field: 'time', title: '录入时间', width: 10},
                {field: 'saleMan', title: '销售人员', width: 10},
                {field: 'studentNum', title: '正式学员数量', width: 10}
            ]
        ]
    });
    //高级查询
    $("#search_btn").linkbutton({
        iconCls: 'icon-search',
        plain:true,
        onClick: function () {
            var options = sale_datagrid.datagrid('options');
            options.url = "/performance/list";
            options.queryParams = {
                beginTime: $("#beginTime").val(),
                endTime: $("#endTime").val(),
                keyword: $("#sale_textbox").val()
            };
            sale_datagrid.datagrid('load');
        }
    });

    //关键字查询
    sale_textbox.textbox({
        width: 180,
        label: "关键字:",
        labelWidth: 50,
        prompt: "请输入销售人员名字"
    });
    //日期查询
    $("#beginTime").datebox({
        label: '开始时间:',
        labelWidth: 60,
        width: 200
    });
    $("#endTime").datebox({
        label: '结束时间:',
        labelWidth: 60,
        width: 200
    });
    //查看报表
    $("#report_btn").linkbutton({
        iconCls: 'icon-add',
        plain:true,
        onClick: function () {
            var param = $("#sale_form").serialize();
            var url = "/performance/saleChartByPie?"+param;
            $.dialog.open(url, {
                id: 'ooxx',
                height:600,
                width: 800,
                title: '销售报表柱状图',
                close: function () {
                }
            });
        }
    })


});


