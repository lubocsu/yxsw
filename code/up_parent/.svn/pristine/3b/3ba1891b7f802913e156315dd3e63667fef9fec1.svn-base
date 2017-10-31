<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检点详情</title>
<link rel="stylesheet" type="text/css" href="${path }/css/style.css"/>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script src="${path }/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function(){
// 		jQuery("td[class='_th']").css("color","#09c")
	});
</script>
</head>
<body>
    	<table class="detail-list" >
			<tr>
				<th style="width:20%;">巡检点名称：</th>
				<td>
					${result.xjdItemName  }
				</td>
			</tr>
			<tr>
				<th>巡检点位置：</th>
				<td>
					${result.xjdItemAddress }
				</td>
			</tr>
			<tr>
				<th>RFID编号：</th>
				<td>
					${result.rfidCode }
				</td>
			</tr>
			<tr>
				<th>巡检点说明：</th>
				<td>
					${result.xjdItemDesc }
				</td>
			</tr>
			<tr>
				<th>创建人：</th>
				<td>
					${result.creatorName }
				</td>
			</tr>
			<tr>
				<th>创建时间：</th>
				<td>
					<fmt:parseDate value="${result.createTimestemp }"  var="createtime" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</table>
</body>
</html>