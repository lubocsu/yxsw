<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备设施巡检详情</title>
<%@include file="/system/common/header_splitmode.jsp" %> 
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--基本选项卡start-->
<script type="text/javascript" src="${path }/libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->
<script type="text/javascript">


	jQuery(function(){
		
		var list_btmH = jQuery(".list-content").outerHeight();
		jQuery(".details-container").height(list_btmH-20);
		var h1 = jQuery("#info").outerHeight();
		var h2 = jQuery("#point").outerHeight();
		var h3 = jQuery("#pointhidden").outerHeight();
		jQuery(".point-iframe").height(list_btmH-h1-h2-h3-8);
		
		jQuery(".to_hide_over_content").on("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).next(".detail-list").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).next(".detail-list").show();
			}
		});
	});
	function showPic(ttaskItemSbssId){
		var url = '${path }/patrollingRecords/viewPic?ttaskItemSbssId='+ttaskItemSbssId+'&_='+new Date();		
		jQuery("#showPic_3 iframe").attr("src",url);
		jQuery("#showPic_3").show();
	}
	
	function hidePic(){
		jQuery("#showPic_3").hide();
	}
</script>
<style type="text/css">
	.task-audit-material table tr td{
		border: 1px solid #99ccff;
		text-align: center;
	}
	.showPic{width: 100%;height: 100%;display: none; position: relative;}
	#_left,#_right{width: 50%;height: 100%; float:left;}
	#showPic_1_2 iframe,#showPic_3 iframe {width: 100%;height: 100%;}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	    	 <c:if test="${!empty sbssInfo }">
			            <div class="task-detail ">
			            	 <span class="detail-title">
			                		巡检详情
			                 </span>
				             <div class="to_hide_over_content" class="text">
				          		<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
				        	 </div> 
			                 <table class="detail-list" >
		                 		<tr>
		                 			<th>类型:</th>
		                 			<td>${sbssInfo.detail_type }</td>
		                 			<th>名称:</th>
		                 			<td>${sbssInfo.name }</td>
		                 		</tr>
		                 		<tr>
		                 			<th>是否必扫标签:</th>
		                 			<td>${sbssInfo.must_scan }</td>
		                 			<th>确认方式:</th>
		                 			<td>${sbssInfo.ewm_confirmed_type }</td>
		                 		</tr>
		                 		<tr>
		                 			<th>是否已完成:</th>
		                 			<td>${sbssInfo.have_complete }</td>
		                 			<th>巡检时间:</th>
		                 			<td>
		                 				<fmt:parseDate value="${sbssInfo.opt_time }"  var="optTime" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate value="${optTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		                 			</td>
		                 		</tr>
		                 		<tr>
		                 			<th>是否有问题:</th>
		                 			<td>
		                 				<c:if test="${sbssInfo.sf_fault eq '0'}">否</c:if>
										<c:if test="${sbssInfo.sf_fault eq '1'}">是</c:if>
									</td>
		                 		</tr>
		                 		<tr>
			                 		<c:if test="${!empty sbssInfo.bzz_sf_fault }">
			                 			<th>班组长确认:</th>
			                 			<td>
			                 				<c:if test="${sbssInfo.bzz_sf_fault eq '0'}">否</c:if>
											<c:if test="${sbssInfo.bzz_sf_fault eq '1'}">是</c:if>
										</td>
									</c:if>
									<c:if test="${!empty sbssInfo.sjk_sf_fault }">
			                 			<th>生技科确认:</th>
			                 			<td>
			                 				<c:if test="${sbssInfo.sjk_sf_fault eq '0'}">否</c:if>
											<c:if test="${sbssInfo.sjk_sf_fault eq '1'}">是</c:if>
										</td>
									</c:if>
		                 		</tr>
		                 		<tr>
		                 			<th>巡检说明:</th>
		                 			<td colspan="3">${sbssInfo.xj_desc }</td>
		                 		</tr>
			                 </table>
		            	</div>
	            </c:if>
	         	<div class="task-detail ">
	            	 <span class="detail-title">
	                	巡检结果
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list" >
	                 	<!-- 设备设施检查项明细-->
	                 	<c:if test="${!empty list }">
		                 	<c:out value="<tr>" escapeXml="false"></c:out>
							<c:forEach var="lists" items="${list }" varStatus="status">
		     					<th>${lists.check_item_name}:</th>
		     					<td>${lists.sel_name}</td>
		     					<c:if test="${status.count%2==0}">  
	            					<c:out value="</tr>" escapeXml="false"></c:out>  
	           						<c:out value="<tr>" escapeXml="false"></c:out>  
	       						 </c:if>  
							</c:forEach>
							<c:out value="</tr>" escapeXml="false"></c:out>
						</c:if>
						<c:if test="${!empty imgs }">
		                    <tr>
		                    	<th>巡检照片：</th>
		                    	<td colspan="3">
		                    		<div class="imglist">
		                    		<c:forEach items="${imgs }" var="item">
		                    			<img onclick="showPic('${item.businessId }');" alt="${item.oldAttachmentName }" src="${ServerURL }/${item.attachmentPath }" width="80" height="60">
		                    		</c:forEach>
		                    		</div>
		                    	</td>
		                    </tr>
	                    </c:if>
	                    <c:if test="${!empty videos }">
		                    <tr>
		                    	<th>巡检视频：</th>
		                    	<td colspan="3">
		                    		<div class="imglist">
		                    		<c:forEach items="${videos }" var="item">
										<video width="150" height="150" controls="controls">
											<source src="${ServerURL }/${item.attachmentPath }" type="video/${item.attachmentSuffix }">
										</video>
		                    		</c:forEach>
		                    		</div>
		                    	</td>
		                    </tr>
	                    </c:if>
	                    <tr style="height:0px;">
	                    <th style="height:0px;"></th>
	                    <td style="height:0px;"></td>
	                    <th style="height:0px;"></th>
	                    <td style="height:0px;"></td>
	                    </tr>
	                 </table>
	            </div>
	            <c:if test="${!empty dealFlow }">
		            
			            <div class="task-detail ">
			            	 <span class="detail-title">
<%-- 			                		问题处理流程${deals.count} --%>
			                 </span>
				             <div class="to_hide_over_content" class="text">
				          		<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
				        	 </div> 
			                 <table border="1px" class="detail-list">
			                 	<tr style="background-color: #c6e5fb;">
			                 		<td style="width:10%;color: #006699;text-align: center;">操作人:</td>
			                 		<td style="width:20%;color: #006699;text-align: center;">操作时间:</td>
			                 		<td style="color: #006699;text-align: center;">操作内容:</td>
			                 	</tr>
			                 	<c:forEach var="deal" items="${dealFlow }" varStatus="deals">
				                 	<tr>
				                 		<td style="text-align: center;">${deal.opt_name }</td>
				                 		<td style="text-align: center;">
				                 			<fmt:parseDate value="${deal.opt_time }"  var="optTime" pattern="yyyyMMddHHmmss"/>
											<fmt:formatDate value="${optTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				                 		</td>
				                 		<td>${deal.opt_content }</td>
				                 	</tr>
			                 	 </c:forEach>
			                 </table>
		            	</div>
	            </c:if>
	         </div>
		</div>
    </div>
    <!-- 设备图片 -->
    <c:if test="${!empty imgs }">
	    <div id="showPic_3" class="showPic">
	    	<iframe frameborder="0"></iframe>
	    </div>
    </c:if>
</body>
</html>