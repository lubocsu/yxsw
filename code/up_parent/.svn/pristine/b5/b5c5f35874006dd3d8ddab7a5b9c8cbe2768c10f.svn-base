<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看权限</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<style type="text/css">
	/* 查看表格样式调整 */
	table {margin-top: 10px;}
	/* 弹出内容边距调整 */
	.contentStyle {margin-right: 25px;}
</style>
</head>
<body>
<table class="tableStyle"  formMode="transparent">
		<tr>
			<td>归属系统：</td>
			<td><span class="valshow">${permissionView.systemname }</span></td>
			<td width="20%">权限名称：</td>
			<td width="30%"><span class="valshow">${permissionView.permissionname }</span></td>
		</tr>
		<tr>
			<td width="20%">权限编码：</td>
			<td><span class="valshow">${permissionView.permissionno }</span></td>
			<td>上级权限：</td>
			<td><span class="valshow">${permissionView.pname }</span></td>
		</tr>
		<tr>
			<td>对应菜单：</td>
			<td><span class="valshow">${permissionView.menuname }</span></td>
			<td>排序号：</td>
			<td><span class="valshow">${permissionView.orderno }</span></td>
		</tr>
		<tr>
			<td>按钮权限：</td>
			<td colspan="3">
			<!-- <div class="selectTree" selWidth="251"  id="btnPermission" name="btnPermission" multiMode="true"  allSelectable="true" data='${btnPermission }'></div> -->
				<div id="hobby" class="checkboxRender" >
					<c:forEach items="${btnPermissions }" var="item">
						<input type="checkbox" onclick="return false" name="btnPermissionItem" value="${item.code }"/><label for="checkbox-1" class="hand">${item.data1 }</label>
					</c:forEach>
                </div>
			</td>
		</tr>
		<tr>
			<td>是否有效：</td>
			<td><span class="valshow">${permissionView.enabled }</span></td>
			<td>权限类型：</td>
			<td><span class="valshow">${permissionView.permissiontype }</span></td>
		</tr>
	</table>
	<script type="text/javascript">
		var childPermissionNo="${permissionView.btnpermission}".split(",");
		$("input:checkbox[name=btnPermissionItem]").each(function(){
			for(var i=0;i<childPermissionNo.length;i++){
				if($(this).val()==childPermissionNo[i]){
					$(this).attr("checked",true);
				}
			}	
		});
	</script>
</body>
</html>