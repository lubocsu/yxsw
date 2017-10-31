<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看用户信息</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">
	jQuery(function(){
		jQuery(function(){
			jQuery(".valshow").each(function(i){
				jQuery(this).attr("title",jQuery(this).text());
				jQuery(this).addClass("textSlice");
			});
		});
	});
</script>
<style type="text/css">
/* 表格固定宽度显示 */
 	.tableStyle { table-layout:fixed; width: 95%; }
	.tableStyle td {overflow:hidden; white-space:nowrap; margin: 0; padding: 5px;}
</style>
</head>
<body>
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
	<table class="tableStyle" formMode="transparent" footer="normal">
		<tr>
			<td width="30%">父级机构：</td>
			<td width="70%"><span class="valshow">${parentName }</span></td>
		</tr>
		<tr>
			<td>机构代码：</td>
			<td><span class="valshow">${sysOrg.orgCode }</span></td>
		</tr>
		<tr>
			<td>机构名称：</td>
			<td><span class="valshow">${sysOrg.orgName }</span></td>
		</tr>
		<tr>
			<td>机构简称：</td>
			<td><span class="valshow">${sysOrg.orgShrotName }</span></td>
		</tr>
		<%-- <tr>
			<td>机构拼音码：</td>
			<td><span class="valshow">${sysOrg.shortSpelling }</span></td>
		</tr> --%>
		<tr>
			<td>机构类型：</td>
			<td><span class="valshow">${orgTypeName }</span></td>
		</tr>
		
		<tr>
			<td>地　　址：</td>
			<td><span class="valshow">${sysOrg.address }</span></td>
		</tr>
		<tr>
			<td>邮　　编：</td>
			<td><span class="valshow">${sysOrg.zip }</span></td>
		</tr>
		<tr>
			<td>负责人：</td>
			<td><span class="valshow">${principalName }</span></td>
		</tr>
		<tr>
			<td>联系电话：</td>
			<td><span class="valshow">${sysOrg.tel }</span></td>
		</tr>
		<tr>
			<td>排序号：</td>
			<td><span class="valshow">${sysOrg.orderNo }</span></td>
		</tr>
		<tr>
			<td>是否有效：</td>
			<td>
				<c:if test="${sysOrg.enabled eq '1'}"> <span class="valshow">是</span> </c:if>
				<c:if test="${sysOrg.enabled eq '0'}"> <span class="valshow">否</span> </c:if>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><span class="valshow">${sysOrg.description }</span></td>
		</tr>
	</table>
	</div>
</body>
</html>