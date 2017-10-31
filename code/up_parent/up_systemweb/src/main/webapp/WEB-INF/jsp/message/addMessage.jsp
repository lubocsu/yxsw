<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送系统消息</title>
<!-- 框架分离模式start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!-- 日期控件start -->
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单异步提交start -->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<!-- 树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/libs/js/tree/ztree/ztree.css"></link>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>

</head>
<body>
	<form id="myFormId" action="${path }/message/addMessage" method="post" target="frmright">
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
	<table class="tableStyle" formMode="transparent">
		<tr>
			<td>消息标题：</td>
			<td><input type="text" name="title" id="title" /></td>
		</tr>
		<tr>
			<td>接受人：</td>
			<td><div class="selectTree" name="receiverIds" id="receiverIds" multiMode="true" noGroup="true" url="${path}/message/getUserList"></div></td>
		</tr>
		<tr>
			<td>消息内容：</td>
			<td><textarea id="content" name="content"></textarea></td>
		</tr>
	</table>
	</div>
	</form>
</body>
</html>