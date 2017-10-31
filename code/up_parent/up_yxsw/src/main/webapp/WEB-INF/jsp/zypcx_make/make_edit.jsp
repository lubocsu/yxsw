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
<script type="text/javascript" src="${path }/js/plug-in/stringutil.js"></script>
<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>

<style type="text/css">
	.task-audit-tableboder table tr td{
		border: 1px solid #99ccff;
		text-align: center;
	}
	.padding0{padding:0px !important;}
	.num_input{width: 100%;height: 100%;border: none; text-align: center;}
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
		                 		<th><span><em style="color:red;">*</em></span>作业票日期：</th>
		                 		<td>
		                 			<input type="text" class="date validate[required]" disabled="disabled" name="zypDate" id="zypDate" value="${zyp.zypDate }" style="width:253px" readonly="readonly" onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd', isShowClear:true, minDate:'%y-%M-%d',onpicked:dateChange})" />
								</td>
		                 		<th>作业票编号：</th>
		                        <td>
		                        	<input type="text" name="zypCode" id="zypCode" disabled="disabled" value="${zyp.zypCode }" readonly="readonly" style="width:253px" title="根据作业票日期自动生成">
		                        </td>
		                 	</tr>
		                    <tr>
		                        <th><span><em style="color:red;">*</em></span>工艺预警状态：</th>
		                        <td>
		                        	<select class="validate[required]" labelField="value" valueField="key" selWidth="253" name="warning" id="warning" prompt="请选择" selectedValue="${zyp.warning }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000018"}'></select>
		                        </td>
		                        <th><span><em style="color:red;">*</em></span>作业票负责人：</th>
		                    	<td>
		                    		<div prompt="请选择" class="selectTree validate[required]" name="fzrId" id="fzrId" selectedValue="${zyp.fzrId }" selWidth="253" url="${path}/zbPlan/getPeopleTree" exceptParent="true" allSelectable="true"> </div>
		                    		<input name="fzrName" id="fzrName" type="hidden" value="${zyp.fzrName }" />
		                    	</td>
		                    </tr>
		                    <tr>
		                        <th>作业票说明：</th>
		                        <td>
		                        	<textarea name="zypDesc" id="zypDesc" maxNum="500" style="width:82%;" >${zyp.zypDesc }</textarea>
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
									    		<div class="text_input_div"><textarea <c:if test="${ makeTmp.jlxdz eq 0}">disabled="disabled"</c:if>  class="text_input <c:if test="${ makeTmp.jlxdz eq 1}">validate[rq,length[0,1000]]</c:if>">${makeTmpItem.jlxdz }</textarea></div>
									    	</td>
									    	<td class="padding0" title="${makeTmpItem.jlxdzfd }">
									    		<div class="text_input_div"><textarea class="text_input text_input_ext"  disabled="disabled">${makeTmpItem.jlxdzfd }</textarea></div>
									    	</td>
									    </c:if>
									    <c:if test="${makeTmpItem.tblx eq NUM_INPUT_TYPE }">
									    	<td class="padding0" ><input <c:if test="${ makeTmp.jlxdz eq 0}">disabled="disabled"</c:if>  class="num_input <c:if test="${ makeTmp.jlxdz eq 1}">validate[rq,length[0,100],custom[onlyNumberDot2]]</c:if>" type="text" value="${makeTmpItem.jlxdz }"/></td>
									    	<td class="padding0" style="line-height: 30px;">
									    		<span style="float: left; width: 8%; font-size: medium; font-weight: bold;">±</span><input <c:if test="${ makeTmp.jlxdz eq 0}">disabled="disabled"</c:if>  class="num_input <c:if test="${ makeTmp.jlxdz eq 1}">validate[rq,length[0,6],custom[onlyNumberDot2]]</c:if>" type="text" value="${makeTmpItem.jlxdzfd }" style="float: right; width: 90%;"/>
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
			
			//保存 和 保存提交 区别验证
			if(saveType == 2){	//提交必填
				$(".text_input:enabled, .num_input:enabled").each(function(){
					$(this).attr("class",$(this).attr("class").replace("rq","required"));
				});
				$("#myFormId").validationEngine();
			}else{	//保存非必填
				$(".text_input:enabled, .num_input:enabled").each(function(){
					$(this).attr("class",$(this).attr("class").replace("required","rq"));
					$(this).removeClass("error-field");
				});
				$("#myFormId").validationEngine();
			}
			
			var valid = $("#myFormId").validationEngine({returnIsValid: true});
			if(!valid){
				return;
			}
			
			// 获取所有上报值
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
					//浮动值为空时不记录，避免后台转换出错
					if(strNVL(jQuery(n).find("td:eq(2)").find("input").val())){
						makeTmpItemJSON.jlxdzfd = jQuery(n).find("td:eq(2)").find("input").val();
					}
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