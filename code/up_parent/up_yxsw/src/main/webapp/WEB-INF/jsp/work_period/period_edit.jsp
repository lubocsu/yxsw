<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>班次设置</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!-- 表单验证start -->
<script type="text/javascript" src="${path}/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid_o.js" type="text/javascript"></script>
<!-- 表单start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${path }/js/plug-in/stringutil.js"></script>
<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>
<script type="text/javascript" src="${path }/js/work_period/period_edit.js"></script>
<script type="text/javascript">

	var path = "${path}";
	var backURL = "${backURL}";
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true;
	
	var wpId = "${wp.workGroupId}";

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
                <form action="#" id="form">
	    	 		<!-- 指标项管理 start -->
	         		<div class="task-detail">
	         		
		        		<input type="hidden" name="workGroupId" id="workGroupId" value="${wp.workGroupId }"/>
		        		<input type="hidden" name="updateTimestemp" id="updateTimestemp" value="${wp.updateTimestemp }"/>
		            
		            	<span class="detail-title">班次设置</span>
		                <div class="to_hide_over_content" class="text">
		          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
		        		</div> 
	                 	<table class="detail-list">
		                 	<tr>
		                 		<th><span class="red">*</span>班次名称：</th>
				    			<td>
				    				<input type="text" name="workGroupName" id="workGroupName" class="validate[required,length[0,42]]" value="${wp.workGroupName }" style="width: 253px;" />
				    			</td>
		                 		<%-- <th><span class="red">*</span>是否启用：</th>
				    			<td>
									<select labelField="value" valueField="key" selWidth="253" name="checkItemType" id="checkItemType" class="validate[required]"  prompt="请选择" url="${path}/platform/getSupportData" params='{"parentNodeId":"${CHECKITEM_SFMR }"}' ></select>
								</td> --%>
		                 	</tr>
		                    <tr>
		                        <th><span class="red">*</span>开始时间：</th>
		                        <td>
		                        	<input type="text" class="date validate[required]" name="startTime" id="startTime"  value="${wp.startTime }"   style="width:253px" readonly="readonly" disabled="disabled"  onFocus="WdatePicker({dateFmt: 'HH:mm',isShowClear:true})" />
		                        </td>
		                        <th><span class="red">*</span>结束时间：</th>
								<td>
									<input type="text" class="date validate[required]" name="endTime" id="endTime"  value="${wp.endTime }"   style="width:253px" readonly="readonly" disabled="disabled" onFocus="WdatePicker({dateFmt: 'HH:mm',isShowClear:true})" />
								</td>
		                    </tr>
		                    <tr>
		                        <th>班次说明：</th>
				    			<td colspan="3">
				    				<textarea name="workGroupDesc" id="workGroupDesc" maxNum="300" style="width:82%;" >${wp.workGroupDesc }</textarea>
				    			</td>
		                    </tr>
		                 </table>
	            	</div>
	            	<!-- 指标项管理 end -->
	            	<!-- 单选项 start -->
		            <div class="task-audit">
				    	<span class="detail-title">班次明细<em style="font-style: normal;color: gray;font-size: 2px;">（请按照排班顺序添加）</em></span>
				        <div class="to_hide_over_content" class="text">
				        	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
				        </div>
				        <div class="padding_right5" style="height: 320px; margin-top: 30px;">
							<div id="wp_detail"></div>
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
			    <!-- 单选项 end -->
	         </div>
         </div>
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
	<script type="text/javascript">
		// 是否异常字典项，用于手动添加检查项明细内容
		var dict_sfyc_json = '${CHECKITEM_SFYC_LIST}';
		// 是否默认 字典项，用于手动添加检查项明细内容
		var dict_sfmr_json = '${CHECKITEM_SFMR_LIST}';
		// 检查项输入类型，单选类型
		var inputype_radio = "${CHECKITEM_INPUTTYPE_RADIO}";
		/**
		 **---------------以上是页面常量和参数，一下是业务操作--------------------------------------------------------
		 */
		jQuery(function(){
			// 显示关闭单选可选项组
			jQuery("#inputType").on("change",function(){
				var inputType = jQuery(this).val();
				if(inputType==inputype_radio){
					jQuery("#checkItemKxx_group_tr").show();
					jQuery("#checkItemDetal_div").show();
				}else{
					resetDetailContent();
					repairCss();
				}
			});
			// 预置 预定义的可选项
			jQuery("#checkItemKxx").on("change",function(){
				var id = jQuery(this).val();
				if(id.length==0){
					return;
				}
				$.post(path+"/checkitemselkxx/getCheckItemSelKxxByParentId",{"parentId":id},function(responseData){
					if(null==responseData||responseData == undefined ||responseData.length==0){
						Dialog.alert("没有可用的可选项");
					}else{
						for ( var i in responseData) {
							var param = {};
							param.value = responseData[i].value;
							param.name = responseData[i].name;
							toAddCheckItemDetailDom(param);
						}
					}
				});
			});
		});
		
		// 添加检查项明细到数据库
		function doAddCheckItemDetail(_this){
			$('#checkItem_content').validationEngine();
			var valid = $("#checkItem_content").validationEngine({returnIsValid: true});
			if(!valid){
				return;
			}
			// 若输入类型未单选，则检查项明细信息内容不能为空
			var inputType = jQuery("#inputType").val();
			var checkItemDeatilArr = new Array();
			if(inputType==inputype_radio){
				var checkItemDeatilTrs = jQuery(".check_item_detail_tr");
				if(checkItemDeatilTrs.length==0){
					Dialog.alert("请添加检查项明细");
					return;
				}
				var valid2 = $("#checkItemDetail_content").validationEngine({returnIsValid: true});
				if(!valid2){
					return;
				}
				jQuery.each(checkItemDeatilTrs,function(i,n){
					var itemDetail = {};
					itemDetail.selSort = jQuery(n).find(".sel_sort").val();
					itemDetail.selValue = jQuery(n).find(".sel_value").val();
					itemDetail.selName = jQuery(n).find(".sel_name").val();
					itemDetail.sfzc = jQuery(n).find(".sfyc").val();
					itemDetail.sfmr = jQuery(n).find(".sfmr").val();
					checkItemDeatilArr.push(itemDetail);
				});
			}
			// 检查项主要信息
			var checkItem = {};
			checkItem.checkItemDesc = jQuery("#checkItemDesc").val();
			if(checkItem.checkItemDesc.length>141){
				Dialog.alert("检查项说明内容不能超过141个字符，请修改后提交");
				return;
			}
			checkItem.checkItemName = jQuery("#checkItemName").val();
			checkItem.checkItemCode = jQuery("#checkItemCode").val();
			checkItem.checkItemType = jQuery("#checkItemType").val();
			checkItem.inputType = jQuery("#inputType").val();
			jQuery(_this).attr("disabled","disabled");
			$.post(path+"/checkitem/doAdd",{"checkItem":JSON.stringify(checkItem),"checkItemDetail":JSON.stringify(checkItemDeatilArr)},function(responseData){
				if(responseData==true){
					Dialog.confirm("检查项保存成功，是否留在新增页面？\n点击【取消】回到列表页面",
						function(){ resetAddContent();},
					 	function(){ goBack(); }
					);
				}else{
					Dialog.alert("保存检查项失败");
				}
				jQuery(_this).removeAttr("disabled");
			});
		}
		
		// 重置新增页面内容
		function resetAddContent(){
			jQuery("#checkItemName").val("");
			jQuery("#checkItemCode").val("");
			var inputType = jQuery("#inputType").val();
			$("#checkItem_content select").attr("selectedValue","");
			$("#checkItem_content select").resetValue();
			jQuery("#checkItemDesc").val("");
			if(inputType==inputype_radio){
				resetDetailContent();
			}
			// 重置后修复css
			repairCss();
		}
		// 重置明细选项
		function resetDetailContent(){
			var checkItemDeatilTrs = jQuery(".check_item_detail_tr");
			jQuery.each(checkItemDeatilTrs,function(i,n){
				jQuery(n).remove();
				--sel_sort;
			});
			jQuery("#checkItemKxx_group_tr").hide();
			$("#checkItemKxx_group_tr select").attr("selectedValue","");
			$("#checkItemKxx_group_tr select").resetValue();
			jQuery("#checkItemDetal_div").hide();
		}
		
		// 添加检查项明细dom
		var sel_sort = 1;
		function toAddCheckItemDetailDom(param){
			if(param==null){
				param = {};param.value ="";param.name = "";
			}
			var check_item_detail_table = jQuery("#check_item_detail_table");
			var sfyc_td_width = check_item_detail_table.find("tr:eq(0) td:eq(4)").width()-10;
			var sfmr_td_width = check_item_detail_table.find("tr:eq(0) td:eq(5)").width()-10;
			var html = "<tr class='check_item_detail_tr'>"
						+"<td style='padding:0px;'><input type='checkbox'/></td>"
						+"<td style='padding:0px;'><input class='sel_sort' value='"+(sel_sort++)+"' disabled='disabled' type='text' style='width:99%;margin:0px;border:0px;'/></td>"
						+"<td style='padding:0px;'><input class='sel_value validate[required,length[0,10]]' value='"+param.value+"' type='text' style='width:99%;margin:0px;border:0px;'/></td>"
						+"<td style='padding:0px;'><input class='sel_name validate[required,length[0,42]]' value='"+param.name+"' type='text' style='width:99%;margin:0px;border:0px;'/></td>"
						+"<td style='padding:0px;'><select class='sfyc validate[required]' labelField='value' valueField='key' id='sfyc_"+sel_sort+"' prompt='请选择' data='"+dict_sfyc_json+"'></select></td>"
						+"<td style='padding:0px;'><select class='sfmr validate[required]' labelField='value' valueField='key' id='sfmr_"+sel_sort+"' prompt='请选择' data='"+dict_sfmr_json+"'></select></td>"
					   +"</tr>";
			check_item_detail_table.append(html);
			jQuery("#sfyc_"+sel_sort).render();
			jQuery("#sfmr_"+sel_sort).render();
			// 重置后修复css
			repairCss();
			jQuery("#sfyc_"+sel_sort).prev().find(".selectbox").width(sfyc_td_width);
			jQuery("#sfyc_"+sel_sort).prev().find(".selectbox-wrapper").width(sfyc_td_width+24);
			jQuery("#sfmr_"+sel_sort).prev().find(".selectbox").width(sfmr_td_width);
			jQuery("#sfmr_"+sel_sort).prev().find(".selectbox-wrapper").width(sfmr_td_width+24);
			// 加载完后运行表单引擎
			$('#checkItemDetail_content').validationEngine();
		}
		// 删除已添加 的检查项明细行
		function toDelCheckItemDetailDom(){
			var checkboxs = jQuery(".check_item_detail_tr input:checkbox:checked");
			jQuery.each(checkboxs,function(i,n){
				jQuery(n).parent().parent().remove();
				--sel_sort;
			});
			// 重置序号
			var checkItemDeatilTrs = jQuery(".check_item_detail_tr");
			jQuery.each(checkItemDeatilTrs,function(i,n){
				jQuery(n).find(".sel_sort").val(++i);
			});
			// 重置全选按钮
			jQuery("#check_item_detail_table tr:eq(0) input:checkbox").attr("checked",false);
		}
		
		// 全选（反选）所有已添加的检查项明细行
		function func_check_item_detail(_this){
			var isChecked = jQuery(_this).is(':checked');
			var checkboxs = jQuery(".check_item_detail_tr input:checkbox");
			if(isChecked){
				jQuery.each(checkboxs,function(i,n){
					jQuery(n).attr("checked",true);
				});
			}else{
				jQuery.each(checkboxs,function(i,n){
					jQuery(n).attr("checked",false);
				});
			}
		}
	</script>
</body>
</html>