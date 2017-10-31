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
<script type="text/javascript" src="${path }/js/zyp_temp/zyptemp_item_add.js"></script>
<script type="text/javascript">

	var path = "${path}";
	var backURL = "${backURL}";
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true;
	
	var addItem = true;

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
                <form action="${path}/zypTemp/saveTempItem" id="form">
                	<input type="hidden" name="tempId" id="tempId" value="${tempId }"/>
                	<input type="hidden" name="zxpTempSort" id="zxpTempSort" value="${zxpTempSort }"/>
                	<%-- <input type="hidden" name="updateTimestemp" id="updateTimestemp" value="${zypTemplate.updateTimestemp }"/> --%>
	         		<div class="task-detail">
		            	<span class="detail-title">配置信息</span>
		                <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		</div> 
	                 	<table class="detail-list">
		                 	<tr>
		                 		<th><span class="red">*</span>指标组名称：</th>
				    			<td>
				    				<input type="text" name="zxpTempItmName" id="zxpTempItmName" class="validate[required,length[0,42]]" value="${zypTemplate.zxpTempItmName }" style="width: 253px;" />
				    			</td>
		                 		<th></th>
		                 		<td></td>
		                 	</tr>
		                 	<tr>
		                 		<th><span class="red">*</span>填写内容：</th>
				    			<td class="cb">
				    				<span>
					    				<input type="checkbox" name="jlxdz" id="jlxdz" checked="checked" value="1" style="" /><label for="jlxdz">记录下达值</label>
					    				<input type="checkbox" name="jlsbz" id="jlsbz" checked="checked" value="1" style="" /><label for="jlsbz">记录完成值</label>
					    				<input type="checkbox" name="jlqcl" id="jlqcl" value="1" style="" /><label for="jlqcl">记录去除率</label>
				    				</span>
				    			</td>
		                 	</tr>
		                    <tr>
		                        <th>说明：</th>
				    			<td colspan="3">
				    				<textarea name="confDesc" id="confDesc" maxNum="500" style="width:82%;" ></textarea>
				    			</td>
		                    </tr>
		                 </table>
	            	</div>
	            	<!-- 作业票配置内容指标项start -->
		            <!-- <div class="task-audit" id="freq_detail">
				    	<span class="detail-title">指标项</span>
				        <div class="to_hide_over_content" class="text">
				        	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				        </div>
						<div class="detail-list">
							<div id="zyptemp_item_detail"></div>
						</div>
				    </div> -->
			     </form>
			    <!-- 作业票配置内容指标项 end -->
	         </div>
         </div>
    </div>
</body>
</html>