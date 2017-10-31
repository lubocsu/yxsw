<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息详情</title>
<link rel="stylesheet" type="text/css" href="${path }/css/style.css"/>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script src="${path }/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function(){
		
	});
</script>
</head>
<body>
    	<table class="detail-list" >
			<tr>
				<th style="width:20%;">标题：</th>
				<td>
					${result.title }
				</td>
			</tr>
			<tr>
				<th>发送人：</th>
				<td>
					${result.creatorName }
				</td>
			</tr>
			<tr>
				<th>发送时间：</th>
				<td>
					<fmt:parseDate value="${result.createTimestemp }"  var="createtime" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>是否重要：</th>
				<td>
					<c:if test="${result.importantLevel eq '1'}">一般</c:if>
					<c:if test="${result.importantLevel eq '2'}">重要</c:if>
				</td>
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					${result.content }
				</td>
			</tr>
		</table>
</body>
</html>