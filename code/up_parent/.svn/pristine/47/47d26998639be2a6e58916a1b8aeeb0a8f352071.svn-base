<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看数据项</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--树组件start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!-- 组合下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectCustom.js"></script>
<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!-- 表单验证start -->
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
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
	/* 查看表格样式调整 */
	table {margin-top: 10px;}
	/* 弹出内容边距调整 */
	.contentStyle {margin-right: 25px;}
	/* 表格固定宽度显示 */
 	.tableStyle { table-layout:fixed; width: 470px;}
	.tableStyle td {overflow:hidden; white-space:nowrap; margin: 0; padding: 0;}
</style>
</head>
<body>
<table class="tableStyle"  formMode="transparent">
		<col style="width: 80px;" /> 
		<col style="width: 180px;" /> 
		<col style="width: 80px;" />
		<col style="" />
		<tbody>
		<tr>
			<td>数据ID：</td>
			<td colspan="3"><span class="valshow">${dictDataBean.nodeId }</span></td>
		</tr>
		<tr>
			<td>数据代码：</td>
			<td><span class="valshow">${dictDataBean.code }</span></td>
			<td>数据1：</td>
			<td><span class="valshow">${dictDataBean.data1 }</span></td>
		</tr>
		<tr>
			<td>数据2：</td>
			<td><span class="valshow">${dictDataBean.data2 }</span></td>
			<td>数据3：</td>
			<td><span class="valshow">${dictDataBean.data3 }</span></td>
		</tr>
		<tr>
			<td>排序号：</td>
			<td colspan="3"><span class="valshow">${dictDataBean.orderNo }</span></td>
		</tr>
		</tbody>
	</table>
</body>
</html>