<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common.jsp" %>
<script type="text/javascript" src="/static/js/plu/print.js"></script>
<script type="text/javascript" src="/static/js/views/latenStudent.js"></script>
<html>
<head>
    <title>潜在学生管理</title>
</head>
<body>
<div id="tools">
    <form id="inportForm" method="post" enctype="multipart/form-data">
        <input id="input"  name="excel" data-options="buttonText:'导入excel',accept:'xls',style:'width:25%'">
    <a href="javascript:void(0)" onClick="window.open('/latenStudent/download')">下载模板</a>
    </form>
</div>
<div id="student_datagrid"></div>
<div id="datagrid_toolbar">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-cmd="add">添加学员</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-edit" data-cmd="edit">编辑学员</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-lock" data-cmd="view">查看</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="highSearch">高级查询</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-search">快速查询:</a>
    <select id="query_btn" style="margin-top: 10px">
        <option value="-1">-----请选择-----</option>
        <option value="0">今日跟进客户</option>
        <option value="1">意向高</option>
        <option value="2">意向一般</option>
        <option value="3">意向低</option>
        <option value="4">意向不确定</option>
    </select>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-print" data-cmd="print">打印</a>
    <a class="easyui-linkbutton" plain="true" onClick="window.open('/excel/exportForStudent')">导出excel</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-ok" data-cmd="turn">转正</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-cmd="moveTo">移交</a>
    <a class="easyui-linkbutton" plain="true" data-cmd="examLogin">考试登记</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-ok" data-cmd="put">放入客户池</a>
    <a class="easyui-menubutton" plain="true" iconCls="icon-edit" data-options="menu:'#mm'">
        批量修改
    </a>
    <div id="mm" class="easyui-menu" data-options="onClick:batchChange">
        <div >修改意向程度
            <div  class="easyui-menu">
                <div ></div>
            </div>
        </div>
        <div >修改客户类型
            <div class="easyui-menu">

            </div>
        </div>
        <div >修改意向学校
            <div class="easyui-menu">
                <div>a</div>
                <div>b</div>
                <div>c</div>
            </div>
        </div>
    </div>
    <input class="easyui-searchbox" data-cmd="dosearch" data-options="searcher:dosearch,prompt:'电话/QQ/姓名'"/>
</div>
<div id="dialogButtons">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="search">查询</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-undo" data-cmd="reset">重置</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-ok" data-cmd="sure">确定</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-no" data-cmd="cancel">退出</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="no">取消</a>
</div>
<div id="student_dialog">
    <form id="student_form" method="post">
        <input type="hidden" name="id">
        <div style="margin-top:10px">
            <input class="easyui-datebox" name="fileDate" data-options="label:'新建日期',labelAlign:'right',width:280">
            <input  id="salesmanId" class="easyui-textbox"  name="salesman.name" readonly  value="<shiro:principal property="realname"/>"
                   data-options="textField:'username',valueField:'id',url:'/employee/listAllSalesMan',label:'营销人员',labelAlign:'right',width:280">
            <input  id="inputUserId" class="easyui-textbox" name="inputUser.name"  value="<shiro:principal property="realname"/>" readonly
                    data-options="textField:'username',valueField:'id',url:'/employee/listAllSalesMan',label:'录入人',labelAlign:'right',width:280">
        </div>
        <div>
            <input class="easyui-datebox" name="inputTime" data-options="label:'录入时间',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="wantSchool.id" data-options="textField:'name',valueField:'id',
                    url:'/dictionary/listItemByParentId?parentId=1',label:'意向校区',labelAlign:'right',width:280">
            <input class="easyui-datebox" name="nextFollowTime" data-options="label:'下次跟进时间',labelAlign:'right',width:280">
        </div>
        <div>
            <input class="easyui-datebox" name="meetingTime" data-options="label:'约访时间',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="comeFrom.id"
                   data-options="textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=2',label:'来源',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="name" data-options="label:'姓名',labelAlign:'right',width:280">
        </div>
        <div>
            <input class="easyui-textbox" name="wechat" data-options="label:'微信',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="qq"
                   data-options="label:'QQ',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="tel" data-options="label:'电话',labelAlign:'right',width:280">
        </div>
        <div>
            <input class="easyui-textbox" name="age" data-options="label:'年龄',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="gender.id"
                   data-options="textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=10',label:'性别',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="email" data-options="label:'Email',labelAlign:'right',width:280">
        </div>
        <div>
            <input class="easyui-textbox" name="address" data-options="label:'联系地址',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="trainagency"
                   data-options="label:'学校/培训机构',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="school.id" data-options="label:'学校客户',labelAlign:'right',
                width:280,textField:'name',valueField:'id',url:'/school/listAll'">
        </div>
        <div>
            <input class="easyui-combobox" name="education.id"
                   data-options="textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=3',label:'学历',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="major" data-options="label:'专业',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="area.id" data-options="label:'地域',labelAlign:'right',
                width:280,textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=12'">
        </div>
        <div>
            <input class="easyui-textbox" name="workAge" data-options="label:'工作年限',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="sponsor" data-options="label:'介绍人',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="wantSubject.id" data-options="label:'意向学科',labelAlign:'right',
                     width:280,textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=7'">
        </div>
        <div>
            <input class="easyui-combobox" name="wantClass.id" data-options="label:'意向班级',
                 labelAlign:'right',width:280,url:'/grade/listAll',valueField:'id',textField:'name'">
            <input class="easyui-combobox" name="wantLevel.id" data-options="label:'意向程度',labelAlign:'right',width:280,
                     url:'/dictionary/listItemByParentId?parentId=4',valueField:'id',textField:'name'">
            <input class="easyui-combobox" data-options="label:'当前状态',labelAlign:'right',
                     width:280,textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=5'">
        </div>
        <div>
            <input class="easyui-combobox" name="clientType.id" data-options="label:'客户类型',labelAlign:'right',
                 width:280,textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=6'">
            <input class="easyui-combobox" name="otherSalesMan.id"
                   data-options="textField:'username',valueField:'id',url:'/employee/listAllSalesMan',label:'其他营销人员',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="computer.id" data-options="label:'携带笔记本电脑',
                 labelAlign:'right',width:280,textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=13'">
        </div>
        <div>
            <input class="easyui-datebox" name="startingDate" data-options="label:'大学入学时间',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="collageClass"
                   data-options="label:'大学班级',labelAlign:'right',width:280">
        </div>
        <div>
            <input class="easyui-textbox" name="problemFocus"
                   data-options="label:'关注问题',multiline:true,labelAlign:'right',width:800,height:100">
        </div>
        <div>
            <input class="easyui-textbox" name="intro"
                   data-options="label:'备注',multiline:true,labelAlign:'right',width:800,height:100">
        </div>
    </form>
</div>
<div id="moveDialog">
    <form id="moveForm" method="post">
        <input type="hidden" name="qq"/>
        <input type="hidden" name="id"/>
        <input type="hidden" name="name"/>
        <input type="hidden" name="tel"/>
        <div>
            <input class="easyui-combobox" name="targetId" data-options="label:'移交目标',labelAlign:'right',
                width:280,textField:'username',valueField:'id',url:'/employee/selectAllConsulting'">
        </div>
        <div>
            <input class="easyui-textbox" name="reason"
                   data-options="multiline:true,label:'移交原因',labelAlign:'right',width:450,height:100"/>
        </div>
    </form>
</div>
<div id="turnDialog">
    <form id="turnForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id">
        <div style="margin-left:40px;margin-top:10px">
            <input class="easyui-numberbox" name="planTuition" data-options="label:'计划学费',labelAlign:'right',width:280">
            <input class="easyui-combobox" name="cheapWay.id"
                   data-options="textField:'name',valueField:'id',url:'/dictionary/listItemByParentId?parentId=15'
                   ,label:'优惠方式',labelAlign:'right',width:280">
            <input class="easyui-numberbox" name="cheapSum" data-options="label:'优惠金额',labelAlign:'right',width:280">
        </div>
        <div style="margin-left:40px;margin-top:10px">
            <input class="easyui-numberbox" name="tuition" data-options="label:'培训学费',labelAlign:'right',width:280">
            <input class="easyui-numberbox" name="otherCost" data-options="label:'其他费用',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="otherCheap" data-options="label:'其他优惠',labelAlign:'right',width:280">
        </div>
        <div style="margin-left:40px;margin-top:10px">
            <input class="easyui-textbox" name="privilege" data-options="label:'优惠说明',labelAlign:'right',width:280">
            <input class="easyui-numberbox" name="totalAmount"
                   data-options="label:'总费用',labelAlign:'right',width:280">
            <input class="easyui-textbox" name="saleWater" data-options="label:'销售流水',labelAlign:'right',width:280">
        </div>
        <div style="margin-left:40px;margin-top:10px">
            <input class="easyui-numberbox" name="prepaid" data-options="label:'已付学费',labelAlign:'right',width:280">
            <input class="easyui-numberbox" name="arrearage"
                   data-options="label:'还欠学费',labelAlign:'right',width:280">
            <input class="easyui-datebox" name="lastPaymentTime" data-options="label:'最后付款时间',labelAlign:'right',width:280">
        </div>
        <div style="margin-left:40px;margin-top:10px">
            <input class="easyui-datebox" name="nearlyPaymentTime" data-options="label:'上次催款时间',labelAlign:'right',width:280">
            <input class="easyui-datebox" name="nestPaymentTime"
                   data-options="label:'下次催款时间',labelAlign:'right',width:280">
            <input class="easyui-numberbox" name="reminderTimes" data-options="label:'催款次数',labelAlign:'right',width:280">
        </div>
        <div style="margin-left:50px;margin-top:10px">
            <input class="easyui-textbox" name="IDcard" data-options="label:'身份证号',labelAlign:'right',width:500">
        </div>
        <div style="margin-left:50px;margin-top:10px">
            <input class="easyui-textbox" name="emergencyContact" data-options="label:'紧急联系人',labelAlign:'right',width:500">
        </div>
        <div style="margin-left:50px;margin-top:10px">
            <input class="easyui-numberbox" name="emergencyTel" data-options="label:'紧急联系电话',labelAlign:'right',width:500">
        </div>
        <div style="margin-left:10px;margin-top:10px">
            <input class="easyui-filebox" data-options="labelWidth:130,label:'身份证上传复印件',labelAlign:'right',width:540">
        </div>
        <div style="margin-left:50px;margin-top:10px">
            <input class="easyui-filebox" data-options="label:'学位证复印件',labelAlign:'right',width:500">
        </div>

        <div style="margin-left:50px;margin-top:10px">
            <input class="easyui-textbox" name="workExperience" data-options="multiline:true,label:'工作经历',labelAlign:'right',height:150,width:500">
        </div>
    </form>
</div>
<div id="examDialog">
    <form id="examForm" method="post">
        <div>
            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-combobox" name="student.id" data-options="label:'潜在学员',labelAlign:'right',
                width:280,textField:'name',valueField:'id',url:'/latenStudent/listAll'">
            </div>
            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-combobox" name="wantGrade.id" data-options="label:'意向班级',labelAlign:'right',
                width:280,textField:'name',valueField:'id',url:'/grade/listAll'">
            </div>
            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-textbox" name="sn" data-options="label:'考试编号',labelAlign:'right',width:280">
            </div>

            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-textbox" name="name" data-options="label:'名称',labelAlign:'right',width:280">
            </div>

            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-textbox" name="qq" data-options="label:'QQ',labelAlign:'right',width:280">
            </div>
            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-textbox" name="tel" data-options="label:'电话',labelAlign:'right',width:280">
            </div>
            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-datebox" name="time" data-options="label:'考试时间',labelAlign:'right',width:280">
            </div>
            <div style="margin-top:10px;margin-left:50px">
                <input class="easyui-textbox" name="intro" data-options="label:'备注',labelAlign:'right',width:280">
            </div>
        </div>
    </form>
</div>
<div id="moveButtons">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="move">移交</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="moveCancel">取消</a>
</div>
<div id="turnButtons">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-ok" data-cmd="turnSave">转正</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-undo" data-cmd="reset">重置</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div>
<div id="examButtons">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="examSave">登记</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-undo" data-cmd="examReset">重置</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="examCancel">取消</a>
</div>
</body>
</html>
