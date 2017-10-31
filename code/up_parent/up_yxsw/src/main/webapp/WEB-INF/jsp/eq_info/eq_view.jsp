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
			<form action="${path}/eqInfo/saveEditEq" id="form" method="post">
				<input type="hidden" name="backURL" value="${backURL }">
				<input type="hidden" name="sbId" value="${sbInfo.sbId }">
				<input type="hidden" name="updateTimestemp" value="${sbInfo.updateTimestemp }">
			    <div class="details-container">
		         	<div class="task-detail">
		            	 <span class="detail-title">设备信息</span>
		                 <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		 </div> 
		                 <table class="detail-list">
				    		<tr>
				    			<th>设备编号:</th>
				    			<td>
				    				${sbInfo.sbCode}
				    			</td>
				    			<th>财务编号:</th>
				    			<td>
				    				${sbInfo.sbFnCode}
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备名称:</th>
				    			<td>
				    				${sbInfo.sbName}
				    			</td>
				    			<th>设备简称:</th>
				    			<td>
				    				${sbInfo.sremark}
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备类别</th>
				    			<td>
				    				${sbInfo.sbSort }
				    			</td>
				    			<th>设备类型:</th>
				    			<td>
				    				${type.name }
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备制造商:</th>
				    			<td>
				    				${factory.name}
				    			</td>
				    			<th>国产进口：</th>
				    			<td>
				    				${sbInfo.gcjk }
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>是否在线采集仪：</th>
				    			<td>
				    				${sbInfo.isZxcjy }
				    			</td>
				    			<th>设备状态：</th>
				    			<td>
				    				${sbInfo.zyStatus }
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>设备型号:</th>
				    			<td>
				    				${sbInfo.sbxh}
				    			</td>
				    			<th>性能参数:</th>
				    			<td>
				    				${sbInfo.xncs}
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>结构原理:</th>
				    			<td>
				    				${sbInfo.jgyl}
				    			</td>
				    			<th>安装位置:</th>
				    			<td>
				    				${sbInfo.setAddress}
				    			</td>
				    		</tr>
		                    <tr>
		                    	<th>购入日期:</th>
				    			<td>
				    				${sbInfo.buyDate}
				    			</td>
				    			<th>是否必扫二维码：</th>
		                 		<td>
		                 			${sbInfo.byzd}
		                 		</td>
	                    	</tr>
	                    	<tr>
	                    		<th>备注:</th>
				    			<td colspan="3">
				    				${sbInfo.remark}
				    			</td>
	                    	</tr>
	                    	<c:if test="${!empty attachmentList }">
		                       <tr>
									<th>设备附件：</th>
		                        	<td colspan="3">
		                         		<table id="hisAttachmentList">
		                          			<c:forEach items="${attachmentList }" var="item">
		                           				<tr id="${item.attachmentId}">
		                            				<td style="width:20%;">
		                             					<a href="${ServerURL}${item.attachmentPath}" download="${item.oldAttachmentName}.${item.attachmentSuffix}" >${item.oldAttachmentName }.${item.attachmentSuffix}</a>
		                            				</td>
		                           				</tr>
		                          			</c:forEach>
		                         		</table>
		                        	</td>
		                    	</tr>
		                    </c:if>
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
				    				${sbInfo.startValue}
				    			</td>
				    			<th>最近评估现值:</th>
				    			<td>
				    				${sbInfo.zjpgValue}
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>开始使用日期:</th>
				    			<td>
				    				${sbInfo.startUseDate}
				    			</td>
				    			<th>最近评估时间:</th>
				    			<td>
				    				${sbInfo.zjpgSj}
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>最低使用年限:</th>
				    			<td>
				    				${sbInfo.canUseYear}
				    			</td>
				    			<th>已折旧年限:</th>
				    			<td>
				    				${sbInfo.haveLostYear}
				    			</td>
				    		</tr>
				    		<tr>
				    			<th>X_坐标:</th>
				    			<td>
				    				${sbInfo.longitude}
				    			</td>
				    			<th>Y_坐标:</th>
				    			<td>
				    				${sbInfo.latitude}
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
	                <button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	            </div>
	        </div>
	    </div>
    </div>
</body>
</html>