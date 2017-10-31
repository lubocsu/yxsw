<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改工艺段</title>
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
	
	function doModifyTechnicsScope(){
		$('#myFormId').validationEngine();
		var valid = $("#myFormId").validationEngine({returnIsValid: true});
		if(valid){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:'${path}/technicsScope/doModifyTechnicsScope',
	            data:$('#myFormId').serialize(),// 你的formid
	            async: false,
	            error: function(request) {
	            	Dialog.alert("保存失败");
	            },
	            success: function(responseText, statusText, xhr, $form){
	            	parent.Dialog.alert(responseText.message, function(){
	            			parent.refresh("${bizTXjTechnicsScopeManage.technicsId }");
		                    //关闭窗口
		                    parent.Dialog.close();
		                    parent.jQuery("#detail-iframe").attr("src","${path}/technicsScope/showDetail?technicsId=${bizTXjTechnicsScopeManage.technicsId }");
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
	                	 修改工艺段
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
		                 <table class="detail-list" >
		                 	<tr>
		                 		<th>厂站：</th>
		                 		<td>
		                 			<input class="textbox" name="belongWscnAme" id="belongWscnAme" value="${bizTXjTechnicsScopeManage.belongWscnAme }" type="text" readonly="readonly" style="width:180px;"/>
									<input type="hidden" name="belongWscId" id="belongWscId" value="${bizTXjTechnicsScopeManage.belongWscId }" />
									<input type="hidden" name="createTimestemp" id="createTimestemp" value="${bizTXjTechnicsScopeManage.createTimestemp }" />
									<input type="hidden" name="creatorAccount" id="creatorAccount" value="${bizTXjTechnicsScopeManage.creatorAccount }" />
									<input type="hidden" name="creatorName" id="creatorName" value="${bizTXjTechnicsScopeManage.creatorName }" />
									<input type="hidden" name="delFlag" id="delFlag" value="${bizTXjTechnicsScopeManage.delFlag }" />
									<input type="hidden" name="technicsId" id="technicsId" value="${bizTXjTechnicsScopeManage.technicsId }" />
		                 		</td>
		                 		<th><span><em style="color:red;">*</em></span>工艺段名称：</th>
		                 		<td><input type="text" id="technicsName" name="technicsName" class="validate[required,length[0,85]]" value="${bizTXjTechnicsScopeManage.technicsName }"  style="width:180px;"/></td>
		                 	</tr>
		                 	<tr>
								<th>工艺段说明：</th>
								<td colspan="3">
									<textarea maxNum="300" name="technicsDesc" class="validate[length[0,300]]"  style="width:73%;height:110px;"/>${bizTXjTechnicsScopeManage.technicsDesc }</textarea>
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
	            	<button class="btm-button" type="button"  onclick="doModifyTechnicsScope();"><span class="ok-icon">保存</span></button>
	            </div>
	        </div>
	    </div>
     </div>
</body>
</html>