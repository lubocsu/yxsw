<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务巡检点详情</title>
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
</script>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	         	<div class="task-detail " id="info">
	            	 <span class="detail-title">
	                	巡检点基本信息
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list" >
	                 	<tr>
	                 		<th>巡检点名称：</th>
	                 		<td>${result.xjd_item_name }</td>
	                 		<th>巡检点位置：</th>
	                 		<td>${result.xjd_item_address }</td>
	                 	</tr>
	                 	<tr>
	                 		<th>确认方式：</th>
	                 		<td>${result.rfid_confirmed_type }</td>
	                 		<th>确认时间：</th>
	                 		<td>
	                 			<fmt:parseDate value="${result.opt_time }"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                 		</td>
	                 	</tr>
	                 	<tr>
	                 		<th>巡检点说明：</th>
	                 		<td colspan="3">${result.xjd_item_desc }</td>
	                 	</tr>
	                 </table>
	            </div>
	           <div class="task-detail" >
			       <span class="detail-title" id="point">
			               	设备设施信息
			       </span>
			       <div class="to_hide_over_content " id="pointhidden" class="text">
			       		<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
			       </div> 
			       <div class="detail-list">
			       		<iframe  style="width: 100% ;height: 100%;" frameborder="0"  class="point-iframe" src="${path }/patrollingRecords/toSbssList?taskItemId=${result.task_item_id }"></iframe>
			       </div>
	         </div>
	         </div>
		</div>
    </div>
</body>
</html>