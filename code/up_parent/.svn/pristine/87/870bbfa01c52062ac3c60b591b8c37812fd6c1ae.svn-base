<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>反馈信息明细</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<style type="text/css">
	/* 查看表格样式调整 */
	table {margin-top: 10px;}
	/* 弹出内容边距调整 */
	.contentStyle {margin-right: 25px;}
	/* 修正自动断行问题 */
	.l-grid-tree-content {display: inherit; white-space: nowrap;}
	/* 表格固定宽度显示 */
 	.tableStyle { table-layout:fixed; width: 100%; padding-right: 15px;}
	.tableStyle td {overflow:hidden; white-space:nowrap; margin: 0; padding: 0;}
</style>
<script type="text/javascript">
jQuery(function(){
	jQuery(".valshow").each(function(i){
		jQuery(this).attr("title",jQuery(this).text());
		//文字超长用...代替
		jQuery(this).addClass("textSlice");
	});
});
</script>
</head>
<body>
	<table class="tableStyle" formMode="transparent" style="margin-top: 10px;">
		<tr>
			<td width="80px;">反馈标题：</td>
			<td><span class="valshow" style="width: 235PX;">${feedBack.title }</span></td>
		</tr>
		<tr>
			<td>归属系统：</td>
			<td>
				<span class="valshow" style="width: 235PX;">${feedBack.systemname }</span>
			</td>
		</tr>
		<tr>
			<td>反馈时间：</td>
			<td>
				<span class="valshow"  style="width: 235PX;">${feedBack.createtime }</span>
			</td>
		</tr>
		<tr>
			<td>反馈人员：</td>
			<td>
				<span class="valshow" style="width: 235PX;">${feedBack.username }</span>
			</td>
		</tr>
		<tr>
			<td>反馈内容：</td>
			<td>
					<textarea readonly="readonly" style="width:238px; height:80px;">${feedBack.content }</textarea>
			</td>
		</tr>
	</table>
</body>
</html>