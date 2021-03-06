<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改检查项定义</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!-- 表单验证start -->
<script type="text/javascript" src="${path}/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript">
	jQuery(function(){
		repairCss();
		/*隐藏展开功能*/
		jQuery(".to_hide_over_content").live("click",function(){
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
		});
		
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
	#check_item_detail_tool a{color:#2C4D79;}
	#check_item_detail_table tr td{border: 1px solid #abc1cc;text-align: center;}
	/****解决动态加载select标签的样式问题****/
	#checkItemDetal_div tr td{width: auto;}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	    	 	<!-- 指标项管理 start -->
	         	<div class="task-detail">
	            	 <span class="detail-title">检查项管理</span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <form action="#" id="checkItem_content">
	                 	<table class="detail-list">
		                 	<tr>
		                 		<th><span class="red">*</span>检查项名称：</th>
				    			<td>
				    				<input type="hidden" id="checkItemId" value="${checkItem.checkItemId }"/>
				    				<input type="text" name="checkItemName" id="checkItemName" value="${checkItem.checkItemName }" class="validate[required,length[0,42]]" value="" style="width: 253px;" />
				    			</td>
		                 		<th><span class="red">*</span>检查项编号：</th>
				    			<td>
				    				<input type="text" name="checkItemCode" id="checkItemCode" value="${checkItem.checkItemCode }" disabled="disabled" style="width: 253px;"/>
				    			</td>
		                 	</tr>
		                    <tr>
		                        <th><span class="red">*</span>检查项类型：</th>
								<td>
									<select labelField="value" valueField="key" selWidth="253" name="checkItemType" id="checkItemType" selectedValue="${checkItem.checkItemType }" disabled="disabled"  prompt="请选择" url="${path}/platform/getSupportData" params='{"parentNodeId":"${CHECKITEM_TYPE }"}' ></select>
								</td>
		                        <th><span class="red">*</span>输入类型：</th>
								<td>
									<select labelField="value" valueField="key"  selWidth="253" name="inputType" id="inputType" selectedValue="${checkItem.inputType }" disabled="disabled" prompt="请选择" url="${path}/platform/getSupportData" params='{"parentNodeId":"${CHECKITEM_INPUTTYPE }"}' ></select>
								</td>
		                    </tr>
		                    <tr id="checkItemKxx_group_tr" style="display: none;">
		                    	<th>选项组：</th>
								<td>
									<select labelField="name" valueField="id" id="checkItemKxx"  selWidth="253" prompt="请选择" url="${path}/checkitemfxx/getCheckItemKXX"></select>
								</td>
		                        <th> </th>
		                        <td></td>
		                    </tr>
		                    <tr>
		                        <th>检查项说明：</th>
				    			<td colspan="3">
				    				<textarea name="checkItemDesc" id="checkItemDesc"  maxNum="141" style="width:76%;" >${checkItem.checkItemDesc }</textarea>
				    			</td>
		                    </tr>
		                 </table>
	                 </form>
	            </div>
	            <!-- 指标项管理 end -->
	            <!-- 单选项 start -->
	            <c:if test="${!empty checkItemDetailList }">
	            	<div class="task-audit" id='checkItemDetal_div'>
	            </c:if>
	            <c:if test="${empty checkItemDetailList }">
	            	<div class="task-audit" id='checkItemDetal_div' style="display: none;">
	            </c:if>
			    	<span class="detail-title">选项明细</span>
			        <div class="to_hide_over_content" class="text">
			        	<p><span>隐藏</span><span>&nbsp;</span><span style="">∨</span></p>
			        </div>
					<div id="check_item_detail_tool">
							<a href="javascript:;" onclick="toAddCheckItemDetailDom();" style=" margin-left:10px;"><span class="icon_add">新增</span></a>
							<a href="javascript:;" onclick="toDelCheckItemDetailDom();"><span class="icon_delete">删除</span></a>
					</div>
					<form action="#" id="checkItemDetail_content">
						<table class="detail-list" id="check_item_detail_table">
				            <tr style="background-color: #c6e5fb;">
				            	<td style="width:2%;padding: 0px;"><input type='checkbox' onclick="func_check_item_detail(this);"/></td>
					           	<td style="width:10%;color: #006699;">序号</td>
					           	<td style="width:22%;color: #006699;">编码</td>
					           	<td style="width:22%;color: #006699;">名称</td>
					           	<td style="width:22%;color: #006699;">是否异常</td>
					           	<td style="width:22%;color: #006699;">是否默认</td>
				         	</tr>
				         	<c:if test="${!empty checkItemDetailList }">
				         		<c:forEach items="${checkItemDetailList }" var="detail">
				         			<tr class="check_item_detail_tr">
										<td style="padding:0px;"><input type="checkbox"/></td>
										<td style="padding:0px;"><input class="sel_sort" value="${detail.selSort }" disabled="disabled" type="text" style="width:99%;margin:0px;border:0px;"/></td>
										<td style="padding:0px;"><input class="sel_value validate[required,custom[notChinese],length[0,10]]" value="${detail.selValue }" type="text" style="width:99%;margin:0px;border:0px;"/></td>
										<td style="padding:0px;"><input class="sel_name validate[required,length[0,42]]" value="${detail.selName }" type="text" style="width:99%;margin:0px;border:0px;"/></td>
										<td style="padding:0px;"><select class="sfyc validate[required]" labelField="value" valueField="key" id="sfyc_${detail.selSort }" prompt="请选择" selectedValue="${detail.sfzc }" data='${CHECKITEM_SFYC_LIST }' relWidth="100%"></select></td>
										<td style="padding:0px;"><select class="sfmr validate[required]" labelField="value" valueField="key" id="sfmr_${detail.selSort }" prompt="请选择" selectedValue="${detail.sfmr }" data='${CHECKITEM_SFMR_LIST }'></select></td>
								   	</tr>
				         		</c:forEach>
				         	</c:if>
				         </table>	
					</form>
			    </div>
			    <!-- 单选项 end -->
	         </div>
         </div>
    </div>
    <!-- 底部按钮 -->
	<div class="list-btm">
	  	<div class="list-btm-div">
	        <div class="div-btn-sbt">
	        	 <button class="btm-button" type="button"  onclick="doModifyCheckItemDetail(this);"><span class="ok-icon">保存</span></button>
	             <button class="btm-button" type="button"  onclick="goBack();"><span class="back-icon">返回</span></button>
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
		var sel_sort = parseInt("${start_sort}");
		/**
		 **---------------以上是页面常量和参数，一下是业务操作--------------------------------------------------------
		 */
		jQuery(function(){
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
			// 如果检查项明细的序号大于1，则需要重置前几行明细的select样式
			if(sel_sort>1){
				var check_item_detail_table = jQuery("#check_item_detail_table");
				var sfyc_td_width = check_item_detail_table.find("tr:eq(0) td:eq(4)").width()-10;
				var sfmr_td_width = check_item_detail_table.find("tr:eq(0) td:eq(5)").width()-10;
				for (var i = 0; i < sel_sort; i++) {
					jQuery("#sfyc_"+i).prev().find(".selectbox").width(sfyc_td_width);
					jQuery("#sfyc_"+i).prev().find(".selectbox-wrapper").width(sfyc_td_width+24);
					jQuery("#sfmr_"+i).prev().find(".selectbox").width(sfmr_td_width);
					jQuery("#sfmr_"+i).prev().find(".selectbox-wrapper").width(sfmr_td_width+24);
				}
			}
		});
		
		// 添加检查项明细到数据库
		function doModifyCheckItemDetail(_this){
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
					Dialog.alert("请添加选项明细");
					return;
				}
				var valid2 = $("#checkItemDetail_content").validationEngine({returnIsValid: true});
				if(!valid2){
					return;
				}
				jQuery.each(checkItemDeatilTrs,function(i,n){
					var itemDetail = {};
// 					itemDetail.detailId = jQuery(n).find(".checkitemdetail_id").val(); // 修改明细为删除，新添加，不需要旧ID
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
			checkItem.checkItemId = jQuery("#checkItemId").val();
			checkItem.checkItemName = jQuery("#checkItemName").val();
			$.post(path+"/checkitem/doModify",{"checkItem":JSON.stringify(checkItem),"checkItemDetail":JSON.stringify(checkItemDeatilArr)},function(responseData){
				if(responseData==true){
					Dialog.confirm("检查项修改成功",function(){ goBack(); });
				}else{
					Dialog.alert("检查项修改失败");
				}
			});
		}
		
		// 重置明细选项
		function resetDetailContent(){
			var checkItemDeatilTrs = jQuery(".check_item_detail_tr");
			jQuery.each(checkItemDeatilTrs,function(i,n){
				jQuery(n).remove();
			});
			jQuery("#checkItemKxx_group_tr").hide();
			$("#checkItemKxx_group_tr select").attr("selectedValue","");
			$("#checkItemKxx_group_tr select").resetValue();
			jQuery("#checkItemDetal_div").hide();
		}
		
		// 添加检查项明细dom
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
						+"<td style='padding:0px;'><input class='sel_value validate[required,custom[notChinese],length[0,10]]' value='"+param.value+"' type='text' style='width:99%;margin:0px;border:0px;'/></td>"
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
		
		function goBack(){
		    window.location = path+"${backURL}?backParam="+encodeURIComponent('${backParam }');
		}
	</script>
</body>
</html>