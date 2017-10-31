<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看菜单</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">
	jQuery(function(){
		jQuery(".valshow").each(function(i){
			jQuery(this).attr("title",jQuery(this).text());
			jQuery(this).addClass("textSlice");
		});
	});
</script>
<style type="text/css">
/* 表格固定宽度显示 */
 	.tableStyle { table-layout:fixed; width: 98%; }
	.tableStyle td {overflow:hidden; white-space:nowrap; margin: 0; padding: 5px;}
</style>
</head>
<body>
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">菜单名称：</td>
				<td >
					<span class="valshow">${menu.menuName }</span>
				</td>
				<td width="15%">父级菜单：</td>
				<td>
				    <span class="valshow">${menu.parentMenuId }</span>
				</td>
			</tr>
			<tr>
				<td valign="top">模块链接地址：</td>
				<td colspan="3" style="text-align: left;">
					<span class="valshow">${menu.linkURL }</span>
				</td>
			</tr>
			<tr>
			  <td>菜单功能类型：</td>
			  <td>
			      <span class="valshow">
			  	      <c:if test="${menu.functionType eq '1' }">有子菜单</c:if>
			  	      <c:if test="${menu.functionType eq '2' }">主窗口打开</c:if>
			  	      <c:if test="${menu.functionType eq '3' }">新窗口打开</c:if>
			  	  </span>
			  </td>
			  <td>参　　数：</td>
			  <td>
			  	  <span class="valshow">${menu.param }</span>
			  </td>
		  </tr>
			<tr>
				<td>菜单类型：</td>
				<td>
				    <span class="valshow">
						<c:if test="${menu.menuType eq '1' }">B/S</c:if>
						<c:if test="${menu.menuType eq '2' }">C/S</c:if>
						<c:if test="${menu.menuType eq '4' }">报表菜单</c:if>
					</span>
				</td>
				<td>图标路径：</td>
				<td>
				    <span class="valshow">${menu.imagePath }</span>
				</td>
			</tr>
			<tr>
			  <td>菜单权限：</td>
			  <td>
			  	  <span class="valshow">${menu.permissionNo }</span>
			  </td>
			  <td>是否有效：</td>
			  <td>
			    <span class="valshow">
				  	<c:if test="${menu.enabled eq '1'}">是</c:if>
				  	<c:if test="${menu.enabled eq '0'}">否</c:if>
			  	</span>
		  </tr>
			<tr>
				<td >所属系统：</td>
				<td >
					<span class="valshow">${menu.systemCode }</span>
				</td>
				<td>排序号：</td>
				<td>
					<span class="valshow">${menu.orderNo }</span>
				</td>
			</tr>
			<tr>
				<td valign="top">描述说明：</td>
				<td colspan="3" style="text-align: left;">
					<span class="valshow">${menu.description }</span>
				</td>
			</tr>
		</table>
	<div class="height_15"></div>
	</div>
</body>
</html>