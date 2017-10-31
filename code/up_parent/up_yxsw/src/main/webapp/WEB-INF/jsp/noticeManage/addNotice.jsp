<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增公告</title>
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
<!-- 异步上传控件end -->
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
		location.href = "${path}/noticeManage/init";
	}
	
	function doAddNotice(){
		$('#myFormId').validationEngine();
		var valid = $("#myFormId").validationEngine({returnIsValid: true});
		if(valid){
			var content = getContent();
			if(!strIsNotNull(content)){
				Dialog.alert("请输入公告内容！");
			}else{
				$("#content").val(content);
				var params = $('#myFormId').serialize();
				params = params+"&attachmentList="+ JSON.stringify(CustomUploadCallBackData_UseQUI);
// 				params.attachmentList = JSON.stringify(CustomUploadCallBackData_UseQUI);
				$.ajax({
		            cache: true,
		            type: "POST",
		            url:'${path}/noticeManage/doAddNotice',
		            data:params,// 你的formid
		            async: false,
		            error: function(request) {
		            	Dialog.alert("提交失败");
		            },
		            success: function(responseText, statusText, xhr, $form){
		            	Dialog.alert(responseText.message, function(){
		            		location.href = "${path}/noticeManage/init";
		                });
		            }
		        });
			}
		}else{
			return;
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
<style type="text/css">
        .edui-editor.edui-default{z-index:0 !important;}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
			<form id="myFormId" action="" method="post" target="frmright">
	    	 <div class="details-container">
	         	<div class="task-detail">
	            	 <span class="detail-title">
	                	 新增公告
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
		                 <table class="detail-list" >
		                 	<tr>
		                 		<th style="width:10%;"><span><em style="color:red;">*</em></span>标题：</th>
		                 		<td colspan="3"><input class="validate[required,length[0,40]]" name="title" id="title" value="" type="text"  style="width:72%;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th ><span><em style="color:red;">*</em></span>重要程度：</th>
		                 		<td ><select labelField="value" valueField="key" selWidth="200"  class="validate[required]" name="importantLevel" id="importantLevel" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${important_level }"}'></select></td>
		                 		<th ><span><em style="color:red;">*</em></span>公告类型：</th>
		                 		<td ><select labelField="value" valueField="key" selWidth="200"  class="validate[required]" name="ggType" id="ggType" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${gg_type }"}'></select></td>
		                 	</tr>
		                 	<tr>
		                 		<th><span><em style="color:red;">*</em></span>是否有效：</th>
		                 		<td ><select labelField="value" valueField="key" selWidth="200"  class="validate[required]" name="isAlive" id="isAlive" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${is_alive }"}'></select></td>
		                 		<th><span><em style="color:red;">*</em></span>有效期：</th>
		                 		<td><input type="text" class="date validate[required]" name="limitDate" id="limitDate"  value=""   style="width:200px" readonly="readonly"  onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',isShowClear:true,minDate:'%y-%M-%d'})" /></td>
		                 	</tr>
		                 	<tr>
		                 		<th style="width:10%;">附件上传：</th>
		                 		<td >
			                 		<div>
										<span id="uploadBtn">上传</span>
	  									<div id="uploadList"></div>
			                 		</div>
		                 		</td>
		                 	</tr>
		                 	<tr>
								<th><span><em style="color:red;">*</em></span>内容：</th>
								<td colspan="3">
									<div>
										<input type="hidden"  value="" name="content" id="content"/>
									    <script id="editor"  type="text/plain" style="width:85%;height:500px;"></script>
									</div>
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
	            	<button class="btm-button" type="button"  onclick="doAddNotice();"><span class="ok-icon">保存</span></button>
	            	<button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	            </div>
	        </div>
	    </div>
     </div>
     <script type="text/javascript">
     	//实例化编辑器
    	 var ue = UE.getEditor('editor');
     	//获取编辑内容
     	function getContent() {
     		var content = UE.getEditor('editor').getContent();
     		return content;
    	}
     	var upload1 = new CustomUpload();
		upload1.init("notice",new Date().getTime(),{
			"btn" : "uploadBtn",
			"listDiv" : "uploadList",
			"fileSize" : '2048000',
			"fileTypes" : '*.*'
		});
     </script>
</body>
</html>