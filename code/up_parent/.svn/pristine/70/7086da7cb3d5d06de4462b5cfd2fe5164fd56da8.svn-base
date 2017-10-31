<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增权限</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!-- 树组件start -->
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<!-- 树组件end -->

<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<!-- 树形下拉框end -->

<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->

<!-- 表单异步提交start -->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<!-- 表单异步提交end -->
<!--树组件插件start -->

<script type="text/javascript" src="${path }/libs/js/tree/ztree/exhide.js"></script>
<!--树组件插件end -->
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
</head>
<body>
	<form id="myFormId" action="${path }/permission/addPermission" method="post" target="frmright">
	<input type="hidden" name="systemCode" value="${systemCode }" />
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="130">归属系统：</td>
				<td width="201" >
					<input type="text" name="systemName" value="${systemName }" style="width: 175px;" disabled="disabled" class="validate[required,length[0,100]] input_width"/>
				</td>
				<td width="130">权限名称：</td>
				<td width="216">
				  	<input type="text" name="permissionName" style="width:175px;" class="validate[required,length[0,30],ajax[${path }/permission/validExists?systemCode=${systemCode }&validateKey=permissionName|* 该权限名称已存在]]"/><span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td>权限编码：</td>
				<td>
					<input type="text" id="permissionNo" name="permissionNo" maxlength="30" style="width: 175px;" class="validate[required,length[0,30],ajax[${path }/permission/validExists?systemCode=${systemCode }&validateKey=permissionNo|* 该权限编码已存在]]"/><span class="star">*</span>
				</td>
				<td>上级权限：</td>
				<td>
					<div class="selectTree" selectedValue="${parentPermissionId }"  boxHeight="150" selWidth="177" name="parentPermissionId" url="${path }/permission/findPermissionTree?systemCode=${systemCode}"></div>
				</td>
		    </tr>
			<tr>
				<td>对应菜单：</td>
				<td>
					<div class="selectTree validate[required]"  boxHeight="150" selWidth="177" name="menuId" id="menuId" url="${path }/permission/findMenuTree?systemCode=${systemCode}"></div><span class="star">*</span>
				</td>
				<td>排序编号：</td>
				<td>
				    <input type="text" name="orderNo" style="width:175px;"  value="${orderNo }" class="validate[custom[onlyNumber],length[0,4]] stepper"/>
				</td>
		  	</tr>
		  	<tr>
				<td>按钮权限：</td>
				<td colspan="3">
				<!-- <div class="selectTree" selWidth="251"  id="btnPermission" name="btnPermission" multiMode="true"  allSelectable="true" data='${btnPermission }'></div> -->
					<div id="hobby" class="checkboxRender">
						<c:forEach items="${btnPermissions }" var="item">
							<input type="checkbox" checked="checked" name="btnPermissionItem" value="${item.code }"/><label for="checkbox-1" class="hand">${item.data1 }</label>
						</c:forEach>
	                </div>
	                <input type="hidden" name="btnPermission" id="btnPermission"/>
	                <input type="hidden" name="permissionType" value="0"/>
				</td>
			</tr>
			<tr>
			  	<td>是否有效：</td>
				<td>
					<input type="radio" id="radio-status-0" name="enabled" value="1" class="hand validate[minCheckbox[1]] checkbox" checked="checked"/><label for="radio-status-0" class="hand">是</label>
					<input type="radio" id="radio-status-1" name="enabled" value="0" class="hand validate[minCheckbox[1]] checkbox"/><label for="radio-status-1" class="hand">否</label>
					<span style="color: red;"> *</span>
				</td>
		  </tr>
		</table>
	<div class="height_15"></div>
	</div>
	</form>
<!-- 异步提交start -->

<script type="text/javascript">
function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
	    //判断表单的客户端验证是否通过
	    var btns="";
	    	$("input:checkbox[name=btnPermissionItem]").each(function(){
	        	if($(this).attr("checked")){
	        		btns += ",'" + $(this).val()+"'";
	        	}
	    	});
	    	$("#btnPermission").val(btns);
			var valid = $('#myFormId').validationEngine({returnIsValid: true});
			if(valid){
			   $(this).ajaxSubmit({
			        //表单提交成功后的回调
			        success: function(responseText, statusText, xhr, $form){
			        	parent.Dialog.alert(responseText.message,function(){
			            	closeWin();
			            });
			        }
			    });
			 }
		    //阻止表单默认提交事件
		    return false; 
	});
}

function submitForm(){
	$('#myFormId').submit();
}
//重置
function closeWin(){
	top.frmright.refresh(false);
	//top.refresh(false);
	//关闭窗口
	parent.Dialog.close();
}
</script>
<!-- 异步提交end -->	
</body>
</html>