<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>指标项明细</title>
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
				<td width="15%">指标项编码	：</td>
				<td >
					<span class="valshow">${cxzb_id.cxzb_code}</span>
				</td>
				
			</tr>
			
			<tr> 
			  <td>指标项名称：</td>
			  <td>
			  	  <span class="valshow">${cxzb_id.cxzb_name}</span>
			  </td>
		  </tr>
			<tr>
				
				<td> 计量单位:</td>
				<td>
				    <span class="valshow">
				    <c:if test="${cxzb_id.cxzb_unit  eq '1' }">kg</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '2' }">m3</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '3' }">kw.h</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '4' }">mg/l</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '5' }">m</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '6' }">m2</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '7' }">%</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '8' }">￥</c:if>
				    <c:if test="${cxzb_id.cxzb_unit  eq '9' }">%</c:if> 
				    </span>
				</td>
			</tr>
				<tr>
				<td> 输入类型:</td>
				<td>
				    <span class="valshow">
				    <c:if test="${cxzb_id.cxzb_tblx eq '1' }">普通文本</c:if>
				    <c:if test="${cxzb_id.cxzb_tblx eq '2' }">数字</c:if>
				    </span>
				</td>
			</tr>
		    <tr>
			    <td>说明：</td>
				<td >	
				<textarea style="width:520px; height:300px"  disabled="disabled">${cxzb_id.cxzb_desc}</textarea>
				</td>
			</tr>
			
		</table>
	<div class="height_15"></div>
	</div>
</body>
</html>