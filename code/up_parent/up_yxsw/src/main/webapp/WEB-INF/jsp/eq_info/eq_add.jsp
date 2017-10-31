<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增设备档案</title>
<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<!--分离模式框架必需end-->
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--树组件start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<!-- 表单验证start -->
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<!-- 表单验证end -->
<script type="text/javascript" src="${path}/libs/js/form/form.js"></script>
<!-- 异步上传控件start -->
<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
<script type="text/javascript" src="${path }/js/plug-in/customupload.js"></script>
<!-- 异步上传控件end -->
<script type="text/javascript" src="${path }/js/eq_info/eq_add.js"></script>
<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<script type="text/javascript">
	var path = "${path}";
	var backURL = "${backURL}";
</script>
<style type="text/css">
input, select,textarea,li,a,html,body {margin: 0px;padding: 0px;}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
			<form action="${path}/eqInfo/saveEqInfo" id="form" method="post">
				<input type="hidden" name="backURL" value="${backURL }">
				<input type="hidden" name="attachmentList" id="attachmentList" value="">
			    <div class="details-container">
		         	<div class="task-detail">
		            	 <span class="detail-title">设备信息</span>
		                 <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		 </div> 
		                 <table class="detail-list">
				    		<tr>
				    			<th><span><em style="color:red;">*</em></span>设备编号:</th>
				    			<td>
				    				<input type="text" name="sbCode" id="sbCode" value="${sb_code}" class="validate[required,length[0,25],ajax[${path }/eqInfo/validateEqCode|* 设备编号已存在]]"/>
				    			</td>
				    			<th><span><em style="color:red;">*</em></span>财务编号:</th>
				    			<td>
				    				<input type="text" name="sbFnCode" id="sbFnCode" value="${sb_fn_code}" class="validate[required,length[0,25]]"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th><span><em style="color:red;">*</em></span>设备名称:</th>
				    			<td>
				    				<input type="text" name="sbName" id="sbName" value="${sb_name}" class="validate[required,length[0,50]]"/>
				    			</td>
				    			<th>设备简称:</th>
				    			<td>
				    				<input type="text" name="sremark" id="sremark" value="${sremark}" class="validate[length[0,50]]"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备类别</th>
				    			<td>
				    				<select labelField="value" valueField="key" selWidth="143" name="sbSort" prompt="请选择" selectedValue="${sbSort }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000002"}'></select>
				    			</td>
				    			<th>设备类型:</th>
				    			<td>
				    				<div name="sbTypeId" class="selectTree" selectedvalue="${sb_type_id }" selWidth="143" boxHeight="200" url="${path }/equipmentType/getEqTypeTree?clickExpand=true"></div>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备制造商:</th>
				    			<td>
				    				<input type="text" name="manufactureName" id="manufactureName" value="${manufacture_name}" readonly="readonly"/>
				    				<input type="hidden" name="manufactureId" id="manufactureId" value="${manufacture_id}"/>
				    				<input type="button" onclick="selectManufacture();" value="选" class="small_button" />
		                            <input type="button" onclick="clearManufacture();" value="清" class="small_button" />
				    			</td>
				    			<th>国产进口：</th>
				    			<td>
				    				<select labelField="value" valueField="key" selWidth="143" name="gcjk" prompt="请选择" selectedValue="${gcjk }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000001"}'></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th><span><em style="color:red;">*</em></span>是否在线采集仪：</th>
				    			<td>
				    				<select labelField="value" valueField="key" class="validate[required]" selWidth="143" name="isZxcjy" prompt="请选择" selectedValue="${isZxcjy }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000006"}'></select>
				    			</td>
				    			<th><span><em style="color:red;">*</em></span>设备状态：</th>
				    			<td>
				    				<select labelField="value" valueField="key" class="validate[required]" selWidth="143" name="zyStatus" prompt="请选择" selectedValue="${zyStatus }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000005"}'></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备型号:</th>
				    			<td>
				    				<input type="text" name="sbxh" id="sbxh" value="${sbxh}" class="validate[length[0,100]]"/>
				    			</td>
				    			<th>性能参数:</th>
				    			<td>
				    				<input type="text" name="xncs" id="xncs" value="${xncs}" class="validate[length[0,200]]"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>结构原理:</th>
				    			<td>
				    				<input type="text" name="jgyl" id="jgyl" value="${jgyl}" class="validate[length[0,200]]"/>
				    			</td>
				    			<th>安装位置:</th>
				    			<td>
				    				<input type="text" name="setAddress" id="setAddress" value="${set_address}" class="validate[length[0,500]]"/>
				    			</td>
				    		</tr>
		                    <tr>
		                    	<th>购入日期:</th>
				    			<td>
				    				<input type="text" class="date" name="buyDate" id="buyDate"  dateFmt="yyyy-MM-dd" readonly="readonly" value="${buy_date}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				    			</td>
				    			<th>是否必扫二维码：</th>
		                 		<td>
		                 			<select labelField="value" valueField="key" selWidth="143" name="byzd" id="byzd" selectedValue="1" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000006"}' ></select>
		                 		</td>
	                    	</tr>
	                    	<tr>
	                    		<th>备注:</th>
				    			<td colspan="3">
				    				<textarea name="remark" id="remark" value="${remark}" maxnum="500"></textarea>
				    			</td>
	                    	</tr>
	                    	<tr>
	                    		<th>设备附件：</th>
	                    		<td colspan="3">
		                    		<span id="uploadBtn"></span>
		  							<div id="uploadList"></div>
		                    	</td>
	                    	</tr>
				    	</table>
			    	</div>
			    	<div class="task-detail">	
		    		 	<span class="detail-title">设备估值</span>
		                <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		</div>
		        		<table class="detail-list">
				    		<tr>
				    			<th>设备原值:</th>
				    			<td>
				    				<input type="text" name="startValue" id="startValue" value="${start_value}" class="validate[length[0,6],custom[onlyNumberDot2]]"/>
				    			</td>
				    			<th>最近评估现值:</th>
				    			<td>
				    				<input type="text" name="zjpgValue" id="zjpgValue" value="${zjpg_value}" class="validate[length[0,6],custom[onlyNumberDot2]]"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>开始使用日期:</th>
				    			<td>
				    				<input type="text" class="date" name="startUseDate" id="startUseDate"  dateFmt="yyyy-MM-dd" readonly="readonly" value="${start_use_date}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				    			</td>
				    			<th>最近评估时间:</th>
				    			<td>
				    				<input type="text" class="date" name="zjpgSj" id="zjpgSj"  dateFmt="yyyy-MM-dd" readonly="readonly" value="${zjpg_sj}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>最低使用年限:</th>
				    			<td>
				    				<input type="text" name="canUseYear" id="canUseYear" value="${can_use_year}" class="validate[length[0,10],custom[onlyNumber]]"/>
				    			</td>
				    			<th>已折旧年限:</th>
				    			<td>
				    				<input type="text" name="haveLostYear" id="haveLostYear" value="${have_lost_year}" class="validate[length[0,10],custom[onlyNumber]]"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>X_坐标:</th>
				    			<td>
				    				<input type="text" name="longitude" id="longitude" value="${longitude}" class="validate[length[0,100]]"/>
				    			</td>
				    			<th>Y_坐标:</th>
				    			<td>
				    				<input type="text" name="latitude" id="latitude" value="${latitude}" class="validate[length[0,100]]"/>
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
	            	<button class="btm-button" type="button"  onclick="submitForm();"><span class="ok-icon">保存</span></button>
	                <button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	            </div>
	        </div>
	    </div>
    </div>
</body>
</html>