<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看检查项定义</title>
<script type="text/javascript" src="${path }/js/jquery-1.7.2.min.js"></script>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	jQuery(function(){
		/*隐藏展开功能*/
		jQuery(".to_hide_over_content").live("click",function(){
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
<style type="text/css">
	#check_item_detail_table tr td{border: 1px solid #abc1cc;text-align: center;}
</style>
</head>
<body style="overflow: hidden;">
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container" style="margin: 10px 15px;">
	    	 	<!-- 指标项管理 start -->
	         	<div class="task-detail">
	            	 <span class="detail-title">检查项管理</span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div>
					<table class="detail-list">
						<tr>
							<th>检查项名称：</th>
							<td>${checkItemMap.checkItemName }</td>
							<th>检查项编号：</th>
							<td>${checkItemMap.checkItemCode }</td>
						</tr>
						<tr>
							<th>检查项类型：</th>
							<td>${checkItemMap.checkItemTypeName }</td>
							<th>输入类型：</th>
							<td>${checkItemMap.inputTypeName }</td>
						</tr>
						<tr>
							<th>检查项说明：</th>
							<td colspan="3"><p>${checkItemMap.checkItemDesc }</p></td>
						</tr>
					</table>
				</div>
	            <!-- 指标项管理 end -->
	            <c:if test="${!empty checkItemDetailList }">
	            	<!-- 单选项 start -->
		            <div class="task-audit" id='checkItemDetal_div'>
				    	<span class="detail-title">选项明细</span>
				        <div class="to_hide_over_content" class="text">
				        	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				        </div>
						<table class="detail-list" id="check_item_detail_table">
							<tr style="background-color: #c6e5fb;">
								<td style="color: #006699;">序号</td>
								<td style="color: #006699;">编码</td>
								<td style="color: #006699;">名称</td>
								<td style="color: #006699;">是否异常</td>
								<td style="color: #006699;">是否默认</td>
							</tr>
							<c:forEach items="${checkItemDetailList }" var="detail">
								<tr>
									<td style="padding: 0px;">${detail.selSort }</td>
									<td style="padding: 0px;">${detail.selValue }</td>
									<td style="padding: 0px;">${detail.selName }</td>
									<td style="padding: 0px;">${detail.sfzcName }</td>
									<td style="padding: 0px;">${detail.sfmrName }</td>

								</tr>
							</c:forEach>
						</table>
					</div>
				    <!-- 单选项 end -->
	            </c:if>
	         </div>
         </div>
    </div>
</body>
</html>