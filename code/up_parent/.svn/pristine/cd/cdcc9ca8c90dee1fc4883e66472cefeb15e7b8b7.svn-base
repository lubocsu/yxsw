<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统消息主页</title>
<!-- 框架分离模式start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path}/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<!-- 表单start -->
<script src="${path}/libs/js/form/form.js" type="text/javascript"></script>
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>
<!-- 日期选择框start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/libs/js/tree/ztree/ztree.css"></link>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<style>
.l-layout-center{
    border:none!important;
}
.l-layout-left{
    border-bottom:none!important;
}
.l-layout-drophandle-left{
    width: 10px;
}
</style>
</head>
<body>
	<div id="layout1">
		<div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="${path}/message/getGridMessageData" id="queryForm" method="post">
			<input type="hidden" id="parentMessageId" name="parentMessageId" value=""/>
			<table>
				<tr>
					<td><input type="hidden" id="initStartTime" value="${initStartTime }" /></td>
					<td><input type="hidden" id="initEndTime" value="${initEndTime }" /></td>
					<td>发送时间：</td>
					<td><input type="text" class="date" name="startTime" id="startTime"  dateFmt="yyyy-MM-dd" style="width:140px" readonly="readonly" value="${initStartTime}"   onFocus="WdatePicker({isShowClear:false})" /></td>
					<td> 至 </td>
					<td><input type="text" class="date" name="endTime" id="endTime" dateFmt="yyyy-MM-dd" style="width:140px" readonly="readonly" value="${initEndTime}"   onFocus="WdatePicker({isShowClear:false})" /></td>
					<td>消息状态：</td>
					<td><select labelField="data1" valueField="code" name="status" url="${path}/common/getSupportData" params='{"dataId":"sysDict","condition":"parentnodeid=S000013"}'></select></td>
					<td>发送人：</td>
					<td><input type="text" id="senderName" name="senderName"  /></td>
					<td><button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button></td>
					<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置查询</span></button></td>
				</tr>
			</table>
			</form>
			</div>
			<div class="padding_right5">
				<div id="dataBasic"></div>
			</div>
        </div>
	</div>
<script type="text/javascript">
	
	var grid = null;
	
	//框架初始化函数
	function initComplete(){
		//初始化grid组件
		initGrid();
	}
	
	//初始化grid组件
	function initGrid(){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '消息标题', name: 'title', align: 'left', width: "20%"},
					    { display: '消息内容', name: 'content', align: 'left', width: "40%", showTitle:true },
					    { display: '消息状态', name: 'status', align: 'center', width: "10%" ,type:"status"},
					    { display: '发送人', name: 'username', align: 'center', width: "10%"},
					    { display: '发送时间', name: 'sendtime', align: 'center', width: "20%",type: "sendtime"},
					    { display: '操作', isAllowHide: false, align: 'center', width:80,
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.messageid + '\')"></span></div>';
	   		                 	     return html;
			                 }
					    }
		    		],
		    url: '${path}/message/getGridMessageData', sortName: 'm.status,sendtime',sortOrder:'desc',rownumbers:true,checkbox:true,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:10, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '标记已读', click: markUnit, iconClass: 'icon_add'},
		           	        {line  : true },
		           	     	{text: '删除消息', click: deleteUnit, iconClass: 'icon_delete'},
		           	        {line  : true }
		           	        /*
							{text: '导出当前页', iconClass: 'icon_export'},
							{ line : true },
							{text: '导出全部',  iconClass: 'icon_export'}
							*/
						   ]
		   }
		});
	}
	
	//查询
    function searchHandler(){
    	var query = $("#queryForm").formToArray(); 
		grid.setOptions({ params : query});
		//页号重置为1
		grid.setNewPage(1);
		//刷新表格数据 
		grid.loadData();
    }
	
	//重置查询
	function resetSearch(){
		$("#queryForm select").resetValue();
		$("#senderName").val("");
		$("#startTime").val($("#initStartTime").val());
		$("#endTime").val($("#initEndTime").val());
		searchHandler();
		
	}
	
	//查看
	function onView(rowid){
		$.ajaxSettings.async = false;
		Dialog.open({
			URL:"${path}/message/findMessageAndChangeStatus?messageId=" + rowid,
			Title:"查看系统消息",Width:400,Height:300});
		//刷新表格数据 
		grid.loadData();
	}
	
	//批量标记
	function markUnit(){
		var rows = grid.getSelectedRows();
	    var rowsLength = rows.length;
	    var messageIds = "";
	    if(rowsLength == 0) {
	        top.Dialog.alert("请选中要标记的消息!");
	        return;
	    }
	    for(var i = 0 ; i < rowsLength ; i++ ){
	    	messageIds += ( rows[i].messageid + "," );
	    }
	    top.Dialog.confirm("确定要标记吗？",function(){
	            $.post("${path}/message/updateMessage",
	                    //获取所有选中行
	                    { "messageIds" : messageIds.substring(0,messageIds.length - 1)},
	                    function(result){
	                        handleResult(result.status);
	                    },
	                    "json");
	        });
	}
	
	//批量删除
	function deleteUnit() {

	    var rows = grid.getSelectedRows();
	    var rowsLength = rows.length;
	    var messageIds = "";
	    if(rowsLength == 0) {
	        top.Dialog.alert("请选中要删除的消息!");
	        return;
	    }
	    for(var i = 0 ; i < rowsLength ; i++ ){
	    	messageIds += ( rows[i].messageid + "," );
	    }
	    top.Dialog.confirm("确定要删除吗？",function(){
	            $.post("${path}/message/deleteMessage",
	                    //获取所有选中行
	                    { "messageIds" : messageIds.substring(0,messageIds.length - 1)},
	                    function(result){
	                        handleResult(result.status);
	                    },
	                    "json");
	        });
	}
	
	//标记、删除后的提示
	function handleResult(result){
			Dialog.alert(result,null,null,null,1);
			grid.loadData();
	}
	
	//发送(新增)消息
	function addUnit(){
		var diag = new Dialog();
		diag.Title = "发送系统消息";
		diag.URL = "${path}/message/toAddMessage";
		diag.ShowButtonRow=true;
		diag.Height = 180;
		diag.Width = 430;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
	}
	
	//Grid时间row格式化
    jQuery.quiDefaults.Grid.formatters['sendtime'] = function (value, column) {
		var newDate = new Date();
		newDate.setTime(value);
		return newDate.toLocaleString();
	}
	
	//Grid类型row格式化
	jQuery.quiDefaults.Grid.formatters['status'] = function (value, column) {
		if(value == '1'){
			value = '<font color="blue">未读</font>';
		}else if(value == '2'){
			value = '已读';
		}else{
			value = '删除';
		}
		return value;
	}

</script>
</body>
</html>