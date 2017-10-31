<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检任务详情</title>
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
	// 	jQuery(".basicTab_con").height(list_btmH-h1-h2-h3-33);
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
	                	巡检任务基本信息
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list" >
	                 	<tr>
	                 		<th>任务名称：</th>
	                 		<td>${result.cx_task_name }</td>
	                 		<th>任务日期：</th>
	                 		<td>
	                 			<fmt:parseDate value="${result.cx_task_date }"  var="createtime" pattern="yyyyMMdd"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd"/>
	                 		</td>
	                 		<th>任务编号：</th>
	                 		<td>${result.cx_task_code }</td>
	                 	</tr>
	                 	<tr>
	                 		<th>计划开始时间：</th>
	                 		<td>
	                 			<fmt:parseDate value="${result.cx_task_pstart_time }"  var="pstarttime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${pstarttime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                 		</td>
	                 		<th>计划结束时间：</th>
	                 		<td>
	                 			<fmt:parseDate value="${result.cx_task_pend_time }"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                 		</td>
	                 		<th>实际开始时间：</th>
	                 		<td>
	                 			<fmt:parseDate value="${result.cx_task_astart_time }"  var="astarttime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${astarttime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                 		</td>
	                 	</tr>
	                 	<tr>
	                 		<th>实际结束时间：</th>
	                 		<td>
	                 			<fmt:parseDate value="${result.cx_task_aend_time }"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                 		</td>
	                 		<th>任务状态：</th>
	                 		<td>${result.cx_task_status }</td>
	                 		<th>巡检人员：</th>
	                 		<td>${result.task_person }</td>
	                 	</tr>
	                 	<tr>
	                 		<th>是否超期：</th>
	                 		<td>${result.is_over_time }</td>
	                 		<th>是否正常超期：</th>
	                 		<td>${result.sf_zc_over_time }</td>
	                 	</tr>
	                 	<tr>
	                 		<th>超期说明：</th>
	                 		<td colspan="3">${result.over_time_desc }</td>
	                 	</tr>
	                 </table>
	            </div>
	           <div class="task-detail" >
			       <span class="detail-title" id="point">
			               	巡检点信息
			       </span>
			       <div class="to_hide_over_content " id="pointhidden" class="text">
			       		<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
			       </div> 
			       <div class="detail-list">
			       		<iframe  style="width: 100% ;height: 100%;" frameborder="0"  class="point-iframe" src="${path }/patrollingRecords/toPointList?cxTaskId=${result.cx_task_id }"></iframe>
			       </div>
	         </div>
	         </div>
		</div>
    </div>
</body>
</html>