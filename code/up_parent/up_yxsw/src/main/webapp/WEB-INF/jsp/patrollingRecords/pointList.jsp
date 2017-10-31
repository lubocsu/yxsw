<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检任务巡检点列表</title>
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
			<div class="padding_right5">
				<div id="dataBasicp"></div>
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
		grid = $("#dataBasicp").quiGrid({
			columns:[	
						{ display: '巡检点名称', name: 'xjd_item_name', align: 'left', width: "20%",showTitle:true},
						{ display: '巡检点位置', name: 'xjd_item_address', align: 'left', width: "20%",showTitle:true},
						{ display: 'RFID确认方式', name: 'rfid_confirmed_type', align: 'center', width: "10%"},
						{ display: '完成时间', name: 'opt_time', align: 'center', width: "10%",type:"time"},
						{ display: '是否完成', name: 'have_complete', align: 'center', width: "5%"},
						{ display: '巡检点说明', name: 'xjd_item_desc', align: 'center', width: "30%"},
					    { display: '操作', isAllowHide: false, align: 'center', width:"5%",
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.task_item_id + '\')"></span>';
	                               	html += '</div>';
	                               	
	                               	return html;
			                 }
					    }
		    		],
		    url: '${path}/patrollingRecords/getPointData?cxTaskId=${cxTaskId }',rownumbers:true,checkbox:true,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
						   ]
		   }
		});
	}
	
	//查看
	function onView(task_item_id){
		var url="${path}/patrollingRecords/toViewXjdItem?taskItemId="+task_item_id;
		window.open(url);
	}
	
	//格式化时间
	jQuery.quiDefaults.Grid.formatters['time'] = function (value, column) {
		var time = timeFormatting(value);
		return time;
	}
	//格式化时间
	function timeFormatting(value){
		var time = "";
		if(value != null){
			var year = value.substring(0,4);
			var month =  value.substring(4,6);
			var day = value.substring(6,8);
			var hour = value.substring(8,10);
			var minute = value.substring(10,12);
			var second = value.substring(12,14);
			
			 time = year + "-" + month + "-" + day + " "; 
			 if(null != hour && hour != ""){
				 time = time + hour;
			 }
			 if(null != minute && minute != ""){
				 time = time + ":" + minute;
			 }
			 if(null != second && second != ""){
				 time = time + ":" + second;
			 }
		}
		return time;
	}
</script>
</body>
</html>