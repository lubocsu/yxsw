<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备厂商管理</title>
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
        	<div class="padding5">
			<form action="#" id="queryForm" method="post">
				<table align="center" style="width:100%;">
					<tr>
						<td style="width:15%;text-align:right">设施名称：</td>
						<td style="width:20%;text-align:left"><input type="text" id="name" name="name"  value=""  /></td>
						<td style="width:15%;text-align:right">设施编号：</td>
						<td style="width:20%;text-align:left"><input type="text" id="code" name="code"  value="" /></td>
						<td >
							<button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button>
							<button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
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
			        	{ display: '设施名称', name: 'name',align: 'left', width: "20%",showTitle:true},
					    { display: '设施编号', name: 'code', align: 'center', width: "20%"},
					    { display: '功能说明', name: 'function', align: 'center', width: "20%", showTitle:true },
					    { display: '工艺段', name: 'technics_name', align: 'center', width: "10%"},
					    { display: '巡检点', name: 'xjd_item_name', align: 'center', width: "10%"},
					    { display: '层级关系', name: 'layer', align: 'left', width: "20%", showTitle:true },
		    		],
		    url: '${path}/freq/getAddFreqSsList?freqId=${freqId }',rownumbers:true,checkbox:true,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
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
		$("#queryForm")[0].reset();
		$("#queryForm input").val("");
		searchHandler();
	}
	
	//新增设施
	function addFa(refresh) {

	    var rows = grid.getSelectedRows();
	    var data = {};
	    data.freqId = "${freqId }";
	    data.ssList = JSON.stringify(rows);
	    if(rows.length == 0) {
	        Dialog.alert("请选中要关联的设施!");
	        return;
	    }
	    $.ajax({
            cache: true,
            type: "POST",
            url:'${path}/freq/saveFreqRelateSs',
            data:data,
            async: false,
            error: function(request) {
            	Dialog.alert("提交失败");
            },
            success: function(responseText, statusText, xhr, $form){
            	if(responseText.flag){
            		Dialog.alert(responseText.message, function(){
            			refresh();
            		});
            	}else{
            		Dialog.alert(responseText.message);
            	}
            }
        });
	}
	
</script>
</body>
</html>