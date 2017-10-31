<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>作业票配置管理</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!-- 表单验证start -->
<script type="text/javascript" src="${path}/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid_o.js" type="text/javascript"></script>
<!--基本选项卡start-->
<script type="text/javascript" src="${path }/libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->
<!-- 表单start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>
<script type="text/javascript" src="${path }/js/plug-in/stringutil.js"></script>
<script type="text/javascript" src="${path }/js/zyp_temp/zyptemp_edit.js"></script>
<script type="text/javascript">

	var path = "${path}";
	var backURL = "${backURL}";
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true;
	
	var tempId = "${zypTemplate.tempId }";
	var updateTimestemp = "${zypTemplate.updateTimestemp }";

	jQuery(function(){
		repairCss();
	});
	
	function repairCss(){
		/***解决core中定义的td样式影响qui的td样式***/
		jQuery(".mainCon td .selectbox").parent().css("width","auto");
		jQuery(".mainCon td .selBtn").css("height","auto");
		jQuery(".mainCon td .selBtn").css("width","11px");
	}
</script>
<style type="text/css">
	#check_item_detail_tool{
		float: left; height: 29px;margin-bottom: -6px;width: 100%; 
		background: linear-gradient(180deg, rgba(198, 229, 251, 1) 0%, rgba(198, 229, 251, 1) 0%, rgba(137, 192, 254, 1) 100%, rgba(137, 192, 254, 1) 100%);
	}
	#check_item_detail_tool a{color:#999;}
	#check_item_detail_table tr td{border: 1px solid #abc1cc;text-align: center;}
	/****解决动态加载select标签的样式问题****/
	#checkItemDetal_div tr td{width: auto;}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
                <form action="${path}/zypTemp/saveEditZypTemp" id="form">
                	<input type="hidden" name="tempId" id="tempId" value="${zypTemplate.tempId }"/>
                	<input type="hidden" name="updateTimestemp" id="updateTimestemp" value="${zypTemplate.updateTimestemp }"/>
                	<input type="hidden" name="submitType" id="submitType" value="0"/>
	         		<div class="task-detail">
		            	<span class="detail-title">作业票配置信息</span>
		                <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		</div> 
	                 	<table class="detail-list">
		                 	<tr>
		                 		<th><span class="red">*</span>配置名称：</th>
				    			<td>
				    				<input type="text" name="tempName" id="tempName" class="validate[required,length[0,42]]" value="${zypTemplate.tempName }" style="width: 253px;" />
				    			</td>
		                 		<th></th>
		                 		<td></td>
		                 	</tr>
		                 	<tr>
		                 		<th>所属单位：</th>
		                 		<td>${zypTemplate.belongWscName }</td>
		                 		<th>配置版本：</th>
				    			<td>
				    				${zypTemplate.tempVersion }
								</td>
		                 	</tr>
		                    <tr>
		                        <th>配置说明：</th>
				    			<td colspan="3">
				    				<textarea name="tempDesc" id="tempDesc" maxNum="500" style="width:82%;" >${zypTemplate.tempDesc }</textarea>
				    			</td>
		                    </tr>
		                 </table>
	            	</div>
	            	<!-- 作业票配置内容start -->
		            <div class="task-audit" id="freq_detail">
				    	<span class="detail-title">作业票配置内容</span>
				        <div class="to_hide_over_content" class="text">
				        	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				        </div>
						<div class="padding_right5" style="margin-top: 30px;">
							<div id="zyptemp_detail"></div>
						</div>
				    </div>
			     </form>
			    <!-- 作业票配置内容 end -->
	         </div>
         </div>
    </div>
    <!-- 底部按钮 -->
	<div class="list-btm">
	  	<div class="list-btm-div">
	        <div class="div-btn-sbt">
	        	 <button class="btm-button" type="button"  onclick="submitForm(0);"><span class="ok-icon">保存</span></button>
	        	 <c:if test="${yy}">
		        	 <button class="btm-button" type="button"  onclick="submitForm(1);"><span class="ok-icon">保存并应用</span></button>
	        	 </c:if>
	             <button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
	</script>
</body>
</html>