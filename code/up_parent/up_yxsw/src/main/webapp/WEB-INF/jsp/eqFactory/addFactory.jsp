<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增设备厂商</title>
<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--分离模式框架必需end-->
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<!--弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<script type="text/javascript">
	jQuery(function(){
		/***解决core中定义的td样式影响qui的td样式***/
		  jQuery(".mainCon td .selectbox").parent().css("width","auto");
		  jQuery(".mainCon td .selBtn").css("height","auto");
		  jQuery(".mainCon td .selBtn").css("width","11px");
		  
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
	
	function back(){
		location.href = "${path}/eqFactory/init";
	}
	function doAddFactory(){
		$('#myFormId').validationEngine();
		var valid = $("#myFormId").validationEngine({returnIsValid: true});
		if(valid){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:'${path}/eqFactory/doAddEquipmentFactory',
	            data:$('#myFormId').serialize(),// 你的formid
	            async: false,
	            error: function(request) {
	            	Dialog.alert("提交失败");
	            },
	            success: function(responseText, statusText, xhr, $form){
	            	Dialog.alert(responseText.message, function(){
	            		
	            		location.href = "${path}/eqFactory/init";
	                });
	            }
	        });
		}
	}
</script>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
			<form id="myFormId" action="" method="post" target="frmright">
	    	 <div class="details-container">
	         	<div class="task-detail">
	            	 <span class="detail-title">
	                	 新增设备厂商
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
		                 <table class="detail-list" >
		                 	<tr>
		                 		<th style="width:10%;"><span><em style="color:red;">*</em></span>厂商名称：</th>
		                 		<td><input class="validate[required,length[0,128]]" name="name" id="name" value="" type="text"  style="width:200px;"/></td>
		                 		<th>厂商法人代表：</th>
		                 		<td><input type="text" id="responsobility" name="responsobility" class="validate[length[0,32]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th>厂商性质：</th>
		                 		<td><select labelField="value" valueField="key" selWidth="200"  name="zjxz" id="zjxz" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${factory_Xz }"}'></select></td>
		                 		<th style="width:10%;">厂商类型：</th>
		                 		<td ><select labelField="value" valueField="key" selWidth="200"  name="type" id="type" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${factory_Type }"}'></select></td>
		                 	</tr>
		                 	<tr>
		                 		<th>厂商地址：</th>
		                 		<td><input type="text" id="address" name="address" class="validate[length[0,256]]" value=""  style="width:200px;"/></td>
		                 		
		                 		<th>企业联系电话：</th>
		                 		<td><input type="text" id="factoryTel" name="factoryTel" class="validate[custom[telephone],length[0,16]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th>主要联系人：</th>
		                 		<td><input type="text" id="contactPeople" name="contactPeople" class="validate[length[0,32]]" value=""  style="width:200px;"/></td>
		                 		<th>主要联系人手机：</th>
		                 		<td><input type="text" id="contactPhone" name="contactPhone" class="validate[custom[mobilephone],length[0,11]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th>其他联系方式：</th>
		                 		<td><input type="text" id="contactOtherway" name="contactOtherway" class="validate[length[0,512]]" value=""  style="width:200px;"/></td>
		                 		<th>联系人邮箱：</th>
		                 		<td><input type="text" id="contactMail" name="contactMail" class="validate[custom[email],length[0,256]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		
		                 		<th>工商登记号码：</th>
		                 		<td><input type="text" id="gsdjh" name="gsdjh" class="validate[length[0,16]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th>纳税人识别号：</th>
		                 		<td><input type="text" id="nrssbh" name="nrssbh" class="validate[length[0,16]]" value=""  style="width:200px;"/></td>
		                 		<th>社会信用代码：</th>
		                 		<td><input type="text" id="shxydm" name="shxydm" class="validate[length[0,16]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th>X坐标：</th>
		                 		<td><input type="text" id="longitude" name="longitude" class="validate[length[0,12]]" value=""  style="width:200px;"/></td>
		                 		<th>Y坐标：</th>
		                 		<td><input type="text" id="latitude" name="latitude" class="validate[length[0,12]]" value=""  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th>企业官网：</th>
		                 		<td><input type="text" id="officialWebsite" name="officialWebsite" class="validate[length[0,256]]" value=""  style="width:200px;"/></td>
		                 		<th><span><em style="color:red;">*</em></span>是否停用：</th>
		                 		<td><select labelField="value" class="validate[required]" valueField="key" selWidth="200"  name="outService" id="outService" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${out_Service }"}'></select></td>
		                 	</tr>
							<tr>
								<th>主要经营范围：</th>
								<td colspan="3">
									<textarea maxNum="650" name="businessScope" class="validate[length[0,650]]"   style="width:70%;height:110px;"/></textarea>
								</td>
							</tr>
		                 	<tr>
								<th>备注：</th>
								<td colspan="3">
									<textarea maxNum="1000" name="reamrk1" class="validate[length[0,1000]]"   style="width:70%;height:110px;"/></textarea>
								</td>
							</tr>
		                 </table>
	            	</div>
	         	</div>
			</form>
		</div>
		<!-- 底部按钮 -->
		<div class="list-btm">
	    	<div class="list-btm-div">
	            <div class="div-btn-sbt">
	            	<button class="btm-button" type="button"  onclick="doAddFactory();"><span class="ok-icon">保存</span></button>
	            	<button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	            </div>
	        </div>
	    </div>
     </div>
</body>
</html>