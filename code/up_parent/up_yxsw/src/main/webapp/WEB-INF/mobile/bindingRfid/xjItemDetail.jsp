<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>巡检点详情</title>
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
				<h1 class="title">巡检点详情</h1>
			</header>
			<div class="content">
				<div class="div-content">
					<table class="gd-table">
					<tr>
						<th class="label-title">巡检点名称</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${xjItem.xjdItemName}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">RFID编号</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${xjItem.rfidCode}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">巡检点位置</th>
						<td class="unwrite">
							<span class="title-content">${xjItem.xjdItemAddress}</span>
						</td>
					</tr>
					<tr>
						<th class="label-title">巡检点说明</th>
						<td class="unwrite">
							<span class="title-content">${xjItem.xjdItemDesc}</span>
						</td>
					</tr>
					<tr>
						<th  class="label-title">创建人</th>
						<td class="unwrite">
							<div class="situation">
								<span class="title-content">${xjItem.creatorName}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="label-title">创建时间</th>
						<td class="unwrite">
							<div class="situation">
								<fmt:parseDate value="${xjItem.createTimestemp}"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
					<div class="btm-btn2" ><span class="confirm-ok" >绑定标签</span><span class="confirm-back">返回</span></div>
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
			$(document).on('click','.confirm-back', function () {
				var url = "${path}/mobile/bindingRfid/toXjItemList?tokenId=${tokenId}";
   				location.href = url;
			});
			//nfc扫描
			$(document).on('click','.confirm-ok', function () {
				var nfcScanParams = {
					type : "1",
					className : "",
					getNfcId : function(nfcId){
// 						$.alert(nfcId);
						var remark = "";
						if("${xjItem.rfidCode}" != "" && "${xjItem.rfidCode}" != null){
							remark = "扫描结果为："+nfcId+",确定后会覆盖原来的RFID编号，确定绑定吗？";
						}else{
							remark = "扫描结果为："+nfcId+",确定绑定吗？";
						}
						 $.confirm(remark, function () {
							 $.ajax({
						         type: "POST",
						         url: "${path}/mobile/bindingRfid/doBindingRfid",
						         data: {
					          		"tokenId":"${tokenId}",
					          		"rfidCode":nfcId,
						       		"xjdItemId":"${xjItem.xjdItemId }"
						       	  },
						        success: function (appresult) {
						        	
						        	if(appresult.flag == true){
						        		if(appresult.message == "1"){
						        			$.alert("该标签已被绑定！");
						        		}else{
						        			
						        		  $.alert(appresult.message,function(){
						        			  
						        			var url = "${path}/mobile/bindingRfid/toxjItemIdDetail?xjItemId=${xjItem.xjdItemId}&tokenId=${tokenId}";
						       				location.href = url;
								          });
						        		}
									}else{
										
										if(appresult.data.login==false){
											$.alert(appresult.message);
											yxapp.project.toLogin();
										}else{
											$.toast(appresult.message);
										}
									}
							 	 },
						       	error:function(result){
						       		var data  = yxapp.base.getBaseData("network");
						        	   if(data == "false"){
							       		$.alert("网络连接失败，请检查网络后重试！");
						        	   }else{
						        		   $.alert("提交失败，请重试！");
						        	   }
						       	}
							});
						 });
					}
				};
				yxapp.device.nfcScan(nfcScanParams);
			});
	});
</script>
</body>
</html>