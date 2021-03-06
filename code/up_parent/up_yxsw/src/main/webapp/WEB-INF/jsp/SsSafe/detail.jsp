<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设施关联设备</title>
 <%@include file="/system/common/header_splitmode.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/js/sssafe/detail.js"></script>
<script type="text/javascript">
	jQuery(function(){
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
	var ssId = "${ssItem.ssId}";
	var grid = null;
	
	var typeId = '${typeId}'

	var trueOrFalse//判断是否渲染增加，删除按钮
	if (typeId <= 2) {

		trueOrFalse = false
	} else {
		trueOrFalse = true

	}
	function initComplete() {
		SBSSRelation.initGrid();
	}
</script>
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
                 <table class="detail-list" >
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
		                 		<th>备注：</th>
								<td colspan="3">${ssItem.remark  }</td>
		                 	</tr>
		                 	<tr>
								
							</tr>
                 </table>
            </div>
            <div class="task-detail">
            	 <span class="detail-title">
                	关联安全提醒
                 </span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <div id="SsCheckGrid" style="float: left;"></div>
            </div>
         </div>
		</div>
    </div>
    <script type="text/javascript">
      //刷新树
        function refreshTree(){
            parent.refreshTree();
        }
    </script>
</body>
</html>