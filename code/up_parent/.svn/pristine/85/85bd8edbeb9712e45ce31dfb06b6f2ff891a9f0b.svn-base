<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>更新菜单</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">
jQuery(function(){
	 var func = '${menu.functionType}';
	    if('1' == func){
	    	jQuery("#linkURL").attr("disabled",true);
	    }
});
function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
            var old_menuName = jQuery("#old_menuName").val();
            var menuName = jQuery("#menuName").val();
            var valid = $('#myFormId').validationEngine({returnIsValid: true});
            if (valid) {
				$(this).ajaxSubmit({
					//表单提交成功后的回调
					success : function(responseText, statusText, xhr, $form) {
						parent.Dialog.alert(responseText.msg, function() {
							closeWin(old_menuName,menuName);
						});
					}
				});
			}
            //阻止表单默认提交事件
            return false; 
    });
}
//提供给父页面调用提交表单
function submitThisForm() {
	jQuery("#myFormId").submit();
}
//重置
function closeWin(old_menuName,menuName){
    //刷新数据
    top.frmright.refresh();
    if(old_menuName != menuName){
    	var menuId = jQuery("#menuId").val();
    	top.frmright.modifyNodeName(menuId,old_menuName,menuName);
    }else{
    	top.frmright.modifyNodeName(null,null,null);
    }
    //关闭窗口
    parent.Dialog.close();
}

//当选择的父级发生变化时，同时改变所属系统值
/* jQuery("#parentMenuId").live("change",function(){
   var node=jQuery(this).data("selectedNode");
   var systemcode = node.systemcode;
   console.log(systemcode);
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
// 验证修改父级菜单时是否是本身和子节点
function validateParentMenu(){
	// 又有父级菜单（选中下拉树节点的id）
	var id = jQuery("#menuId").val();
	var node =jQuery("#parentMenuId").data("selectedNode");
	var preId = node.id;
	var preParentId = node.parentId;
	if(id == preId || id == preParentId){
		return true;
	}
	return false;
}

//判断菜单功能类型
function onChangeFunctionType(obj){
	var functionType = jQuery(obj).val();
	// ||"3"==functionType
	if("1"==functionType){
		jQuery("#linkURL").val("");
		jQuery("#linkURL").attr("disabled",true);
	}else{
		var oldValue = jQuery("#oldLinkURL").val();
		jQuery("#linkURL").val(oldValue);
		jQuery("#linkURL").attr("disabled",false);
	}
}

//当选择发生变化时，改变权限值
jQuery("#permissionId").live("change",function(){
	var node=jQuery(this).data("selectedNode");
	var permissionNo = node.permissionno;
	console.log(permissionNo);
	jQuery("#permissionNo").val(permissionNo);
});
</script>
<style type="text/css">
.input_width{width: 173px;}
textarea {
	width: 494px !important;
	height: 70px !important;
}
</style>
</head>
<body>
	<form action="${path }/menu/doModifyMenu" method="post" id="myFormId" target="frmright">
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="130">菜单名称：</td>
				<td width="201" >
					<input type="hidden" name="menuId" id="menuId" value="${menu.menuId }" />
					<input type="hidden" id="old_menuName" value="${menu.menuName }"/>
					<input type="text" name="menuName" id="menuName" value="${menu.menuName }" class="validate[required,length[0,100]] input_width"/><span class="star">*</span>
				</td>
				<td width="120">父级菜单：</td>
				<td width="216">
				  <div selWidth="175" name="parentMenuId" id="parentMenuId" class="selectTree validate[functionCall[validateParentMenu|* 父级不能是本身或者子级节点]]"   selectedValue="${menu.parentMenuId }"  url="${path }/menu/queryMenuTree?systemCode=${menu.systemCode}"></div>
				</td>
			</tr>
			<tr>
				<td >模块链接地址：</td>
				<td colspan="3">
					<input type="hidden" id="oldLinkURL" value="${menu.linkURL }"/>
					<input type="text" name="linkURL" id="linkURL" class="validate[length[0,1020]]" value="${menu.linkURL }" style="width: 494px;"/>
				</td>
			</tr>
			<tr>
			  <td>菜单功能类型：</td>
			  <td>
			  	<select prompt="请选择" selwidth="175"  name="functionType" onchange="onChangeFunctionType(this)" selectedValue="${menu.functionType }" class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.SYS_MENU_FUNC }" id="treeType"></select><span class="star">*</span>
			  </td>
			  <td>参　　数：</td>
			  <td>
			  	<input type="text" name="param" class="validate[length[0,256]] input_width" value="${menu.param }"/>
			  </td>
		  </tr>
			<tr>
				<td>菜单类型：</td>
				<td>
					<select prompt="请选择" selWidth="175" name="menuType" selectedValue="${menu.menuType }" class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.MENU_TYPE }" id="treeType"></select>
					<span class="star">*</span>
				</td>
				<td>图标路径：</td>
				<td><input type="text" name="imagePath" class="validate[length[0,60]] input_width" value="${menu.imagePath }" /></td>
			</tr>
			<tr>
			  <td>菜单权限：</td>
			  <td>
			  <input type="hidden" name="permissionNo"  id="permissionNo" value="${menu.permissionNo }"/>
				<div class="selectTree" id="permissionId" selWidth="175" selectedValue="${permissionID }"  url="${path }/permission/findPermissionTree?systemCode=${menu.systemCode}"></div>
			  <td >是否有效：</td>
			  <td>
			  	<input type="radio" id="radio-1" name="enabled" value="1" <c:if test="${menu.enabled eq '1'}">checked="checked"</c:if> /><label for="radio-1" class="hand">是</label>
				<input type="radio" id="radio-2" name="enabled" value="0" <c:if test="${menu.enabled eq '0'}">checked="checked"</c:if> /><label for="radio-2" class="hand">否</label>
		  </tr>
			<tr>
				<td >所属系统：</td>
				<td >
					<input type="hidden" name="systemCode" value="${menu.systemCode }" id="systemCode" />
					<input type="text" value="${systemName}" id="systemName" disabled="disabled" class="input_width"/>
				</td>
				<td>排序号：</td>
				<td>
					<input type="hidden" name="isPublic" value="0" />
					<input type="text" name="orderNo" style="width:173px;" min="1" max="9999"  value="${menu.orderNo }" class="validate[custom[onlyNumber]] stepper"/>
				</td>
			</tr>
			<tr>
				<td>描述说明：</td>
				<td colspan="3" style="text-align: left;">
					<textarea name="description" maxNum="1300">${menu.description }</textarea>
				</td>
			</tr>
		</table>
	<div class="height_15"></div>
	</div>
</form>
</body>
</html>