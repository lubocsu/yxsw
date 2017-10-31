<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="addr" value="${pageContext.request.localAddr}"></c:set>
<c:set var="port" value="${pageContext.request.localPort}"></c:set>
<c:set var="cPath" value="${pageContext.request.contextPath }" ></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>登录中转站</title>
		<script type="text/javascript">
			function to_platform_login(){
				if(null!="${tokenInvalided}"&&""!="${tokenInvalided}"){
					alert("${tokenInvalided}");
				}
				window.location = 'http://${addr}:${port}/up_systemweb/login/toLogin?_hub=${cPath}&Authentication=${Authentication}';	
				// _hub 标识来自于子系统跳转，记录了子系统的上下
			}
		</script>
	</head>
	<body onload="to_platform_login();"></body>
</html>