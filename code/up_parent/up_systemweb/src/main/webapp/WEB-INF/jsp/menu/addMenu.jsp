<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增菜单</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期控件end -->
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单异步提交start -->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
<style type="text/css">
.input_width{width: 173px;}
textarea {
	width: 494px !important;
	height: 70px !important;
}
</style>
</head>
<body>
	<form id="myFormId" action="${path }/menu/doAddMenu" method="post" target="frmright">
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="130">菜单名称：</td>
				<td width="201" >
					<input type="text" name="menuName" class="validate[required,length[0,100]] input_width"/><span class="star">*</span>
				</td>
				<td width="120">父级菜单：</td>
				<td width="216">
				  <div class="selectTree" selWidth="175" name="parentMenuId" id="parentMenuId" selectedValue="${menu.parentMenuId }"  url="${path }/menu/queryMenuTree?systemCode=${menu.systemCode}"></div>
				</td>
			</tr>
			<tr>
				<td >模块链接地址：</td>
				<td colspan="3"><input type="text" name="linkURL" id="linkURL" class="validate[length[0,900]]" style="width: 494px;" watermark="填写格式：/menu/addMenu"/></td>
			</tr>
			<tr>
			  <td>菜单功能类型：</td>
			  <td><select prompt="请选择" selwidth="175" onchange="onChangeFunctionType(this)"  name="functionType" selectedValue="2" class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.SYS_MENU_FUNC }" id="treeType"></select>
			    <span class="star">*</span>
			  </td>
			  <td>参　　数：</td>
			  <td><input type="text" name="param" class="validate[length[0,256]] input_width"/></td>
		  </tr>
			<tr>
				<td>菜单类型：</td>
				<td>
					<select prompt="请选择" selWidth="175" name="menuType" selectedValue="1" class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.MENU_TYPE }" id="treeType"></select>
					<span class="star">*</span>
				</td>
				<td>图标路径：</td>
				<td><input type="text" name="imagePath" class="validate[length[0,60]] input_width" /></td>
			</tr>
			<tr>
			  <td>菜单权限：</td>
			  <td><input type="hidden" name="permissionNo"  id="permissionNo"/>
			    <div class="selectTree" id="permissionId" selwidth="175" url="${path }/permission/findPermissionTree?systemCode=${menu.systemCode}"></div></td>
			  <td >是否有效：</td>
			  <td><input type="radio" id="radio-1" name="enabled" value="1" checked="checked" />
			    <label for="radio-1" class="hand">是</label>
			    <input type="radio" id="radio-2" name="enabled" value="0" />
			    <label for="radio-2" class="hand">否</label></td>
		  </tr>
			<tr>
				<td >所属系统：</td>
				<td >
					<input type="hidden" name="systemCode"  value="${menu.systemCode }" id="systemCode"/>
					<input type="text" value="${systemName.systemname }" id="systemName" class="input_width" disabled="disabled"/>
				</td>
				<td>排序号：</td>
				<td>
					<input type="hidden" name="isPublic" value="0" />
					<input type="text" name="orderNo" style="width:173px;" min="1" max="9999"  value="${maxOrderNo.maxorderno }" class="validate[custom[onlyNumber]] stepper"/>
				</td>
			</tr>
			<tr>
				<td>描述说明：</td>
				<td colspan="3" style="text-align: left;">
					<textarea name="description" maxNum="1300"></textarea>
				</td>
			</tr>
		</table>
	<div class="height_15"></div>
	</div>
	</form>
<!-- 异步提交start -->
<script type="text/javascript">
	function initComplete() {
		//表单提交
		$('#myFormId').submit(function() {
			//判断表单的客户端验证是否通过
			var valid = $('#myFormId').validationEngine({
				returnIsValid : true
			});
			if (valid) {
				$(this).ajaxSubmit({
					//表单提交成功后的回调
					success : function(responseText, statusText, xhr, $form) {
						parent.Dialog.alert(responseText.message, function() {
							closeWin(responseText.newNode);
						});
					}
				});
			}
			//阻止表单默认提交事件
			return false;
		});
	}
	
	// 提供给父页面调用提交表单
	function submitThisForm() {
		jQuery("#myFormId").submit();
	}
	//重置
	function closeWin(newNode) {
		//刷新数据
		top.frmright.refresh();
		top.frmright.addNewNode(newNode);
		//关闭窗口
		parent.Dialog.close();
	}
	
	/* //当选择的父级发生变化时，同时改变所属系统值
	jQuery("#parentMenuId").bind("change",function(){
	   var node=jQuery(this).data("selectedNode");
	   var systemcode = node.systemcode;
	 	jQuery.ajax({
	 		type:"POST",
	 		url :"${path}/menu/getSystemNameByAjax",
	 		dataType : "json",
	 		data:{
	 			systemCode:systemcode,
	 		},
	 		success : function(result){
	 			jQuery("#systemCode").val(systemcode);
	 			jQuery("#systemName").val(result.systemname);
			},
			error : function(a){
				parent.Dialog.alert("获取系统名称失败");
			}
	 	});
	}); */
	
	//当选择发生变化时，改变权限值
	jQuery("#permissionId").bind("change",function(){
		var node=jQuery(this).data("selectedNode");
		var permissionNo = node.permissionno;
		jQuery("#permissionNo").val(permissionNo);
	});

	//判断菜单功能类型
	function onChangeFunctionType(obj){
		var functionType = jQuery(obj).val();
		// ||"3"==functionType
		if("1"==functionType){
			jQuery("#linkURL").val("");
			jQuery("#linkURL").attr("disabled",true);
		}else{
			jQuery("#linkURL").attr("disabled",false);
		}
	}
</script>
<!-- 异步提交end -->	
</body>
</html>