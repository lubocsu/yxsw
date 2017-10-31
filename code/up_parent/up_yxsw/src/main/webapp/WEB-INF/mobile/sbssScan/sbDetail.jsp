<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>设备详情</title>
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
				<h1 class="title">设备详情</h1>
			</header>
			<div class="content">
				<div class="div-content">
					<table class="gd-table">
					<tr>
						<th class="label-title">设备编码</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${bizTSbBaseinfo.sbCode}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">设备财务编码</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${bizTSbBaseinfo.sbFnCode}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">设备名称</th>
						<td class="unwrite">
<!-- 							<div class="situation"> -->
								<span class="title-content">${bizTSbBaseinfo.sbName}</span>
<!-- 							</div> -->
						</td>
					</tr>
					<tr>
						<th class="label-title">设备简称</th>
						<td class="unwrite">
<!-- 							<div class="situation"> -->
								<span class="title-content">${bizTSbBaseinfo.sremark}</span>
<!-- 							</div> -->
						</td>
					</tr>
					<tr>
						<th class="label-title">设备类别</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${bizTSbBaseinfo.sbSort}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th  class="label-title">设备类型</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${bizTSbBaseinfo.sbTypeId}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th  class="label-title">设备制造商</th>
						<td class="unwrite">
<!-- 							<div class="situation"> -->
								<span class="title-content">${factory.name}</span>
<!-- 							</div> -->
						</td>
					</tr>
					<tr>
						<th  class="label-title">是否在线采集仪</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">
									<c:if test="${bizTSbBaseinfo.isZxcjy eq '0'}">否</c:if>
									<c:if test="${bizTSbBaseinfo.isZxcjy eq '1'}">是</c:if>
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<th  class="label-title">国产进口</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.gcjk}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">设备型号</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.sbxh}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">性能参数</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.xncs}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">结构原理</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.jgyl}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">设备状态</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.zyStatus}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">安装位置</th>
						<td class="unwrite">
<!-- 							<div class="situation"> -->
								<span class="title-content">${bizTSbBaseinfo.setAddress}</span>
<!-- 							</div> -->
						</td>
					</tr>
					<tr>
						<th class="label-title">购入日期</th>
						<td class="unwrite">
							<div class="situation">
								<fmt:parseDate value="${bizTSbBaseinfo.buyDate}"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd"/>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">是否必扫二维码</th>
						<td class="unwrite">
							<div class="situation">
									<c:if test="${bizTSbBaseinfo.byzd eq '0'}">否</c:if>
									<c:if test="${bizTSbBaseinfo.byzd eq '1'}">是</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">备注</th>
						<td class="unwrite">
<!-- 							<div class="situation"> -->
								<span class="title-content">${bizTSbBaseinfo.remark}</span>
<!-- 							</div> -->
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<th class="label-title">是否删除</th> -->
<!-- 						<td class="unwrite"> -->
<!-- 							<div class="situation"> -->
<%-- 								${bizTSbBaseinfo.delFlag} --%>
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th class="label-title">设备原值</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.startValue}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">最近评估现值</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.zjpgValue}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">开始使用日期</th>
						<td class="unwrite">
							<div class="situation">
								<fmt:parseDate value="${bizTSbBaseinfo.startUseDate}"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd"/>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">最近评估时间</th>
						<td class="unwrite">
							<div class="situation">
								<fmt:parseDate value="${bizTSbBaseinfo.zjpgSj}"  var="ctime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${ctime }" pattern="yyyy-MM-dd"/>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">最低使用年限</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.canUseYear}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">已折旧年限</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.haveLostYear}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">X_坐标</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.longitude}
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">Y_坐标</th>
						<td class="unwrite">
							<div class="situation">
								${bizTSbBaseinfo.latitude}
							</div>
						</td>
					</tr>
					<c:if test="${!empty attachmentList }">
						<tr>
							<th  colspan="2" class="label-title icon-sycl">
									附件
							</th>
						</tr>
						<tr>
							<td colspan="2" class="unwrite">
							<div class="situation">
								<div class="table-show">
									<table>
									 <c:forEach items="${attachmentList }" var="item">
								            	<tr>
									            	<td>
									                	<a href="#" class="downLoad"  id="${item.attachmentPath}">${item.oldAttachmentName }.${item.attachmentSuffix}</a>   	
									            	</td>
								                </tr>    
						              </c:forEach>
									</table>
								</div>
							</div>
							</td>
						</tr>
					</c:if>
				</table>
				</div>
				<!--底部按钮 start-->
				<div class="div-btn">
					<!--btm-btn2的几种用法注意：①“取消”、“返回”类：默认黑色，此类一般来说始终处于可用状态，无需加特别的class。
					②“提交”、“确认”类：可用状态为class="active",不可用状态为class="disabled",此类一般来说状态必为“可用”、“不可用”之一
					③“删除”类：可用状态为class="warning",不可用状态为class="disabled",此类一般来说状态必为“可用”、“不可用”之一-->
					<div class="btm-btn2" style="background:none;"><span class="confirm-ok" style="width:100%;">巡检记录</span></div>
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
				var url = "${path}/mobile/sbssScan/toXjList?xjId=${bizTSbBaseinfo.sbId}&tokenId=${tokenId}";
				location.href = url;
			});
			$(".downLoad").click(function(){
				var videoPath="${ServerURL }" + $(this).attr("id");
				yxapp.project.downOrOpenFile(videoPath);
			});
	});
</script>
</body>
</html>