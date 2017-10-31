<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设施关联设备</title>
 <%@include file="/system/common/header_splitmode.jsp" %>
 <%@include file="/system/common/menu_permission.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/js/ssattachsb/detail.js"></script>
<script type="text/javascript">
	var onlyQuery = "${onlyQuery}"=="true"?true:false;
	jQuery(function(){
		jQuery(".to_hide_over_content").on("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).next(".detail-list2").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).next(".detail-list2").show();
			}
		});
	});
	var ssId = "${ssItem.ssId}";
	var grid = null;
	
	function initComplete(){
		SBSSRelation.initGrid();
	}
</script>
<style type="text/css">
	#ssInfoDetailTable tr td{
		vertical-align: top;
		padding-top: 7px;
	}
</style>
</head>
<body style="overflow: hidden;">
	<div class="details-max">
		<div class="list-content">
    	 <div class="details-container">
         	<div class="task-detail">
            	 <span class="detail-title">
                	设施详情
                 </span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <table class="detail-list detail-list2" id="ssInfoDetailTable">
                 	<tr>
		                 		<th>父类设施：</th>
		                 		<td>${parentName }</td>
		                 		<th>设施名称：</th>
		                 		<td>${ssItem.name }</td>
		                 	</tr>
		                 	<tr>
		                 		<th>设施编号：</th>
		                 		<td>${ssItem.code }</td>
		                 		<th>层级关系：</th>
		                 		<td>${layerName }</td>
		                 	</tr>
		                 	<tr>
		                 		<th>功能说明：</th>
		                 		<td>${ssItem.function }</td>
		                 		<th>是否扫描二维码：</th>
		                 		<td>${byzdName }</td>
		                 	</tr>
		                 	<tr>
								<th>备注：</th>
								<td colspan="3">${ssItem.remark  }</td>
							</tr>
                 </table>
            </div>
            <div class="task-detail">
            	 <span class="detail-title">
                	设施设备关联信息
                 </span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <div id="sbssRelationGrid" class="detail-list2" style="float: left;"></div>
            </div>
         </div>
		</div>
    </div>
</body>
</html>