$(function () {
    var salaryDatagrid, salaryEditBtnAndQuitBtn, salaryDialog,
        salaryForm,searchNameBtn,searchStartBtn,searchEndBtn,
        searchStateBtn;
    salaryDatagrid = $("#salary_dataGrid");
    salaryEditBtnAndQuitBtn = $("#salary_editBtn,#salary_quitBtn");
    salaryDialog = $("#salary_dialog");
    salaryForm = $("#salary_form");
    searchNameBtn = $("#searchnameBtn");
    searchStartBtn = $("#searchstartBtn");
    searchEndBtn = $("#searchendBtn");
    searchStateBtn=$("#searchStateBtn");
    salaryDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/salary/list',
        fitColumns: true,
        toolbar:'#tb',
        columns: [
            [
                {field: 'employee', align: 'center', width: 10, formatter: nameFormatter, title: '员工姓名'},
                {field: 'basesalary', align: 'center', width: 10, title: '基本工资'},
                {field: 'alldaysalary', align: 'center', width: 10, title: '全勤奖金'},
                {field: 'subsidy', align: 'center', width: 10, title: '补贴'},
                {field: 'fivesalary', align: 'center', width: 10, title: '五险一金'},
                {field: 'fine', align: 'center', width: 10, title: '罚款'},
                {field: 'commission', align: 'center', width: 10, title: '提成'},
                {field: 'totalsalary', align: 'center', width: 10, title: '总工资'},
                {field: 'aftersalary', align: 'center', width: 10, title: '税后工资'},
                {field: 'yearendsalary', align: 'center', width: 10, title: '年终奖'},
                {field: 'salarytime', align: 'center', width: 10, title: '日期'},
                {field: 'state', align: 'center', width: 10, formatter: stateFormatter,title: '状态'}
            ]
        ], //数据表格
        onClickRow: function (rowIndex, rowData) {
            //判断当前记录中的状态的值.
            if (rowData.state == 0) {
                //员工已经离职了,编辑和离职按钮应该变灰.
                salaryEditBtnAndQuitBtn.linkbutton("disable");
            } else {
                //启用按钮
                salaryEditBtnAndQuitBtn.linkbutton("enable");
            }
        }
    });
    //对话框
    salaryDialog.dialog({
        width: 550,
        height: 380,
        buttons: '#bt',
        closed: true
    });
    searchNameBtn.textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 80,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    searchStartBtn.datetimebox({
        width: 230,
        label: "开始时间:",
        labelWidth: 80,
        prompt: "请输入搜索开始时间",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    searchEndBtn.datetimebox({
        width: 230,
        label: "结束时间:",
        labelWidth: 80,
        prompt: "请输入搜索结束时间",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton:searchComit
    });
    searchStateBtn.combobox({
        width: 130,
        labelWidth: 80,
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    function searchComit() {
        var keyword = $(searchNameBtn).val();
        var startTime=$(searchStartBtn).val();
        var endTime=$(searchEndBtn).val();
        var state = $(searchStateBtn).val();
        var month=$("#makedate").val();
        salaryDatagrid.datagrid("load", {
            name: keyword,
            beginDate: startTime,
            endDate: endTime,
            month:month,
            state: state
        });
    }

    //对按钮进行统一事件监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    //方法统一管理起来]
    var cmdObj = {
        add: function () {
            //1.清空表单数据
            salaryForm.form("clear");
            //2.设置对话框的标题
            salaryDialog.dialog("setTitle", "新增");
            //3.打开对话框
            salaryDialog.dialog("open");
        },
        edit: function () {
            var rowData = salaryDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                salaryForm.form("clear");
                //2.设置对话框的标题
                salaryDialog.dialog("setTitle", "新增");
                //3.打开对话框
                salaryDialog.dialog("open");
                //特殊数据的处理
                if (rowData.employee)
                    rowData["employee.id"] = rowData.employee.id;
                //4.回显数据
                salaryForm.form("load", rowData);//基于同名匹配规则

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }

        },
        quit: function () {
            var rowData = salaryDatagrid.datagrid("getSelected");
            if(rowData){
                $.messager.confirm("温馨提示","您已经检查无误了么",function(yes){
                    if(yes){
                        $.get("/salary/quit?id="+rowData.id,function(data){
                            if(data.success){
                                salaryDatagrid.datagrid("reload");
                                $.messager.alert("温馨提示",data.msg,"info");
                            }else{
                                $.messager.alert("温馨提示",data.msg,"error");
                            }
                        },"json")
                    }
                });
            }else{
                $.messager.alert("温馨提示","请选择需要离职的员工记录.","warning");
            }
        },
        out:function () {
            location.href="excel/outSalary";
        },
        print:function () {
            CreateFormPage("薪资表打印",$("#salary_dataGrid"));
        },
        reload: function () {
            //刷新数据表格
            salaryDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/salary/update";
            } else {
                url = "/salary/save";
            }
            salaryForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            salaryDialog.dialog("close");
                            salaryDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            salaryDialog.dialog("close");
        }
    }
    $('#makedate').datebox({
        width: 230,
        label: "月份:",
        labelWidth: 40,
        buttonIcon: 'icon-search',
        onClickButton:searchComit,
        type:"text",
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); //触发click事件弹出月份层
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(span.html())[0]//得到年份
                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                    $('#makedate').datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                });
            }, 0);
            yearIpt.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) {
            var a = parseInt(d.getMonth())<parseInt('9')?'0'+parseInt(d.getMonth()+ 1):d.getMonth() + 1;
            return d.getFullYear() + '-' +a; }
    });
    var p = $('#makedate').datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        yearIpt = p.find('input.calendar-menu-year'),//年份输入框
        span = p.find('span.calendar-text'); //显示月份层的触发控件
    console.log(yearIpt)

});

function nameFormatter(value,record,index){
    if(value.realname){
        return value.realname;
    }
    return value;
}

function stateFormatter(value,record,index){
    if(value==0){
        return "<font color='green'>已确认</font>";
    }else if(value==1){
        return "<font color='red'>未确认</font>";
    }
}



