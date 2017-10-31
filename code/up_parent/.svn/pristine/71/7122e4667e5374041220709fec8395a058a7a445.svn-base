	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>安全定义明细</title>
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
				<td width="15%">安全提醒标题：</td>
				<td >
					<span class="valshow">${warning.title}</span>
				</td>
				
			</tr>
			
			<tr>
			 
			  <td>是否重要提醒：</td>
			  <td>
			  	  <span class="valshow">
			  	  <c:if test="${warning.is_important eq  '1' }" >是</c:if>
			  	  <c:if test="${warning.is_important eq  '0' }" >否</c:if>
			  	  </span>
			  </td>
		  </tr>
			<tr>
				
				<td>提醒类型:</td>
				<td>
				    <span class="valshow">
				    <c:if test="${warning.tx_type eq '1' }">设备安全</c:if>
				    <c:if test="${warning.tx_type eq '2' }">设施安全</c:if>
				    <c:if test="${warning.tx_type eq '3' }">巡检点安全</c:if>
				    </span>
				</td>
			</tr>
		<tr>
			     <td>内容：</td>
				<td >
					<%-- <span class="valshow">${warning.content}</span> --%>
					
				<textarea style="width:850px; height:300px"  disabled="disabled">${warning.content}</textarea>
				</td>
			</tr>
			<tr>
			    <c:if test="${!empty attachmentList }">
		                    <tr>
		                    	<td>附件：</td>
		                    	<td align="left">
		                    	<!-- 	<div class="imglist"> -->
		                    		<c:forEach items="${attachmentList }" var="item">
		                    	<%-- 	console.log(${item.attachmentPath})
		                    		console.log(${ServerURL}) --%>
		                    			<%-- <a href="${ServerURL}${item.attachmentPath}"  download="${item.oldAttachmentName}.${item.attachmentSuffix}" >${item.oldAttachmentName }.${item.attachmentSuffix}</a> --%>
		                    			<a href="${ServerURL}${item.attachmentPath}"  download="${item.oldAttachmentName}.${item.attachmentSuffix}">${item.oldAttachmentName}.${item.attachmentSuffix}</a> 
		                    			<br />
		                    		</c:forEach>
		                    		<!-- </div> -->
		                    	</td>
		                    </tr>
	                    </c:if>
	                    
			</tr>
		</table>
	<div class="height_15"></div>
	</div>
</body>
</html>