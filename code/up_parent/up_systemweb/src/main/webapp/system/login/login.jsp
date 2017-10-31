<%@page import="com.upsoft.login.controller.LoginController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="LOGIN_PREFIX" value="<%=LoginController.FORWARD_PREFIX%>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>渝西水务生产综合管理平台</title>
<link href="${path}/system/login/skin/style.css" rel="stylesheet" type="text/css" id="skin"/>
<script type="text/javascript">
	if (window.top != window.self) {
		window.top.location = "${path}${LOGIN_PREFIX}/toLogin?message=${message}";
	}
</script>
<script type="text/javascript" src="${path}/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path}/libs/js/login.js"></script>

<!--居中显示start-->
<script type="text/javascript" src="${path}/libs/js/method/center-plugin.js"></script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/
</style>
</head>
<body >
	<div class="login_main">
		<div class="login_top">
		</div>
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<form id="loginForm" action="login.do" class="login_form" method="post">
					<div class="login_user"><input type="text" id="username"></div>
					<div class="login_pass"><input type="password" id="password"></div>
					<div class="clear"></div>
					<div class="login_button">
						<div class="login_button_left"><input type="button" onclick="login()"/></div>
						<div class="login_button_right"><input type="reset" value=""/></div>
						<div class="clear"></div>
					</div>
					</form>
					<div class="login_info" style="display:none;"></div>
					<div class="login_info2">&nbsp;&nbsp;</div>
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">版权所有：重庆远通电子技术开发有限公司</div>
		</div>
	</div>
<script>
	$(function(){
		if((null!="${tokenInvalided}")&&(""!="${tokenInvalided}")){
			alert("${tokenInvalided}");
		}
		//居中
		 $('.login_main').center();
		 document.getElementById("username").focus();
		 $("#username").keydown(function(event){
		 	if(event.keyCode==13){
				login();
			}
		 });
		 $("#password").keydown(function(event){
		 	if(event.keyCode==13){
				login();
			}
		 });
		 
		// 防止浏览器后退 2017.10.21 by huyi
			history.pushState(null, null, document.URL);
		    window.addEventListener('popstate', function () {
		        history.pushState(null, null, document.URL);
		    });
		 
	});

	//登录
	function login() {
		var errorMsg = "";
		var loginName = document.getElementById("username");
		var password = document.getElementById("password");
		if(!loginName.value){
			errorMsg += "&nbsp;&nbsp;用户名不能为空!";
		}
		if(!password.value){
			errorMsg += "&nbsp;&nbsp;密码不能为空!";
		}

		if(errorMsg != ""){
			$(".login_info").html(errorMsg);
			$(".login_info").show();
		}
		else{
			$(".login_info").show();
			$(".login_info").html("&nbsp;&nbsp;正在登录中...");
			//登录处理
			$.ajax({
				url: "${path}${LOGIN_PREFIX}/doLogin",
				type: "POST",
				dataType: "json",
				data: {
					usercode: loginName.value,
					password: password.value
				},
				success: function(result){
					if (result == null) {
						$(".login_info").html("&nbsp;&nbsp;登陆失败！");
						$("#password").val("");
						return false;
					}
					if (result.status == "true" || result.status == true) {
						// 从后台获取跳转路径
						$.post("${path}${LOGIN_PREFIX}/getForwardUrl",{usercode: loginName.value,password: password.value}, 
							function(forwatUrl){
								$(".login_info").html("&nbsp;&nbsp;登录成功，正在转到主页...");
								window.location = forwatUrl + "?tokenId=" + result.tokenId;
						});
					} else {
						$(".login_info").html("&nbsp;&nbsp;" + result.message);
						$("#password").val("");
					}
				}
			});
		}
	}
</script>
</body>
</html>

