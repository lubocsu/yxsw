<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检任务记录</title>
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
			<form action="${path}/patrollingRecords/getRecordsData" id="queryForm" method="post">
				<table align="center" style="width:100%;">
					<tr>
						<td  style="width:10%;text-align:right">所属单位：</td>
						<td  style="width:15%;text-align:left"><select labelField="value" valueField="key" selWidth="180" id="csOsrg" name="csOsrg" prompt="请选择" selectedValue="" url="${path}/technicsScope/getSupportData" ></select></td>
						<td  style="width:10%;text-align:right">是否超期：</td>
						<td  style="width:15%;text-align:left"><select labelField="value" valueField="key" selWidth="180" name="isOverTime" id="isOverTime" prompt="请选择" selectedValue="${outService }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${overTime }"}'></select></td>
						<td  style="width:10%;text-align:right">任务日期：</td>
						<td  style="width:20%;text-align:left">
							<input type="text" class="date" name="startTime" id="startTime"  value=""  dateFmt="yyyy-MM-dd" style="width:90px" readonly="readonly" value="${startTime}"   onFocus="WdatePicker({isShowClear:false})" />
							至<input type="text" class="date" name="endTime" id="endTime"  value=""  dateFmt="yyyy-MM-dd" style="width:90px" readonly="readonly" value="${endTime}"   onFocus="WdatePicker({isShowClear:false})" />
						</td>
						<td  style="width:15%;text-align:left">
							<button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
						</td>
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
					    { display: '任务日期', name: 'cx_task_date', align: 'center', width: "10%",showTitle:true,type:"time"},
					    { display: '巡检人员', name: 'task_person', align: 'left', width: "15%",showTitle:true},
					    { display: '巡检任务', name: 'cx_task_name', align: 'left', width: "25%",showTitle:true},
					    { display: '要求完成时间', name: 'cx_task_pend_time', align: 'center', width: "15%",showTitle:true,type:"time"},
					    { display: '实际完成时间', name: 'cx_task_aend_time', align: 'center', width: "15%",showTitle:true,type:"time"},
					    { display: '是否超期', name: 'is_over_time', align: 'center', width: 80},
					    { display: '是否正常超期', name: 'sf_zc_over_time', align: 'center', width: 80,showTitle:true},
					    { display: '任务状态', name: 'cx_task_status', align: 'center', width: "10%",showTitle:true},
					    { display: '所属单位', name: 'belong_wsc_name', align: 'center', width: "10%",showTitle:true},
					    { display: '操作', isAllowHide: false, align: 'center', width:80,
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.cx_task_id + '\')"></span>';
	                               	 if("${csOrgType }" == 3 && rowdata.cx_task_status == "已完成" && rowdata.is_over_time == "是" && rowdata.sf_zc_over_time == null){
	                               		html += '<span class="img_edit hand" title="修改" onclick=overTime(\''+ rowdata.cx_task_id +'\')></span>';
	                               	 }
	                               	html += '</div>';
	                               	
	                               	 return html;
			                 }
					    }
		    		],
		    url: '${path}/patrollingRecords/getRecordsData',rownumbers:true,checkbox:false,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
						   ]
		   }
		});
	}
	
	//页面弹框直接填写是否超期
	function overTime(cxTaskId){
		
		var diag = new Dialog();
		diag.Title = "是否正常超期";
		diag.URL = "${path }/patrollingRecords/toIsZcOverTime?cxTaskId="+cxTaskId;
		diag.Width = 400;
		diag.Height = 200;
		//设置保存按钮文字（默认是确定）
		diag.OkButtonText = "确定";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
		
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
		$("#startTime").val("");
		$("#endTime").val("");
		$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		searchHandler();
		
	}
	
	//查看
	function onView(cx_task_id){
		var url="${path}/patrollingRecords/toViewCxTask?cxTaskId="+cx_task_id;
		window.open(url);
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
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