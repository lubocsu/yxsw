<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色选择用户</title>
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

<!-- 树形双选器start -->

<script type="text/javascript" src="${path }/libs/js/form/listerTree.js"></script>

<!-- 树形双选器end -->
</head>
<body>
	<div align="center" style="margin-top:10px;font-size: 22px; font-weight: bold;">[<span style="color: #daa047;">${roleName}</span>]的用户</div>
	<div style="border-bottom:1px solid #80c0e7; margin-top:13px;margin-bottom:5px;"></div>
	<div class="listerTree" id="listerTree" data='${treeMap }' style="width: 400px;"></div>
<script type="text/javascript">
//表单重置
	function resetTree(){
		$("#listerTree").removeData("data");
		$("#listerTree").resetValue(); 
	}
	function addRoleViewUser(){
		var userIds=$("#listerTree").attr("relValue");
		if(userIds!=null&&userIds.length>0){
			$.ajax({
				type : "post",
				url : "${path }/role/addRoleViewUser?roleId=${roleId}&userIds="+userIds,
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
			parent.Dialog.alert("已选列表不能为空！");
		}
	}
	function getNodeId(nodes,ids){
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].oldParentId=="1"){
				if(ids==""){
					ids=nodes[i].id;
				}else{
					ids+=","+nodes[i].id;
				}
			}else{
				 if (nodes[i].isParent){
					 ids=getNodeId(nodes[i].children,ids);
				 }
			}
		}
		return ids;
	}
	
</script> 
</body>
</html>