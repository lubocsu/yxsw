<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权限配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
 <link rel="stylesheet" type="text/css" id="customSkin" href="${path }/plug/qui/system/layout/skin/style.css"/>
<!--分离模式框架必需end-->

<!-- 树组件start -->

<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>

<link type="text/css" rel="stylesheet" href="${path }/libs/js/tree/ztree/ztree.css"></link>

<!-- 树组件end -->
</head>
<body>
<div align="center" style="margin-top:10px;font-size: 22px; font-weight: bold;">[<span style="color: #daa047;">${roleName}</span>]的权限</div>
	<div style="border-bottom:1px solid #80c0e7; margin-top:13px;margin-bottom:5px;"></div>
	<div class="orgTreeContainer">
        		<ul id="permissionTree" class="ztree" style="position:absolute; left:0; top:50px; overflow-y:auto; right:0; bottom:0;"></ul>
    </div>
<script type="text/javascript">
var treeObj=null;
//定义选中的树节点
var selectTreeNode = null;

//定义树节点初始数据
var nodes =[];

//树属性配置
var selectionSetting = {
		view: {
			dblClickExpand: false
		},
		callback: {
			onClick: zTreeSelect
		},check: {
	        enable: true
	    }




};
//初始化函数
function initComplete(){
	//初始化tree
	nodes = nodes.concat(JSON.parse('${treeMap}'));
	treeObj = $.fn.zTree.init($("#permissionTree"), selectionSetting, nodes);
}

//点击树节点刷选对应的表格数据 
function zTreeSelect(event,treeId,treeNode) {
	if(treeNode.id!='0'){
		var query = null;
		selectTreeNode = treeNode;
		query = {'systemCode':treeNode.id,
				 'roleName':$("#searchInput").val()
					};
		$("#systemCode").val(treeNode.id);
		$("#systemName").val(treeNode.name);
		grid.setOptions({ params : query});
		    //页号重置为1
		    grid.setNewPage(1);
		    //刷新表格数据 
			grid.loadData();
	}
}

	function addRoleViewPermission(){
		var permissionIds="";
		var selectNodes=treeObj.getCheckedNodes(true);
		if(selectNodes.length>0){
			for(var i=0;i<selectNodes.length;i++){
				permissionIds+=","+selectNodes[i].id;
			}
			$.ajax({
				type : "post",
				url : "${path }/role/addRoleViewPermission?roleId=${roleId}&permissionIds="+permissionIds,
				async : false,
				success : function(data) {
					parent.Dialog.alert(data.message,function(){
						parent.Dialog.close();
		            });
				},
				error : function() {
					parent.Dialog.alert(data.message);
				}
			});
		}else{
			parent.Dialog.alert("当前角色无选中权限！");
		}
	}
	
</script> 
</body>
</html>