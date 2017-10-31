<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看系统消息</title>
<!-- 主框架必须start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript">

jQuery(function(){
	jQuery(".valshow").each(function(i){
		jQuery(this).attr("title",jQuery(this).text());
		//文字超长用...代替
		jQuery(this).addClass("textSlice");
	});
});
</script>
<style type="text/css">
	.tableStyle { table-layout:fixed; width: 383px;}
	.tableStyle td {overflow:hidden; white-space:nowrap; margin: 0; padding: 0;}
	textarea {resize:none;}
</style>
</head>
<body>
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
	<table class="tableStyle" formMode="transparent" footer="normal">
		<tr>
			<td width="30%">消息标题：</td>
			<td width="70%"><span class="valshow">${message.title }</span></td>
		</tr>
		<tr>
			<td width="10%">消息内容：</td>
			<td width="90%"><textarea class="textarea" keepDefaultStyle="true" style="width: 262px;height: 130px;" readonly="readonly">${message.content }</textarea></td>
		</tr>
		<tr>
			<td>发送人：</td>
			<td><span class="valshow">${message.username }</span></td>
		</tr>
		<tr>
			<td>消息状态：</td>
			<td>
			<c:if test="${message.status eq '1' }"><span class="valshow">未读</span></c:if>
			<c:if test="${message.status eq '2' }"><span class="valshow">已读</span></c:if>
			<c:if test="${message.status eq '3' }"><span class="valshow">删除</span></c:if>
			</td>
		</tr>
		<tr>
			<td>发送时间：</td>
			<td><span class="valshow">${message.sendtime }</span></td>
		</tr>
	</table>
	</div>
</body>
</html>