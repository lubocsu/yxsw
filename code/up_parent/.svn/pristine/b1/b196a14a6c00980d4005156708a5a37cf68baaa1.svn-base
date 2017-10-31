<%@page import="com.upsoft.systemweb.controller.IndexController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="INDEX_PREFIX" value="<%=IndexController.FORWARD_PREFIX%>"></c:set> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!--框架必需start-->
		<script type="text/javascript" src="${path}/libs/js/jquery.js"></script>
		<script type="text/javascript" src="${path}/libs/js/language/cn.js"></script>
		<script type="text/javascript" src="${path}/libs/js/framework.js"></script>
		<link href="${path}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" id="skin" prePath="${path}/" scrollerY="false"/>
		<link rel="stylesheet" type="text/css" id="customSkin"/>
		<!--框架必需end-->
		
		<script type="text/javascript" src="${path}/js/index/accordion.js"></script>
		
		<script type="text/javascript">
		    function initComplete() {
		    	$(".nav").accordion({
		            //accordion: true,
		            speed: 500,
		    	    closedSign: '[+]',
		    		openedSign: '[-]'
		    	});
		    }
		    function customHeightSet(contentHeight){
		    	
			}
		</script>
		<style>
			ul{list-style:none}
			.demo{width:300px; margin:40px auto; padding:10px; background:#f7f7f7}
			.nav {width: 213px; padding: 40px 28px 25px 10px; font-family: "Microsoft yahei", Arial, Helvetica, sans-serif;} 
			ul.nav {padding: 0; margin: 0; font-size: 1em; line-height: 0.5em; list-style: none;} 
			ul.nav li {} 
			ul.nav li a {line-height: 10px; font-size: 14px; padding: 10px 5px; color: #000; display: block; text-decoration: none; font-weight: bolder;}
			ul.nav li a:hover {background-color:#675C7C;    color:white;}
			ul.nav ul { margin: 0; padding: 0;display: none;} 
			ul.nav ul li { margin: 0; padding: 0; clear: both;} 
			ul.nav ul li a { padding-left: 20px; font-size: 12px; font-weight: normal;}
			ul.nav ul li a:hover {background-color:#D3C99C; color:#675C7C;} 
			ul.nav ul ul li a {color:silver; padding-left: 40px;} 
			ul.nav ul ul li a:hover { background-color:#D3CEB8; color:#675C7C;} 
			ul.nav span{float:right;}
		</style>
	</head>
	
	<body leftFrame="true">
		<div id="scrollContent" style="overflow-x: hidden;">
			<ul class="nav">
<%-- 				<c:forEach items="${menus}" var="menu"> --%>
<!-- 					<li> -->
<%-- 						<a href="${empty menu.url ? 'javascript:void(0);' : url}" target="${menu.target}"> --%>
<%-- 							<c:if test="${!empty menu.icon}"> --%>
<%-- 								<img src="${menu.icon}" alt="" /> --%>
<%-- 							</c:if> --%>
<%-- 							${menu.name} --%>
<!-- 						</a> -->
<%-- 						<c:if test=""> --%>
						
<%-- 						</c:if> --%>
<!-- 					</li> -->
<%-- 				</c:forEach> --%>
				<li>
					<a href="javascript:void(0);" target="_blank">首页</a>
				</li>
				<li>
					<a href="#">服务</a>
					<ul>
						<li><a href="#">JAVASCRIPT</a></li>
						<li><a href="#">PHP</a></li>
						<li><a href="#">MYSQL</a></li>
						<li><a href="#">LINUX</a></li>
					</ul>
				</li>
				<li>
					<a href="#">案例</a>
				</li>
				<li>
					<a href="#">文章</a>
					<ul>
						<li class="active">
							<a href="http://www.helloweba.com/blog-4.html" target="_blank">XHTML/CSS</a>
						</li>
						<li>
							<a href="#">Javascript/Ajax/jQuery</a>
							<ul>
								<li><a href="#">Cookies</a></li>
								<li><a href="#">Event</a></li>
								<li><a href="#">Games</a></li>
								<li><a href="#">Images</a></li>
							</ul>
						</li>
						<li>
							<a href="http://www.helloweba.com/blog-6.html" target="_blank">PHP/MYSQL</a>
						</li>
						<li>
							<a href="http://www.helloweba.com/blog-7.html" target="_blank">前端观察</a>
						</li>
						<li>
							<a href="http://www.helloweba.com/blog-9.html" target="_blank">HTML5/移动WEB应用</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="http://www.helloweba.com/about.html" target="_blank">关于</a>
				</li>
			</ul>
		</div>
	</body>
</html>