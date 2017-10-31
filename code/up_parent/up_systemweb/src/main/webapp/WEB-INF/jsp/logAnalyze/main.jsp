<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志分析主页</title>
<!-- 框架分离模式start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path}/libs/js/nav/layout.js"></script>
<!-- fusionchart Start -->
<script type="text/javascript" src="${path}/js/fusionchart/FusionCharts.js"></script>
<!--树组件start -->
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<link href="${path }/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>

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
/* 修正自动断行问题 */
.l-grid-row-cell-inner {white-space: nowrap;}

</style>
<script type="text/javascript">

var Height = null;
var Width = null;
var loginHeight = null;
var loginWidth = null;


jQuery(function() {
	lin_height();
	initDiv();
	initTree();
	
});

function initDiv(){
	Height = $("#pie").height();
	Width = $("#pie").width();
	loginHeight = $("#line").height();
	loginWidth = $("#line").width();
}

var treeObj = null;
//树属性配置
var selectionSetting = {
		view: {
			fontCss: getFont,
			dblClickExpand: true
		},
		callback:{
			onClick:zTreeSelect
		}
};
function getFont(treeId, node) {
	return node.font ? node.font : {};
}

function initTree(){
	
	$.ajax({
		type : 'POST',
		url : '${path}/logAnalyze/getSystemName',
		dataType : 'json',
		data : {
		},
		success : function(result){
			treeObj = $.fn.zTree.init(jQuery("#tree"), selectionSetting, result);
			var nodeArray =  treeObj.transformToArray(treeObj.getNodes());
			var firstNode = "";
			if(nodeArray.length > 0){
					treeObj.selectNode(nodeArray[0],false);
					firstNode = nodeArray[0];
			}
			initDatas(firstNode.id);
		},
		error : function(a){
			alert("系统使用情况目录获取失败");
		}
	});
	
}


function initDatas(treeNode){
	
	if(FusionCharts("myChartId1")){
		FusionCharts("myChartId1").dispose();
	}
	if(FusionCharts("myChartId2")){
		FusionCharts("myChartId2").dispose();
	}
	if(FusionCharts("myChartId3")){
		FusionCharts("myChartId3").dispose();
	}
	
	$.post("${path}/logAnalyze/initCharts",{"treeNode":treeNode},function(data){
		
		var columnChart = new FusionCharts("Column3D","myChartId1",Width,Height);
		columnChart.setJSONData(data.columnChart);
		columnChart.render("column");
		
		var pieChart = new FusionCharts("Pie3D","myChartId2",Width,Height);
		pieChart.setJSONData(data.pieChart);
		pieChart.render("pie");
		
		var lineChart = new FusionCharts("Line","myChartId3",loginWidth,loginHeight);
		lineChart.setJSONData(data.lineChart);
		lineChart.render("line");
		
	});

	$.ajaxSettings.async = false;
	$.post("${path}/logAnalyze/initDatas",{"treeNode":treeNode},function(data){
		$("#functionName").html(treeNode);
		$("#loginCount").html(data.loginCount);
		$("#maxLoginTime").html(data.maxCountTime.logtime);
		$("#maxLoginCount").html(data.maxCountTime.logincount);
		$("#dwellTime").html(data.dewllTime);
		$("#functionNameAndCount").html(data.functionNameCount);
	});
	
}

//点击树节点刷选对应的表格数据 
function zTreeSelect(event,treeId,treeNode) {
	//取得点击节点的值
	var treeNodeId = treeNode.id;
	if(treeNodeId.indexOf("管网") == 0){
		initDatas(treeNodeId);
	}else{
		//取得点击节点的父节点
		var parentNode = treeNode.getParentNode();
		//取得父节点的ID的值
		var parentNodeId = parentNode.id;
		updateDatas(parentNodeId,treeNodeId);
	}
}

function updateDatas(parentNodeId,treeNodeId){
	$.ajaxSettings.async = false;
	$.post("${path}/logAnalyze/updateDwellTimeAndCount",{"systemName":parentNodeId,"functionName":treeNodeId},function(data){
		$("#functionName").html(data.functionname);
		$("#dwellTime").html(data.hours);
		$("#functionNameAndCount").html(data.count);
	});
}


function lin_height(){
	var height = jQuery(".zi-top-content-right .zi-con1").height();
	jQuery(".zi-con1").css("line-height",height+'px');	
	var height2 = jQuery(".zi-btm-right .zi-btm-rightm").height();
	jQuery(".zi-btm-rightm").css("line-height",height2+'px');
}

window.onresize = function(){
	lin_height();
}

</script>
<style type="text/css">

	*{ margin:0; padding:0;}
	.zi-max{ position:absolute; left:0; top:0; right:0; bottom:0;}
	.zi-top{ position:absolute; left:15px; top:3%; right:15px; height:30%; border:1px solid #a8c3d8;}
	.zi-top-title{ background:url(${path}/image/ti-bg.png) repeat-x; height:27px; line-height:27px; float:left; width:100%; border-bottom:1px solid #a2c5db; font-size:14px; text-indent:8px;}
	.zi-top-content{ background:#e1effa; position:absolute; left:0; top:28px; right:0; bottom:0; overflow:auto;}
	.zi-top-content-left{ position:absolute; left:1%; top:10px; bottom:10px; width:70%; background:#fff; border:1px solid #d1d8de;text-align: center;}
	.zi-top-content-right{ position:absolute; top:10px; right:1%; bottom:10px; width:26%; overflow:hidden;}
	.zi-con1{ position:absolute; left:0; top:0; right:0; height:43%; background:#fff; border:1px solid #d1d8de; text-align:center;font-size: 16px;}
	.zi-con2{ position:absolute; left:0; bottom:0; right:0; height:43%; background:#fff; border:1px solid #d1d8de; text-align:center;font-size: 16px; line-height:2em; padding-top:10px;}
	.zi-top-content-right strong{color:#c77347;}
	.zi-btm-right strong{color:#c77347;}
	.zi-btm{ position:absolute; left:15px; right:15px; bottom:2%; height:62%; border:1px solid #a8c3d8;}
	.zi-btm-title{ background:url(${path}/image/ti-bg.png) repeat-x; height:27px; line-height:27px; float:left; width:100%; border-bottom:1px solid #a2c5db; font-size:14px; text-indent:8px;}
	.zi-btm-content{ background:#e1effa; position:absolute; left:0; top:28px; right:0; bottom:0; overflow:auto;}
	.zi-btm-left{ position:absolute; left:1%; top:10px; width:20%; bottom:10px; background:#fff; border:1px solid #d1d8de;}
	.zi-btm-center{ position:absolute; left:22%; top:10px; width:56%; bottom:10px;}
	.zi-btm-right{ position:absolute; left:79%; top:10px; width:20%; bottom:10px;}
	.zi-btm-left-content{ background:#fff; position:absolute; left:0; top:28px; right:0; bottom:0; overflow:hidden;}
	.zi-btm-center-top{ position:absolute; left:0; top:0; right:0; height:48%;overflow: hidden; border:1px solid #d1d8de;}
	.zi-btm-center-btm{ position:absolute; left:0; bottom:0; right:0; height:48%; border:1px solid #d1d8de;}
	.zi-btm-right1{ position:absolute; left:0; top:0; right:0; height:31%; background:#fff; border:1px solid #d1d8de;text-align: center;font-size: 20px;}
	.zi-btm-right2{ position:absolute; left:0; top:34.5%; right:0; height:31%; background:#fff; border:1px solid #d1d8de;text-align: center;font-size: 20px;}
	.zi-btm-right3{ position:absolute; left:0; bottom:0; right:0; height:31%; background:#fff; border:1px solid #d1d8de;text-align: center;font-size: 20px;}
	.blue{background: #bcd4ec;}
</style>
</head>
<body>
<div class="zi-max">
	<div class="zi-top">
    	<div class="zi-top-title">人数分析</div>
        <div class="zi-top-content">
        	<div class="zi-top-content-left" id="line"></div>
            <div class="zi-top-content-right">
            	<div class="zi-con1">登录总次数：<strong><span id="loginCount"></span></strong></div>
                <div class="zi-con2">最多登录日期：<strong><span id="maxLoginTime"></span></strong></br>登录次数:<strong><span id="maxLoginCount"></span></strong></div>
            </div>
        </div>
    </div>
    <div class="zi-btm">
    	<div class="zi-btm-title">系统点分析</div>
        <div class="zi-btm-content">
        	<div class="zi-btm-left">
            	<div class="zi-top-title">系统目录</div>
                <div class="zi-btm-left-content">
                	<input type="hidden" id="parentSystemId" name="parentSystemId" />
                	<input type="hidden" id="systemCode" name="systemCode"/>
                	<ul class="ztree" id="tree">
                	</ul>
                </div>
            </div>
            <div class="zi-btm-center">
            	<div class="zi-btm-center-top">
                	<div class="zi-top-title">停留时间总计</div>
                    <div class="zi-btm-center-top-content zi-btm-left-content" id="pie"></div>
                </div>
                <div class="zi-btm-center-btm">
                	<div class="zi-top-title">使用次数</div>
                    <div class="zi-btm-center-top-content zi-btm-left-content" id="column"></div>
                </div>	
            </div>
            <div class="zi-btm-right">
            	<div class="zi-btm-rightm zi-btm-right1"><span id="functionName"></span></div>
                <div class="zi-btm-rightm zi-btm-right2">停留时间：<strong><span id="dwellTime"></span></strong></div>
                <div class="zi-btm-rightm zi-btm-right3">使用次数：<strong><span id="functionNameAndCount"></span></strong></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>