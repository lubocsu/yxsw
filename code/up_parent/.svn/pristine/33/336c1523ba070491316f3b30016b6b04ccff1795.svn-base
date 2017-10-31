<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>巡检记录详情</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--SUI mobile必须-->
	<link rel="stylesheet" href="${path}/mobile/libs/sui/css/sm.css" />
	<link rel="stylesheet" href="${path}/mobile/libs/sui/css/sm-extend.css" />    
	<!--自定义样式-->
	<link rel="stylesheet" href="${path}/mobile/css/yxapp.css" />
</head>
<body>
	<div class="page-group">
    	<div class="page page-current">
	    	<header class="bar bar-nav">	
				<span class="custom-icon fanhui"></span>
				<h1 class="title">巡检记录详情</h1>
			</header>
			<div class="content">
				<div class="div-content">
					<table class="gd-table">
						<tr>
							<th class="label-title">巡检点名称</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.xjd_item_name}</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">RFID编号</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.rfid_code}</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">巡检点位置</th>
							<td class="unwrite">
<!-- 								<div class="situation"> -->
									<span class="title-content">${result.xjd_item_address}</span>
<!-- 								</div> -->
							</td>
						</tr>
						<tr>
							<th class="label-title">巡检点说明</th>
							<td class="unwrite">
<!-- 								<div class="situation"> -->
									<span class="title-content">${result.xjd_item_desc}</span>
<!-- 								</div> -->
							</td>
						</tr>
						<tr>
							<th class="label-title">RFID确定方式</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.rfid_confirmed_type}</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">是否已完成</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.have_complete}</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">操作时间</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.opt_time}</span>
								</div>
							</td>
						</tr>
				</table>
				</div>
				<!--底部按钮 start-->
<!-- 				<div class="div-btn"> -->
					<!--btm-btn2的几种用法注意：①“取消”、“返回”类：默认黑色，此类一般来说始终处于可用状态，无需加特别的class。
					②“提交”、“确认”类：可用状态为class="active",不可用状态为class="disabled",此类一般来说状态必为“可用”、“不可用”之一
					③“删除”类：可用状态为class="warning",不可用状态为class="disabled",此类一般来说状态必为“可用”、“不可用”之一-->
<!-- 					<div class="btm-btn2"><span class="confirm-ok" style="width:100%;">巡检纪录</span></div> -->
<!-- 				</div> -->
				<!--底部按钮 end-->
			</div>
		</div>
	</div>
	<!--SUI mobile必须-->
	<script type='text/javascript' src='${path}/mobile/js/zepto.js' charset='utf-8'></script>
	<script type='text/javascript' src='${path}/mobile/libs/sui/js/sm.js' charset='utf-8'></script>
	<script type='text/javascript' src='${path}/mobile/libs/sui/js/sm-extend.js' charset='utf-8'></script>	
	<!--自定义脚本-->
	<script type="text/javascript" src="${path}/mobile/js/uc-ui.js"></script>
	<script type="text/javascript" src="${path}/mobile/js/yxapp.js"></script>
	<script type="text/javascript">
		$(function(){
			$(document).on('click','.fanhui', function () {
				yxapp.webview.goBack();
			});
	});
</script>
</body>
</html>