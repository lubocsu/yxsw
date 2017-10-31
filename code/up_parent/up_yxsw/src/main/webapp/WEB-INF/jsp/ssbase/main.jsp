<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设施档案管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/system/common/header_splitmode.jsp" %>
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--布局控件end-->
<script type="text/javascript" src="${path }/js/ssbase/main.js"></script>


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

/* iframe{border: red 1px solid; */
/*     margin-bottom: 100px;} */
</style>
</head>
<body>
	<div id="layout1">
        <div id="leftCon" position="left" panelTitle="设施清单">
        	<c:if test="${!onlyQuery }">
        		<div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
			    <div class="center">
				    <div class="left">
					    <div class="right">
					        <div class="padding_top3 padding_left10">
						        <div class="box_tool_line"></div>
						        <a style="color:#2C4D79" href="javascript:;" onclick="SSBaseInfo.add();"><span class="icon_add">新增</span></a>
						        <div class="box_tool_line"></div>
						        <a style="color:#2C4D79" href="javascript:;"onclick="SSBaseInfo.modify();"><span class="icon_edit">修改</span></a>
						        <div class="box_tool_line"></div>
						        <a style="color:#2C4D79" href="javascript:;"onclick="SSBaseInfo.del();"><span class="icon_delete">删除</span></a>
					    	</div>
				    	</div>      
			    	</div>  
			    </div>
			    <div class="clear"></div>
			</div>  
        	</c:if>
        	<div class="searchBox" style="padding-left: 5px;padding-top: 1px;">
				<input type="text" style="width:85%;" watermark="输入关键字" id="searchTree"/>
				<div style="width: 11%;padding-top: 4px;float: right;"><span class="img_find hand" title="点击查找节点" keepdefaultstyle="true" onclick="searchTree()"></span></div>
			</div>
        	<div class="SSTreeContainer" style="padding: 5px;">
        		<ul id="tree" class="ztree"></ul>
        	</div>
        </div>
        <div id="centerCon" position="center">
			<div class="padding_right5" style="height: 100%;">
				<iframe  style="width: 100% ;height: 100%;" frameborder="0"  id="ss-detail-iframe"></iframe>
			</div>
        </div>
    </div> 
	
	
<script type="text/javascript">
	var path = "${path}";
	var DELFLAG = '${DELFLAG}'; // 是否停用标识
	
	//初始化函数
	function initComplete(){
		//当提交表单刷新本页面时关闭弹窗
		Dialog.close();
		//初始化path
		SSBaseInfo.config({
			path:"${path}",
		});
		$("#layout1").layout({ leftWidth: 230,onEndResize:function(){}}); 
	}
	//处理高度自适应，每次浏览器尺寸变化时触发
	function customHeightSet(contentHeight) {
		$(".cusBoxContent").height(contentHeight - 55);
		$(".SSTreeContainer").height(contentHeight - 30);
	}
	
	function refreshNewNode(parentId){
		SSBaseInfo.refreshNewNode(parentId);
	}
</script>	
</body>
</html>