<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>工艺段设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<!--分离模式框架必需end-->
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--布局控件end-->
<script type="text/javascript" src="${path }/js/technicsScope/technicsScope.js"></script>


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
        <div id="leftCon" position="left" panelTitle="工艺段设置">
        	<div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
			    <div class="center">
				    <div class="left">
					    <div class="right">
					        <div class="padding_top5 padding_left10">
						        <div class="box_tool_line"></div>
						        <a href="javascript:;" onclick="TechnicsScope.add();"><span class="icon_add">新增</span></a>
						        <div class="box_tool_line"></div>
						        <a href="javascript:;"onclick="TechnicsScope.modify();"><span class="icon_edit">修改</span></a>
						        <div class="box_tool_line"></div>
						        <a href="javascript:;"onclick="TechnicsScope.del();"><span class="icon_delete">删除</span></a>
					    	</div>
				    	</div>      
			    	</div>  
			    </div>
			</div> 
			<div >
				<select labelField="value" valueField="key" selWidth="220" id="select" name="csOsrg" prompt="请选择" selectedValue="" url="${path}/technicsScope/getSupportData" ></select>
			</div> 
        	<div class="orgTreeContainer" style="padding: 10px 5px;">
        		<ul id="tree" class="ztree"></ul>
        	</div>
        </div>
        <div id="centerCon" position="center">
			<div class="padding_right5" style="height: 100%;">
				<iframe  style="width: 100% ;height: 100%;" frameborder="0"  id="detail-iframe"></iframe>
			</div>
        </div>
    </div> 
	
	
<script type="text/javascript">
	var path = "${path}";
	var csOrg ="";
	var csOrgType = "${csOrgType}";
	//按钮权限
	
	//初始化函数
	function initComplete(){
		//当提交表单刷新本页面时关闭弹窗
		Dialog.close();
		//初始化path
		TechnicsScope.config({
			path:"${path}",
			csOrg:"",
		});
		$("#layout1").layout({ leftWidth: 230,onEndResize:function(){}}); 
	}
	//处理高度自适应，每次浏览器尺寸变化时触发
	function customHeightSet(contentHeight) {
		$(".cusBoxContent").height(contentHeight - 55);
		$(".orgTreeContainer").height(contentHeight - 30);
	}
	
	function refresh(parentId){
		TechnicsScope.refreshTree(parentId);
	}
	
	 document.getElementById('select').onchange=function(){
		 var csOrg = this.options[this.options.selectedIndex].value;
		 TechnicsScope.setTree(csOrg);
		 TechnicsScope.initTree(csOrg);
		 jQuery("#detail-iframe").attr("src","");
	  }
</script>	
</body>
</html>