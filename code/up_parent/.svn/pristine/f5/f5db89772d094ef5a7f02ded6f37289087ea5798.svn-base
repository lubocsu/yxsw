<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增检查项定义</title>
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
<script type="text/javascript" src="${path }/js/freq_mgt/freq_add.js"></script>
<script type="text/javascript">

	var path = "${path}";
	var backURL = "${backURL}";
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true; 

	jQuery(function(){
		repairCss();
		/*隐藏展开功能*/
		/* jQuery(".to_hide_over_content").live("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).parent().find(".detail-list").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).parent().find(".detail-list").show();
			}
		}); */
		
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
                <form action="${path}/freq/saveFreq" id="form">
                	<input type="hidden" name="updateTimestemp" value="${freq.updateTimestemp }"/>
	         		<div class="task-detail">
		            	<span class="detail-title">任务信息</span>
		                <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		</div> 
	                 	<table class="detail-list">
		                 	<tr>
		                 		<th><span class="red">*</span>任务名称：</th>
				    			<td>
				    				${freq.freqSegmentName }
				    			</td>
		                 		<th><span class="red">*</span>所属班次：</th>
				    			<td>
									${freq.detailName }
								</td>
		                 	</tr>
		                    <tr>
		                        <th><span class="red">*</span>开始时间：</th>
		                        <td>
		                        	${freq.startTime }
		                        </td>
		                        <th><span class="red">*</span>结束时间：</th>
								<td>
									${freq.endTime }
								</td>
		                    </tr>
		                    <!-- <tr>
		                        <th>任务说明：</th>
				    			<td colspan="3">
				    				<textarea name="workGroupDesc" id="workGroupDesc" maxNum="500" style="width:82%;" ></textarea>
				    			</td>
		                    </tr> -->
		                 </table>
	            	</div>
	            	<!-- 关联设备设施 start -->
		            <div class="task-audit" id="freq_detail">
				    	<span class="detail-title">任务明细</span>
				        <div class="to_hide_over_content" class="text">
				        	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				        </div>
						<div class="detail-list">
							<div  class="basicTab" style="background-color: rgb(241, 244, 255);"  iframeMode="true" 
								data='{"list":[{"name":"巡检设备","url":"${path }/freq/toFreqEpListView?freqId=${freq.freqSegmentId }"},
						      			{"name":"巡检设施","url":"${path }/freq/toFreqSsListView?freqId=${freq.freqSegmentId }"}]}'>
							    <div style="height: 380px; border:none; border-top:solid 2px #5ba2fc;">
							        <IFRAME scrolling="no"  style="overflow: hidden;width:100%;height: 100%;" frameBorder=0 id=frmrightChild name=frmrightChild class="frmrightChild" onload="iframeHeight('frmrightChild')" allowTransparency="true"></IFRAME>
							    </div>
						    </div>
						</div>
						<!-- <div id="check_item_detail_tool">
								<a href="javascript:;" onclick="toAddCheckItemDetailDom();" style=" margin-left:10px;"><span class="icon_add">新增选项</span></a>
								<a href="javascript:;" onclick="toDelCheckItemDetailDom();"><span class="icon_delete">批量删除</span></a>
						</div>
						<table class="detail-list" id="check_item_detail_table">
				            <tr style="background-color: #c6e5fb;">
				            	<td style="width:2%;padding: 0px;"><input type='checkbox' onclick="func_check_item_detail(this);"/></td>
					           	<td style="width:10%;color: #006699;">序列号</td>
					           	<td style="width:22%;color: #006699;">编码</td>
					           	<td style="width:22%;color: #006699;">名称</td>
					           	<td style="width:22%;color: #006699;">是否异常</td>
					           	<td style="width:22%;color: #006699;">是否默认</td>
				         	</tr>
				         </table>	 -->
				    </div>
			     </form>
			    <!-- 关联设备设施 end -->
	         </div>
         </div>
    </div>
    <!-- 底部按钮 -->
	<div class="list-btm">
	  	<div class="list-btm-div">
	        <div class="div-btn-sbt">
	             <button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		 function back(){
			 location.href= "${backURL }";
		 }
	</script>
</body>
</html>