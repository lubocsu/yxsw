<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>重新登录</title>
	<script type="text/javascript" src="${path}/mobile/js/yxapp.js"></script>
	<script type="text/javascript">
		alert("身份认证已过期，请重新登录");
		yxapp.project.toLogin();
	</script>
</head>
<body>
      <!-- <div class="page page-current">登录tokenId验证失败，请重新登录！</div> -->
</body>
</html>