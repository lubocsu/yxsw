<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>数据权限配置页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var path = '${path }';
</script>
<!--框架必需start-->
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<!--框架必需end-->

<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--分离模式框架必需end-->

<!-- 树组件start -->
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<!-- 树组件end -->
</head>
<body>
	<input type="hidden" id="relateId" value="${userId}">
	<div align="center" style="margin-top:10px;font-size: 22px; font-weight: bold;">用户[<span style="color: #daa047;">${userName}</span>]的数据权限</div>
	<div style="border-bottom:1px solid #80c0e7; margin-top:13px;margin-bottom:5px;"></div>
	<div class="orgTreeContainer">
        <ul id="permissionTree" class="ztree" style="position:absolute; left:0; top:50px; overflow-y:auto; right:0; bottom:0;"></ul>
    </div>
    <div style="position: absolute;bottom: 0px;right: 10px;color: #666;">请通过右键允许和禁止</div>
    <!-- 右键菜单项 -->
    <div class="b-m-mpanel" style="width: 150px;visibility:hidden;" id="rMenu">
	    <div class="b-m-item" id="m_add" onclick="addPermission();">
	        <img align="middle" src="${path }/libs/images/icons/add.png"/><span>允许</span>
	    </div>
	    <div class="b-m-item" id="m_add_select" onclick="addSelectPermissions();">
	        <img align="middle" src="${path }/libs/images/icons/add.png"/><span>允许选中项</span>
	    </div>
	    <div class="b-m-item" id="m_del" onclick="delPermission();">
	        <img align="middle" src="${path }/libs/images/icons/close.png"/><span>禁止</span>
	    </div>
	    <div class="b-m-item" id="m_del_select" onclick="delSelectPermissions();">
	        <img align="middle" src="${path }/libs/images/icons/close.png"/><span>禁止选中项</span>
	    </div>
	    <div class="b-m-item" id="m_cls_select" onclick="clsSelectPermissions();">
	        <img align="middle" src="${path }/libs/images/icons/close.png"/><span>清除选中项</span>
	    </div>
    </div>

</div>
<script type="text/javascript" src="${path }/js/permissiondata/userPermissionDataTree.js"></script>
<script type="text/javascript">
var zTree=null;
//定义树节点初始数据
var nodes =[];
//树属性配置
var selectionSetting = {
		view: {
			dblClickExpand: false,
			// 自定义字体支持
			fontCss: function(treeId, node){
				return node.font ? node.font : {};
			}
		},
		check: {
          enable: true,
          chkboxType : { "Y": "s", "N": "s" }
      },
		callback: {
          //响应右键
          onRightClick: OnRightClick
      }
};
//初始化函数
function initComplete(){
	//初始化tree
	nodes = nodes.concat(JSON.parse('${treeMap}'));
	zTree = $.fn.zTree.init($("#permissionTree"), selectionSetting, nodes);
	
	//鼠标移入右键菜单效果
  	$("#rMenu >div").hover(function(){
      	$(this).addClass("b-m-ifocus");
  	},function(){
      	$(this).removeClass("b-m-ifocus");
  	});
}
</script>
</body>
</html>