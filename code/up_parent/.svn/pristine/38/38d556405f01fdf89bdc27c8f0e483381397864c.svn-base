<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备厂商详情</title>
<script type="text/javascript" src="${path }/js/jquery-1.7.2.min.js"></script>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${path }/js/pis-common.js"></script>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>
<script type="text/javascript">
	jQuery(function(){
		jQuery(".time").each(function(){
			$(this).text(dateStr2Str($(this).text(),"$1-$2-$3 $4:$5"));
		});
		
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
<%-- 			<div class="order-status-name"><span style="float:right;">${order.order_status_name }</span><div></div></div> --%>
	    	 <div class="details-container">
	    	 	<!-- 基本信息 start -->
	         	<div class="task-detail">
	            	 <span class="detail-title">厂商基本信息</span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list">
	                 	<tr>
	                 		<th>厂商名称：</th>
	                 		<td>${result.name }</td>
	                 		<th>厂商类型：</th>
	                        <td>${result.typename }</td>
	                 	</tr>
	                 	<tr>
	                        <th>厂商地址：</th>
	                        <td colspan="3" valign="middle">
	                        	<div style="float: left; height: 30px;line-height: 30px;">${result.address }</div>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>厂商法人代表：</th>
	                    	<td>${result.responsobility }</td>
	                    	<th>厂商性质：</th>
	                    	<td>${result.xzname}</td>
	                    </tr>
	                    <tr>
	                    	<th>是否停用：</th>
	                    	<td>${result.outservicename}</td>
	                        <th>企业联系电话：</th>
	                        <td>${result.factory_tel }</td>
	                    </tr>
	                    <tr>
	                    	<th>主要联系人：</th>
	                    	<td>${result.contact_people}</td>
	                        <th>主要联系人手机：</th>
	                        <td>${result.contact_phone }</td>
	                    </tr>
	                    <tr>
	                    	<th>其他联系方式：</th>
	                    	<td>${result.contact_otherway}</td>
	                        <th>联系人邮箱：</th>
	                        <td>${result.contact_mail }</td>
	                    </tr>
	                    <tr>
	                        <th>主要经营范围：</th>
	                        <td colspan="3">${result.business_scope }</td>
	                    </tr>
	                    <tr>
	                    	<th>工商登记号码：</th>
	                    	<td>${result.gsdjh}</td>
	                        <th>纳税人识别号：</th>
	                        <td>${result.nrssbh }</td>
	                    </tr>
	                    <tr>
	                    	<th>社会信用代码：</th>
	                    	<td>${result.shxydm}</td>
	                        <th>企业官网：</th>
	                        <td>${result.official_website }</td>
	                    </tr>
	                    <tr>
	                    	<th>X坐标：</th>
	                    	<td>${result.longitude}</td>
	                        <th>Y坐标：</th>
	                        <td>${result.latitude }</td>
	                    </tr>
	                    <tr>
	                    	<th>创建时间：</th>
	                    	<td class="time">${result.create_timestemp}</td>
	                        <th>创建人：</th>
	                        <td>${result.creator_name }</td>
	                    </tr>
	                    <tr>
	                        <th>备注：</th>
	                        <td colspan="3">${result.reamrk1 }</td>
	                    </tr>
	                 </table>
	            </div>
	            <!-- 基本信息 end -->
	         </div>
         </div>
    </div>
    <!-- 底部按钮 -->
	<div class="list-btm">
	  	<div class="list-btm-div">
	        <div class="div-btn-sbt">
	        	<c:if test="${hidBack != 1 }">
	           		<button class="btm-button" type="button"  onclick="javascript:location.href='${backURL}'"><span class="back-icon">返回</span></button>
	        	</c:if>
	        </div>
	    </div>
	</div>
</body>
</html>