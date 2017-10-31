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
						<c:if test="${!empty sbssDetail }">
							<tr>
								<th class="label-title">名称</th>
								<td class="unwrite">
									<div class="situation">
										<span class="title-content">${sbssDetail.name}</span>
									</div>
								</td>
							</tr>
							<tr>
								<th class="label-title">类型</th>
								<td class="unwrite">
									<div class="situation">
										<span class="title-content">
											<c:if test="${sbssDetail.detail_type eq '1'}">设备</c:if>
											<c:if test="${sbssDetail.detail_type eq '2'}">设施</c:if>
										</span>
									</div>
								</td>
							</tr>
							<tr>
								<th class="label-title">是否完成</th>
								<td class="unwrite">
									<div class="situation">
										<span class="title-content">
											<c:if test="${sbssDetail.have_complete eq '0'}">否</c:if>
											<c:if test="${sbssDetail.have_complete eq '1'}">是</c:if>
										</span>
									</div>
								</td>
							</tr>
							<tr>
								<th class="label-title">巡检时间</th>
								<td class="unwrite">
									<div class="situation">
										<span class="title-content">
											<fmt:parseDate value="${sbssDetail.opt_time}"  var="opttime" pattern="yyyyMMddHHmmss"/>
											<fmt:formatDate value="${opttime }" pattern="yyyy-MM-dd HH:mm:ss"/>
										</span>
									</div>
								</td>
							</tr>
							<tr>
								<th class="label-title">巡检说明</th>
								<td class="unwrite">
									<span class="title-content">${sbssDetail.xj_desc}</span>
								</td>
							</tr>
							<tr>
								<th class="label-title">是否有问题</th>
								<td class="unwrite">
									<div class="situation">
										<span class="title-content">
											<c:if test="${sbssDetail.sf_fault eq '0'}">否</c:if>
											<c:if test="${sbssDetail.sf_fault eq '1'}">是</c:if>
										</span>
									</div>
								</td>
							</tr>
							<c:if test="${!empty sbssDetail.bzz_sf_fault }">
								<tr>
									<th class="label-title">班组长确认</th>
									<td class="unwrite">
										<div class="situation">
											<span class="title-content">
												<c:if test="${sbssDetail.bzz_sf_fault eq '0'}">否</c:if>
												<c:if test="${sbssDetail.bzz_sf_fault eq '1'}">是</c:if>
											</span>
										</div>
									</td>
								</tr>
							</c:if>
							<c:if test="${!empty sbssDetail.sjk_sf_fault }">
								<tr>
									<th class="label-title">生技科确认</th>
									<td class="unwrite">
										<div class="situation">
											<span class="title-content">
												<c:if test="${sbssDetail.sjk_sf_fault eq '0'}">否</c:if>
												<c:if test="${sbssDetail.sjk_sf_fault eq '1'}">是</c:if>
											</span>
										</div>
									</td>
								</tr>
							</c:if>
						</c:if>
						
						<c:forEach var="lists" items="${list }" >
						<tr>
							<th class="label-title">${lists.check_item_name}</th>
							<td class="unwrite">
								<div class="situation">
									<span class="title-content">${lists.sel_name}</span>
								</div>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${!empty imgs }">
						<tr>
							<th  colspan="2" class="label-title icon-clqzp">
								巡检照片
							</th>
						</tr>
						<tr>
							<td colspan="2" class="unwrite">
								<div class="situation">
									<div class="picture-left">
					                    <img alt="${imgs.oldAttachmentName }" src="${ServerURL }/${imgs.attachmentPath }" width="80" height="60" class="photo">
				                    	<span class="red-pointe">${photoCount }</span>
									</div>
									<span class="picture-right">
									</span>
								</div>
							</td>
						</tr>
	                </c:if>
	                <c:if test="${!empty videos }">
	                	<tr>
							<th  colspan="2" class="label-title icon-sp">
								巡检视频
							</th>
						</tr>
						<tr>
							<td colspan="2">
							<div class="situation">
								<div class="radio-wraper">
									<ul>
										<c:forEach var="video" items="${videos }" >
											<li>
												<video   alt="${video.oldAttachmentName }" src="${ServerURL }/${video.attachmentPath }" class="videoimage"></video>
												<img src="${path}/mobile/img/radio-play.png" class="video-play" id="${video.attachmentPath }">
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</tr>
	                </c:if>
	                <c:if test="${!empty dealFlow }">
						<tr>
							<th  colspan="2" class="label-title">
							</th>
						</tr>
						<tr>
							<td colspan="2" class="unwrite">
							<div class="situation">
								<div class="table-show">
									 <c:forEach items="${dealFlow }" var="item">
										<table>
										    <tr>
												<td >${item.opt_name }</td>
				                 				<td >
				                 					<fmt:parseDate value="${item.opt_time }"  var="optTime" pattern="yyyyMMddHHmmss"/>
													<fmt:formatDate value="${optTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				                 				</td>
										    </tr>  
										    <tr >
						                 		<td colspan="2">${item.opt_content }</td>
										    </tr>  
										</table>
						              </c:forEach>
								</div>
							</div>
							</td>
						</tr>
					</c:if>
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
			$(".photo").click(function(){
				var faullPicString = '${photoList}';
				var faullPicArr = faullPicString.split(",");
				var position=0;
				yxapp.device.showPic(faullPicArr, position);
			});
			
			$(".video-play").click(function(){
				var videoPath="${ServerURL }" + $(this).attr("id");
				yxapp.project.playVideo(videoPath);
			});
	});
</script>
</body>
</html>