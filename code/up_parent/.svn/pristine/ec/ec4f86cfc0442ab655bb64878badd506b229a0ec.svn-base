<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>点位关联</title>
<%@include file="/system/common/header_splitmode.jsp" %> 
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--基本选项卡start-->
<script type="text/javascript" src="${path }/libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->

<script type="text/javascript">
	jQuery(function(){
		
		var list_btmH = jQuery(".list-content").outerHeight();
// 		var list_btmH = jQuery(".details-container").outerHeight();
		var h1 = jQuery("#info").outerHeight();
		var h2 = jQuery("#point").outerHeight();
		var h3 = jQuery("#pointhidden").outerHeight();
		jQuery(".basicTab_con").height(list_btmH-h1-h2-h3-43);
		jQuery(".frmrightChild").height(list_btmH-h1-h2-h3-53);
		
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
		jQuery(".basicTab").css("background-color","#f1f4ff");
	});
</script>
</head>
<body style="overflow:hidden;">
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	         	<div class="task-detail" id="info">
	            	 <span class="detail-title">
	                	 巡检点基本信息
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list" >
	                 	<tr>
	                 		<th>巡检点名称：</th>
	                 		<td>${BizTXjdItem.xjdItemName }</td>
	                 		<th>巡检点位置：</th>
	                 		<td>${BizTXjdItem.xjdItemAddress }</td>
	                 	</tr>
	                 	<tr>
	                 		<th>RFID编号：</th>
	                 		<td>${BizTXjdItem.rfidCode }</td>
	                 	</tr>
	                 	<tr>
	                 		<th>巡检点说明：</th>
	                 		<td >${BizTXjdItem.xjdItemDesc }</td>
	                 	</tr>
	                 </table>
	            </div>
	           <div class="task-detail">
			       <span class="detail-title" id="point">
			               	关联设备
			       </span>
			       <div class="to_hide_over_content" id="pointhidden" class="text" >
			       		<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
			       </div> 
			       <div class="detail-list">
			       		       	<div  class="basicTab"  iframeMode="true" 
				data='{"list":[{"name":"关联设备","url":"${path }/spotRelate/toViewEquipment?spotId=${BizTXjdItem.xjdItemId }"},
		      			{"name":"关联设施","url":"${path }/spotRelate/toViewFacility?spotId=${BizTXjdItem.xjdItemId }"},
		      			{"name":"关联安全提醒","url":"${path }/spotRelate/toViewWarning?spotId=${BizTXjdItem.xjdItemId }"}
		      				]}'>
				    <div>
				        <IFRAME scrolling="no"  style="overflow: hidden;width:100%;height: 100%;" frameBorder=0 id=frmrightChild name=frmrightChild class="frmrightChild" onload="iframeHeight('frmrightChild')" allowTransparency="true"></IFRAME>
				    </div>
				</div>
			       </div>
	         </div>
	         </div>
		</div>
    </div>
    <script type="text/javascript">
	  //刷新树
	    function refreshTree(spotId){
	    	parent.refreshTree(spotId);
	    }
    </script>
</body>
</html>