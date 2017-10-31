<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>无数据</title>
	<script type="text/javascript" src="${path}/mobile/js/yxapp.js"></script>
	<script type="text/javascript">
		alert("该标签未关联相关巡检点!");
		yxapp.project.toIndex();
	</script>
</head>
<body>
      <!-- <div class="page page-current">登录tokenId验证失败，请重新登录！</div> -->
</body>
</html>