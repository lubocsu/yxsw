<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>作业票拟定</title>
<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--分离模式框架必需end-->
<!-- 日期选择框start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path}/libs/js/form/selectTree.js"></script>
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>

<style type="text/css">
	.task-audit-tableboder table tr td{
		border: 1px solid #99ccff;
		text-align: center;
	}
	.padding0{padding:0px !important;}
	.num_input{width: 100%;height: 100%;border: none; }
	.text_input{width: 99%;max-height: 100px; resize: none;border:none;}
	.text_input_ext{background-color: #f1f4ff;}
	.text_input_div{max-height: 100px; overflow-x:hidden;}
	.his {float: right; background: #009688; width: 20px; height: 20px; color: white; line-height: 1.8; margin-right: 5px;}
	textarea:hover {border:none !important;}
	textarea:focus {border:none !important;}
	input:hover {border:none !important;}
	input:focus {border:none !important;}
</style>
</head>
<body style="overflow: hidden;">
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	    	 	<!-- 工作票基本信息 start -->
	    	 	<form action="#" id="myFormId" method="post">
	         		<div class="task-detail">
		            	 <span class="detail-title">作业票基本信息</span>
		            	 <input type="hidden" name="cxMakeId" id="cxMakeId" value="${zyp.cxMakeId}"/>
		            	 <input type="hidden" name="updateTimestemp" id="updateTimestemp" value="${zyp.updateTimestemp}"/>
		                 <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		 </div>
		                 <table class="detail-list">
		                 	<tr>
		                 		<th>作业票日期：</th>
		                 		<td>
		                 			${zyp.zypDate }
								</td>
		                 		<th>作业票编号：</th>
		                        <td>
		                        	${zyp.zypCode }
		                        </td>
		                 	</tr>
		                    <tr>
		                        <th>工艺预警状态：</th>
		                        <td>
		                        	${zyp.warningName }
		                        </td>
		                        <th>作业票负责人：</th>
		                    	<td>
		                    		${zyp.fzrName }
		                    	</td>
		                    </tr>
		                    <tr>
		                        <th>作业票说明：</th>
		                        <td>
		                        	${zyp.zypDesc }
		                        </td>
		                        <th></th>
		                    	<td>
		                    	</td>
		                    </tr>
		                 </table>
	         		
		            </div>
		            <!-- 工作票基本信息 end -->
		            <!-- 作业票各个工艺段指标内容  start-->
		            <c:forEach items="${zyp.makeTmpList }" var="makeTmp">
		            	<div class="task-audit task-audit-tableboder makeTmp">
				            <span class="detail-title">${makeTmp.zxpTempItmName }</span>
				            <div class="to_hide_over_content" class="text">
				          		<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				       		</div> 
					        <table class="detail-list">
					        	<tr style="background-color: #c6e5fb;">
						        	<td style="color: #006699; width: 20%;">指标项</td>
						            <td style="color: #006699; width: 50%;">下达值</td>
						            <td style="color: #006699; width: 20%">浮动值</td>
					            </tr>
						        <c:forEach items="${makeTmp.makeTmpItemList }" var="makeTmpItem">
									<tr makeTmpItemId="${makeTmpItem.makeTmpItemId }" inputType="${makeTmpItem.tblx }" class="makeTmpItemTr">
										<td cxzbId="${makeTmpItem.cxzbId }">
											${makeTmpItem.cxzbName }
											<c:if test="${makeTmpItem.tblx eq NUM_INPUT_TYPE }">
												<span class="his" title="查看历史数据" onclick='showHis("${makeTmp.zxpTempItmId }","${makeTmpItem.cxzbId }")'>?</span>
											</c:if>
										</td>
									    <c:if test="${makeTmpItem.tblx eq TEXT_INPUT_TYPE }">
									    	<td class="padding0" title="${makeTmpItem.jlxdz }">
									    		<%-- <div style="max-height: 100px;overflow: auto;">${makeTmpItem.jlxdz }</div> --%>
									    		<div style="max-height: 100px;overflow: auto;"><textarea style="background: #f1f4ff !important;" class="text_input text_input_ext" disabled="disabled">${makeTmpItem.jlxdz }</textarea></div>
									    	</td>
									    	<td class="padding0" title="${makeTmpItem.jlxdzfd }">
									    	</td>
									    </c:if>
									    <c:if test="${makeTmpItem.tblx eq NUM_INPUT_TYPE }">
									    	<td class="padding0" >${makeTmpItem.jlxdz }</td>
									    	<td class="padding0" style="line-height: 30px;">
									    		<span style="float: left; width: 8%; font-size: medium; font-weight: bold; line-height: 30px;">±</span>${makeTmpItem.jlxdzfd }
									    	</td>
									    </c:if>
									</tr>    
						        </c:forEach>
					         </table>	
				       </div>
		            </c:forEach>	
	            <!-- 作业票各个工艺段指标内容  end-->
	            </form>
	         </div>
         </div>
    </div>
     <!-- 底部按钮 -->
	<!-- <div class="list-btm">
	  	<div class="list-btm-div">
	        <div class="div-btn-sbt">
	             <button class="btm-button" type="button"  onclick="goBack();"><span class="back-icon">返回</span></button>
	        </div>
	    </div>
	</div> -->
	<script type="text/javascript">
	
		jQuery(function(){
			
			jQuery(".mainCon td .selectbox").parent().css("width","auto");
		  	jQuery(".mainCon td .selBtn").css("height","auto");
		  	jQuery(".mainCon td .selBtn").css("width","11px");
		  	
		  	$("#fzrId").bind("change",function(){
		  		$("#fzrName").val($(this).attr("relText"));
		  	});

		});
	  
		var cxMakeId = "${zyp.cxMakeId}";
		// 保存
		function saveAndSubmit(saveType, goBack){
			// 获取所有上报值
			/* var makeTmp = jQuery(".makeTmp");
			var makeTmpList = new Array();
			jQuery.each(makeTmp, function(i, tmp){
				var makeTmpItemTrs = jQuery(tmp).find(".makeTmpItemTr");
				var makeTmpItemJSONArr = new Array();
				var makeTmpJson = {}
				makeTmpJson.zxpTempItmName = jQuery(tmp).find(".zxpTempItmName").val();
				makeTmpJson.ZxpTempItmId = jQuery(tmp).find(".ZxpTempItmId").val();
				makeTmpJson.zxpTempSort = jQuery(tmp).find(".zxpTempSort").val();
				makeTmpJson.jlxdz = jQuery(tmp).find(".jlxdz").val();
				makeTmpJson.jlsbz = jQuery(tmp).find(".jlsbz").val();
				makeTmpJson.jlqcl = jQuery(tmp).find(".jlqcl").val();
				jQuery.each(makeTmpItemTrs,function(i,n){
					var makeTmpItemJSON = {};
					//makeTmpItemJSON.makeTmpItemId = jQuery(n).attr("makeTmpItemId");
					var inputType = jQuery(n).attr("inputType");
					makeTmpItemJSON.cxzbName = jQuery(n).find("td:eq(0)").text();
					makeTmpItemJSON.cxzbId = jQuery(n).find("td:eq(0)").attr("cxzbId");
					if(inputType == "${TEXT_INPUT_TYPE}" ){
						makeTmpItemJSON.jlxdz = jQuery(n).find("td:eq(1)").find("textarea").val();
						//makeTmpItemJSON.jlqcl = "";
					}
					if(inputType == "${NUM_INPUT_TYPE}" ){
						makeTmpItemJSON.jlxdz = jQuery(n).find("td:eq(1)").find("input").val();
						makeTmpItemJSON.jlsbz = jQuery(n).find("td:eq(2)").find("input").val();;
					}
					makeTmpItemJSONArr.push(makeTmpItemJSON);
				});
				makeTmpJson.makeTmpItemList = makeTmpItemJSONArr;
				makeTmpList.push(makeTmpJson);
			}); */
			
			var makeTmpItemTrs = jQuery(".makeTmpItemTr");
			var makeTmpItemJSONArr = new Array();
			jQuery.each(makeTmpItemTrs,function(i,n){
				var makeTmpItemJSON = {};
				makeTmpItemJSON.makeTmpItemId = jQuery(n).attr("makeTmpItemId");
				var inputType = jQuery(n).attr("inputType");
				makeTmpItemJSON.cxzbId = jQuery(n).find("td:eq(0)").attr("cxzbId");
				if(inputType == "${TEXT_INPUT_TYPE}" ){
					makeTmpItemJSON.jlxdz = jQuery(n).find("td:eq(1)").find("textarea").val();
				}
				if(inputType == "${NUM_INPUT_TYPE}" ){
					makeTmpItemJSON.jlxdz = jQuery(n).find("td:eq(1)").find("input").val();
					makeTmpItemJSON.jlxdzfd = jQuery(n).find("td:eq(2)").find("input").val();;
				}
				makeTmpItemJSONArr.push(makeTmpItemJSON);
			});
			
			var param = {
					"makeTmpItemJSONArr":JSON.stringify(makeTmpItemJSONArr),
					"cxMakeId":jQuery("#cxMakeId").val(),
					"warning":jQuery("#warning").val(),
					"fzrId":jQuery("#fzrId").attr("relvalue"),
					"fzrName":jQuery("#fzrName").val(),
					"zypDesc":jQuery("#zypDesc").val(),
					"updateTimestemp":jQuery("#updateTimestemp").val(),
					"saveType":saveType
			}
			
			$.post("${path}/zypcxMake/doSaveEditMake",param,function(result){
				if(result.flag){
					Dialog.alert(result.message, function(){
						goBack();
					});
				}else{
					Dialog.alert(result.message);
				}
			});
			
		}
		
		//根据作业票日期生成作业票编号
		function dateChange(){
			var zypDate = $("#zypDate").val();
			var date = new Date(zypDate);
			var firstDay = new Date(date.getFullYear() + "-01-01 00:00:00");
			var day = Math.ceil((date.getTime() - firstDay.getTime())/(1000*3600*24));
			$("#zypCode").val(date.Format("yyyyMMdd") + "-" + day);
		}
		
		function showHis(tempItemId, zbxId){
			Dialog.open({
				URL:"${path}/zbhistory/init?tempitemid=" + tempItemId + "&zbxid=" + zbxId + "&wscid=" + "${wscid}",
				Title:"查看历史数据",Width:800, Height:600});
		}
	</script>
</body>
</html>