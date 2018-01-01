$(function () {
   var attendanceDataGrid,searchNameBtn,searchStartBtn,searchEndBtn,searchStateBtn;
   attendanceDataGrid = $("#attendance_dataGrid");
    searchNameBtn = $("#searchNameBtn");
    searchStartBtn = $("#searchstartBtn");
    searchEndBtn = $("#searchendBtn");
    searchStateBtn=$("#searchStateBtn");

   attendanceDataGrid.datagrid({
       fit:true,
       rownumbers:true,
       singleSelect:true,
       pagination:true,
       url:'/attendance/list',
       fitColumns:true,
       toolbar:'#tb',
       columns:[
           [
               {field:'employee',align:'center',width:10,formatter:nameFormatter,title:'员工名字'},
               {field:'worktime',align:'center',width:10,title:'规定时间'},
               {field:'clocktime',align:'center',width:10,title:'打卡时间'},
               {field:'state',align:'center',width:10,formatter:stateFormatter,title:'状态'}
           ]
       ]
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
        label: "起始时间:",
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
        onClickButton: searchComit
    });
    searchStateBtn.combobox({
        width: 230,
        label: "状态:",
        labelWidth: 80,
        prompt: "请选择搜索状态",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: searchComit
    });
    function searchComit() {
        var keyword = $(searchNameBtn).val();
        var startTime=$(searchStartBtn).val();
        var endTime=$(searchEndBtn).val();
        var state = $(searchStateBtn).val();
        attendanceDataGrid.datagrid("load", {
            name: keyword,
            beginDate: startTime,
            endDate: endTime,
            state: state
        });
    }
});

function stateFormatter(value,record,index){
    if(value==0){
        return "<font color='green'>正常</font>";
    }else if(value==1){
        return "<font color='red'>迟到</font>";
    }else if(value==2){
        return "<font color='blue'>休息</font>";
    }else if(value==3){
        return "<font color='#ffb6c1'>请假</font>";
    }
}

function nameFormatter(value,record,index){
    if(value.realname){
        return value.realname;
    }
    return value;
}

