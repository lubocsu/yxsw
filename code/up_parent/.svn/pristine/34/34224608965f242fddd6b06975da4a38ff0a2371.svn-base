<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增设备类型</title>
<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--分离模式框架必需end-->
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<!--弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->
<script type="text/javascript">
	jQuery(function(){
		/***解决core中定义的td样式影响qui的td样式***/
		  jQuery(".mainCon td .selectbox").parent().css("width","auto");
		  jQuery(".mainCon td .selBtn").css("height","auto");
		  jQuery(".mainCon td .selBtn").css("width","11px");
		jQuery(".to_hide_over_content").on("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).next(".detail-list").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).next(".detail-list").show();
			}
		});
	});
	
	function selectpraent(){
		
		Dialog.open({
			  ID:"c1",
		      InnerHtml: "",
		      Title:"选择父类型",
		      Width:340,
		      Height:400,
		});
		var $container=$(document.getElementById("_Container_c1"));
		$("#_Container_c1").css("overflow","auto");
	    var $tree=$(' <ul id="tree" class="ztree"></ul>');
	    var treeSetting = {
				view: {
					dblClickExpand: true
				},
				callback: {
					onClick: treeClick,
					beforeClick:zTreeBeforeClick
				},
		};
		
		jQuery.ajax({
			type : 'POST',
			url : '${path}/equipmentType/getEquipmentTypeTree',
			dataType : 'json',
			success : function(nodes){
			   var treeObj =  $.fn.zTree.init($tree, treeSetting, nodes);
			    $container.append($tree)
// 				treeObj.expandAll(true);
			},
			error : function(a){
				Dialog.open("获取设备类型失败");
			}
		});
	}
	
	function treeClick(event,treeId,treeNode){
		var id = treeNode.id;
		var name = treeNode.name;
		jQuery("#parentId").val(id);
		jQuery("#parentName").val(name);
		
		Dialog.close();
	}
	
	// 判断是否具备点击事件
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
		var id = treeNode.id;
		var outService = treeNode.out_service;
		var flag = true;
		if(outService == "1"){
			flag = false;
		}
		if(flag == false){
			parent.Dialog.alert("该类型已停用，不能选做父类型！");
		}
		return flag;
	}
	
	function clearParent(){
		jQuery("#parentId").val("");
		jQuery("#parentName").val("");
	}
	
	function doAddMaterial(){
		$('#myFormId').validationEngine();
		var valid = $("#myFormId").validationEngine({returnIsValid: true});
		if(valid){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:'${path}/equipmentType/doAddEquipmentType',
	            data:$('#myFormId').serialize(),// 你的formid
	            async: false,
	            error: function(request) {
	            	parent.Dialog.alert("提交失败");
	            },
	            success: function(responseText, statusText, xhr, $form){
	            	parent.Dialog.alert(responseText.message, function(){
	            		if(responseText.message != "该类型名称或者类型编码已存在！"){
		            		var equipmentTypeId = responseText.equipmentTypeId;
		                	//刷新数据
	// 	                	parent.refresh(jQuery("#parentId").val());
		                	parent.refresh(equipmentTypeId);
		                    //关闭窗口
		                    parent.Dialog.close();
		                    parent.jQuery("#equipment-detail-iframe").attr("src","${path}/equipmentType/showDetail?sbTypeId="+ equipmentTypeId + "&parentName=" + $("#parentName").val() );
	            		}else{
	            			 Dialog.close();
	            		}
	                });
	            }
	        });
		}
	}
	function strIsNotNull(str){
		var flag = true;
		if(typeof str != "null"&& typeof str != "undefined"){
			if((str.replace(/(^s*)|(s*$)/g, "").length ==0)){
				flag =false;
			}
		}else{
			flag = false;
		}
		return flag;
	}
</script>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
			<form id="myFormId" action="" method="post" target="frmright">
	    	 <div class="details-container">
	         	<div class="task-detail">
	            	 <span class="detail-title">
	                	 新增设备类型
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
		                 <table class="detail-list" >
		                 	<tr>
		                 		<th>父类型：</th>
		                 		<td>
		                 			<input class="textbox" name="parentName" id="parentName" value="${equipmentType.name }" type="text" readonly="readonly" style="width:180px;"/>
									<input type="hidden" name="parentId" id="parentId" value="${equipmentType.id }" />
									<input type="button" onclick="selectpraent();" value="选" class="small_button" />
		                            <input type="button" onclick="clearParent();" value="清" class="small_button" />
		                 		</td>
		                 		<th><span><em style="color:red;">*</em></span>类型名称：</th>
		                 		<td><input type="text" id="name" name="name" class="validate[required,length[0,85]]" value=""  style="width:180px;"/></td>
		                 	</tr>
		                 	<tr>
		                 		<th><span><em style="color:red;">*</em></span>类型编码：</th>
		                 		<td><input type="text" id="code" name="code" class="validate[custom[notChinese],required,length[0,10]]" value=""  style="width:180px;"/></td>
		                 		<th>计量单位：</th>
		                 		<td style="width:18%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="unit" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${unit }"}'></select></td>
<!-- 		                 		<td><input type="text" id ="unit" name="unit" class="validate[length[0,3]]" value=""  style="width:180px;"/></td> -->
		                 	</tr>
		                 	<tr>
		                 		<th>排序号：</th>
		                 		<td><input type="text"  id= "orders" name="orders" inputMode="numberOnly" class="validate[length[0,4]]" value=""  style="width:180px;"/></td>
		                 		<th><span><em style="color:red;">*</em></span>是否停用：</th>
								<td style="width:18%;text-align:left"><select labelField="value" valueField="key" selWidth="200" class="validate[required]" name="isStopUse" id="isStopUse" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${isStopUse }"}'></select></td>
		                 	</tr>
		                 	<tr>
								<th>类型说明：</th>
								<td colspan="3">
									<textarea maxNum="300" name="remark" class="validate[length[0,300]]" value=""  style="width:50%;height:110px;"/></textarea>
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
	            	<button class="btm-button" type="button"  onclick="doAddMaterial();"><span class="ok-icon">保存</span></button>
<%-- 	                <button class="btm-button" type="button"  onclick="javascript:location.href='${path }/order/init'"><span class="back-icon">返回</span></button> --%>
	            </div>
	        </div>
	    </div>
     </div>
</body>
</html>