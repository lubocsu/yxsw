<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备类型详情</title>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script src="${path }/js/jquery-1.7.2.min.js" type="text/javascript"></script>
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
</script>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
    	 <div class="details-container">
         	<div class="task-detail">
            	 <span class="detail-title">
                	 设备类型详情
                 </span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <table class="detail-list" >
                 	<tr>
                 		<th>父类型：</th>
                 		<td>${parentName }</td>
                 		<th>类型名称：</th>
                 		<td>${bizTSbTypesEntity.name }</td>
                 	</tr>
                 	<tr>
                 		<th>类型编码：</th>
                 		<td>${bizTSbTypesEntity.code }</td>
                 		<th>计量单位：</th>
                 		<td>${bizTSbTypesEntity.unitname }</td>
                 	</tr>
                 	<tr>
                 		<th>层级关系：</th>
                 		<td>${bizTSbTypesEntity.layer }</td>
                 		<th>排序号：</th>
                 		<td>${bizTSbTypesEntity.orders }</td>
                 	</tr>
                 	<tr>
                 		<th>是否停用：</th>
                 		<td>${bizTSbTypesEntity.outservicename }</td>
                 		<th>创建时间：</th>
                 		<td>
                 			<fmt:parseDate value="${bizTSbTypesEntity.create_timestemp }"  var="createtime" pattern="yyyyMMddHHmmss"/>
							<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                 		</td>
                 	</tr>
                 	<tr>
                 		<th>类型说明：</th>
                 		<td>${bizTSbTypesEntity.remark }</td>
                 	</tr>
                 </table>
            </div>
         </div>
		</div>
    </div>
</body>
</html>