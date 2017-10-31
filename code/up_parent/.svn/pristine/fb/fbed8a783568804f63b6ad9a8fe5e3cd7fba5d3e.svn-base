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
			<div class="padding_right5">
				<div id="dataBasicp"></div>
			</div>
        </div>
	</div>
<script type="text/javascript">
	
	var grid = null;
	var diag = new top.Dialog();
	
	//框架初始化函数
	function initComplete(){
		//初始化grid组件
		initGrid();
	}
	
	//初始化grid组件
	function initGrid(){
		grid = $("#dataBasicp").quiGrid({
			columns:[	
					    { display: '设备名称', name: 'sb_name', align: 'left', width: "20%"},
					    { display: '设备类别', name: 'sb_sort', align: 'center', width: "10%"},
					    { display: '设备类型', name: 'sb_type_name', align: 'center', width: "10%"},
					    { display: '国产进口', name: 'gcjk', align: 'center', width: "10%", showTitle:true },
					    { display: '设备型号', name: 'sbxh', align: 'center', width: "10%"},
					    { display: '性能参数', name: 'xncs', align: 'center', width: "10%"},
					    { display: '工艺段', name: 'technics_name', align: 'center', width: "10%"},
					    { display: '巡检点', name: 'xjd_item_name', align: 'center', width: "10%"},
					    { display: '安装位置', name: 'set_address', align: 'center', width: "10%"},
		    		],
		    url: '${path}/freq/getFreqEpList?freqId=${freqId }',rownumbers:true,checkbox:false,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true
		});
	}
</script>
</body>
</html>