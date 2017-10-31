<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>巡检任务详情</title>
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
				<h1 class="title">巡检任务详情</h1>
			</header>
			<div class="content">
				<div class="div-content">
					<table class="gd-table">
						<tr>
							<th class="label-title">任务编号</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.cx_task_code}</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">任务名称</th>
							<td class="unwrite">
								<span class="title-content">${result.cx_task_name}</span>
							</td>
						</tr>
						<tr>
							<th class="label-title">任务日期</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">
										<fmt:parseDate value="${result.cx_task_date}"  var="tasktime" pattern="yyyyMMdd"/>
										<fmt:formatDate value="${tasktime }" pattern="yyyy-MM-dd"/>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">计划结束时间</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">
										<fmt:parseDate value="${result.cx_task_pend_time}"  var="pendtime" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate value="${pendtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">实际完成时间</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">
										<fmt:parseDate value="${result.cx_task_aend_time}"  var="aendtime" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate value="${aendtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">任务状态</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">
										<c:if test="${result.cx_task_status eq '0'}">待下发</c:if>
										<c:if test="${result.cx_task_status eq '1'}">已下发</c:if>
										<c:if test="${result.cx_task_status eq '2'}">在执行</c:if>
										<c:if test="${result.cx_task_status eq '3'}">已完成</c:if>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">巡检人员</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.task_person}</span>
								</div>
							</td>
						</tr>
						<tr>
							<th class="label-title">是否超期</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">
										<c:if test="${result.is_over_time eq '0'}">否</c:if>
										<c:if test="${result.is_over_time eq '1'}">是</c:if>
									</span>
								</div>
							</td>
						</tr>
						<c:if test="${result.is_over_time eq '1'}">
							<tr>
								<th class="label-title">是否正常超期</th>
								<td class="unwrite">
									<div class="situation">
										<span class="title-content">
											<c:if test="${result.sf_zc_over_time eq '0'}">否</c:if>
											<c:if test="${result.sf_zc_over_time eq '1'}">是</c:if>
										</span>
									</div>
								</td>
							</tr>
							<tr>
								<th class="label-title">超期说明</th>
								<td class="unwrite">
									<span class="title-content">${result.over_time_desc}</span>
								</td>
							</tr>
						</c:if>
						<tr>
							<th class="label-title">所属厂站</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${result.belong_wsc_name}</span>
								</div>
							</td>
						</tr>
				</table>
				</div>
				<!--底部按钮 start-->
				<div class="div-btn">
					<!--btm-btn2的几种用法注意：①“取消”、“返回”类：默认黑色，此类一般来说始终处于可用状态，无需加特别的class。
					②“提交”、“确认”类：可用状态为class="active",不可用状态为class="disabled",此类一般来说状态必为“可用”、“不可用”之一
					③“删除”类：可用状态为class="warning",不可用状态为class="disabled",此类一般来说状态必为“可用”、“不可用”之一-->
 					<div class="btm-btn2 "style="background:none;"><span class="confirm-ok" style="width:100%;">设施设备列表</span></div>
				</div>
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
			$(document).on('click','.confirm-ok', function () {
				var url = "${path}/mobile/inspectionRecord/toSbssList?cxTaskId=${result.cx_task_id}&tokenId=${tokenId}";
				location.href = url;
			});
		});
</script>
</body>
</html>