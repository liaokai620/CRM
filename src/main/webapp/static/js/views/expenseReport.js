/**
 * Created by Administrator on 2017/11/11.
 */
$(function () {
   var expense_datagrid ;
    expense_datagrid = $("#expense_datagrid");

    expense_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        url:"/expenseReport/list",
        rownumbers:true,
        columns:[
            [
                {field:'groupByType',title:'分组类型',width:10},
                {field:'totalMoney',title:'支出总金额',width:10}
            ]
        ]
    });
    $("#expense_combobox").combobox({
        width:120
    });

    $("#expense_linkbutton").linkbutton({
            iconCls:'icon-add',
            onClick:function () {
                var param = $("#expense_form").serialize();
                var url ="/expenseReport/reportByPie?"+param;
                $.dialog.open(url,{
                    id:'ojk',
                    height:600,
                    width: 800,
                    title: '销售报表饼状图',
                    close: function () {
                    }

                })
            }
    });

    //时间查询
    //日期查询
    $("#beginTime").datebox({
        label: '开始时间:',
        labelWidth: 80,
        width: 200
    });
    $("#endTime").datebox({
        label: '结束时间:',
        labelWidth: 80,
        width: 200
    });
    //高级查询
    $("#search_btn").linkbutton({
        iconCls: 'icon-search',
        plain:true,
        onClick: function () {
            var url = '/expenseReport/list';
            var options = expense_datagrid.datagrid('options');
            options.url = "/expenseReport/list";
            options.queryParams = {
                beginTime: $("#beginTime").val(),
                endTime: $("#endTime").val(),
                groupBy: $("#expense_combobox").val()
            };
            expense_datagrid.datagrid('load');
        }
    });

});
