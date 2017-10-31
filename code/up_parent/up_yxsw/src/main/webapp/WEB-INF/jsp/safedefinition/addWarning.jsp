<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增指标项</title>
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
<!-- 日期选择框start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
<!--弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 异步上传控件start -->
<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
<script type="text/javascript" src="${path }/js/plug-in/customupload.js"></script>
<script type="text/javascript">
var path = '${path }';
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
		location.href = "${path}/warning/init";
	}
	function doAddWarn(){
		var title = $("#title").val();
		var warningType=$("#warningType").val();
		var is_important=$("#is_important").val();
		
		var textarea=$("#textarea").val();
		/* if(!strIsNotNull(title)){
			Dialog.alert("请输入安全提醒标题！");
		} *//* else  if(!strIsNotNull(warningType)){
			
			Dialog.alert("请输入提醒类型");
		}else if(!strIsNotNull(is_important)){
			
			Dialog.alert("请输入是否重要提醒");
		}else if (!strIsNotNull(textarea)){
			
			Dialog.alert("请输入内容");
		} */  if (textarea.lenth>300){
			Dialog.alert("输入内容过多");
			
		} 
		var valid = $("#myFormId").validationEngine({returnIsValid: true});
		if(!valid){
			//Dialog.alert("输入重复咯");
			return;
		}
		else{
			
			var params = $('#myFormId').serialize();
			params = params+"&attachmentList="+ JSON.stringify(CustomUploadCallBackData_UseQUI);
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:'${path}/warning/doAddWarning',
	            data:params,// 你的formid $('#myFormId').serialize()+JSON.stringify(CustomUploadCallBackData_UseQUI)
	            async: false,
	            error: function(request) {
	            	Dialog.alert("提交失败");
	            },
	            success: function(responseText, statusText, xhr, $form){
	            	Dialog.alert(responseText.message, function(){

	            		location.href = "${path}/warning/init";
	                });
	            }
	        });
		}
	}
	function strIsNotNull(str){
		var flag = true;
		if(typeof str != "null"&& typeof str != "undefined"){
			if((str.replace(/(^s*)|(s*$)/g, "").length ==0)){
				flag =false;
			}
		}else{
			flag = false;
		}
		return flag;
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
	                	安全提醒定义
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
		                 <table class="detail-list" >
		                 	<tr>
				
				<th style="width:10%;"> <span><em style="color:red;">*</em></span>安全提醒标题：</th>
		                 	
		                 			<td><input class="validate[required,length[0,25]]" id="title" name="title" type="text"  style="width:200px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th> <span><em style="color:red;">*</em></span>提醒类型：</th>
		                 		<td><select  selWidth="200" class="validate[required]"  labelField="value"  name="warningType" id="warningType" prompt="请选择" selectedValue="${warningType }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000008"}'></select></td>
		                 		<th style="width:10%;"> <span><em style="color:red;">*</em></span>是否重要提醒：</th>
		                 		<td ><select  selWidth="200"  class="validate[required]"  labelField="value"  name="is_important"  id="is_important" prompt="请选择" selectedValue="${is_important }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000006"}'></select></td>
		                 	</tr>
		                 	<tr>
								<th> <span><em style="color:red;">*</em></span>安全提醒内容：</th>
								<td colspan="3">
									<textarea maxNum="300" name="content" class="validate[required,length[0,300]]" id="textarea" style="width:70%;height:110px;"/></textarea>
								</td>
							</tr>
							<tr>
							<th style="width:10%;">附件上传：</th>
			   
				<td>
					
						<span id="uploadBtn"></span>
						<div id="uploadList"></div>
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
	            	<button class="btm-button" type="button"  onclick="doAddWarn();"><span class="ok-icon">保存</span></button>
	            	<button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	            </div>
	        </div>
	    </div>
   
      
</body>
<script type="text/javascript">

	
	var upload1 = new CustomUpload();
	upload1.init("safeIndicative",new Date().getTime(),{
		"btn" : "uploadBtn",
		"listDiv" : "uploadList",
		"fileSize" : '2048000',
		"fileTypes" : '*.*'
	});

</script>
</html>