<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>作业票详情</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<!-- 表单验证end -->
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
	.task-audit-tableboder table tr td{
		border: 1px solid #99ccff;
		text-align: center;
	}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	    	 	<!-- 工作票基本信息 start -->
	         	<div class="task-detail">
	            	 <span class="detail-title">工作票基本信息</span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list">
	                 	<tr>
	                 		<th>作业票日期：</th>
	                        <td>${zyp.zypDate }</td>
	                 		<th>工作票编号：</th>
	                 		<td>${zyp.zypCode }</td>
	                 	</tr>
	                    <tr>
	                        <th>工艺预警状态：</th>
	                 		<td>${zyp.warningName }</td>
	                        <th>作业票负责人：</th>
	                    	<td>${zyp.fzrName }</td>
	                    </tr>
	                    <tr>
	                    	<th>天气：</th>
	                        <td colspan="3">${zyp.weatherName}</td>
	                    </tr>
	                 </table>
	            </div>
	            <!-- 工作票基本信息 end -->
	            <!-- 作业票各个工艺段指标内容  start-->
	            <c:forEach items="${zyp.makeTmpList }" var="makeTmp">
	            	<div class="task-audit task-audit-tableboder">
			            <span class="detail-title">${makeTmp.zxpTempItmName }</span>
			            <div class="to_hide_over_content" class="text">
			          	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
			        </div> 
			        <table class="detail-list">
			        	<tr style="background-color: #c6e5fb;">
				        	<td style="color: #006699;">指标项</td>
				            <td style="color: #006699;">下达值</td>
				            <td style="color: #006699;">完成量</td>
				            <td style="color: #006699;">去除率</td>
			            </tr>
				        <c:forEach items="${makeTmp.makeTmpItemList }" var="makeTmpItem">
							<tr>
								<td>${makeTmpItem.cxzbName }</td>
							    <c:if test="${makeTmpItem.tblx eq TEXT_INPUT_TYPE }">
							    	<td title="${makeTmpItem.jlxdz }"><div style="max-height: 100px;overflow: auto;">${makeTmpItem.jlxdz }</div></td>
							    	<td title="${makeTmpItem.jlsbz }"><div style="max-height: 100px;overflow: auto;">${makeTmpItem.jlsbz }</div></td>
							        <td></td>       
							    </c:if>
							    <c:if test="${makeTmpItem.tblx eq NUM_INPUT_TYPE }">
							    	<td>${makeTmpItem.jlxdz }±${makeTmpItem.jlxdzfd }</td>
							    	<td>${makeTmpItem.jlsbz }</td>
							    	<td>${makeTmpItem.jlqcl }</td>       
							    </c:if>
							     	
							</tr>    
				        </c:forEach>
			         </table>	
			       </div>
	            </c:forEach>	
	            <!-- 作业票各个工艺段指标内容  end-->
	            <!-- 值班人员考核  start-->
	            <c:if test="${!empty zyp.makePersonKhList }">
		            	<div class="task-audit task-audit-tableboder">
				            <span class="detail-title">值班考核</span>
				            <div class="to_hide_over_content" class="text">
				          	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				        </div> 
				        <table class="detail-list">
				        	<tr style="background-color: #c6e5fb;">
					        	<td style="color: #006699;width: 10%">人员</td>
					            <td style="color: #006699;width: 60%">考核情况</td>
					            <td style="color: #006699;width: 10%">所扣分值</td>
					            <td style="color: #006699;width: 10%">确认</td>
				            </tr>
					        <c:forEach items="${zyp.makePersonKhList }" var="khPerson">
								<tr class="khPersonTr" bkhId="${khPerson.bkhId }">
									<td>${khPerson.bkhName }</td>
								    <td title="${khPerson.khDesc }">${khPerson.khDesc }</td>       
								    <td>${khPerson.khScore }</td>
								    <td>
								    	<c:if test="${khPerson.haveConfirm eq 1 }">已确认</c:if>
								    	<c:if test="${khPerson.haveConfirm eq 0 }">未确认</c:if>
								    </td> 	
								</tr>    
					        </c:forEach>
				         </table>	
				       </div>
	            </c:if>
	            <!-- 值班人员考核  end-->
	            <!-- 验收 start -->
	            <c:if test="${csOrgType eq '3'}">
		         	<div class="task-detail">
		            	 <span class="detail-title">作业票验收</span>
		                 <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		 </div> 
<%-- 		        		 <form id="myFormId" action="${path }/zypReceive/doReceiveZyp" method="post" target="frmright"> --%>
			                 <table class="detail-list">
			                 	<tr>
			                 		<th style="width:10%;"><span><em style="color:red;">*</em></span>是否通过：</th>
			                 		<td>
			                 			<input type="radio" class="radio_" id="pass" name="radio_" value="1" checked="checked"/><label for="pass" class="hand">通过</label>
		    							<input type="radio" class="radio_" id="noPass" name="radio_" value="0" /><label for="noPass" class="hand">不通过</label>
									</td>
			                 	</tr>
			                    <tr>
			                        <th><span class="points"><em style="color:red;">*</em></span>意见：</th>
			                        <td><textarea  maxNum="650"  class="content" name="content"  id="content" style="width:60%;height:110px;"/></textarea></td>
			                    </tr>
			                 </table>
<!-- 		                 </form> -->
		            </div>
	            </c:if>
	            <!-- 验收 end -->
	         </div>
         </div>
    </div>
    <script type="text/javascript">
	    jQuery(function(){
	    	var radioValue = $("input:radio[name=radio_]").filter("[checked]").val();
    		if(radioValue == "0"){
    			jQuery(".points").show();
    		}else{
    			jQuery(".points").hide();
    		}
    		
	    	jQuery(".radio_").on("click",function(){
	    		jQuery(".radio_:eq(0)").removeAttr("checked");
	    		jQuery(".radio_:eq(1)").removeAttr("checked");
	    		jQuery(this).attr("checked","checked");
	    		
	    		var radioValue = $("input:radio[name=radio_]").filter("[checked]").val();
	    		if(radioValue == "0"){
	    			jQuery(".points").show();
	    		}else{
	    			jQuery(".points").hide();
	    		}
	    	});
	    });
	    
	    function submit(url){
	    	var radioValue = $("input:radio[name=radio_]").filter("[checked]").val();
	    	var content = jQuery(".content").val();
			if(radioValue == "0" && content == ""){
				parent.Dialog.alert("意见不能为空！");
				return;
			}
	    		var param = {
						"radioValue": radioValue,
						"content":content,
						"cxMakeId":"${cxMakeId }",
						"updateTimestemp":"${zyp.updateTimestemp}"
				};
				$.ajax({
		            cache: true,
		            type: "POST",
		            url:'${path}/zypReceive/doReceiveZyp',
		            data:param,
		            async: false,
		            error: function(request) {
		            	parent.Dialog.alert("提交失败");
		            },
		            success: function(responseText, statusTsext, xhr, $form){
		            	parent.Dialog.alert(responseText.message, function(){
		            		if(responseText.message != "数据已发生变化，请刷新后重试!"){
		            			parent.location.href = url;
		            		}
		                });
		            }
		        }); 
	    }
    </script>
</body>
</html>