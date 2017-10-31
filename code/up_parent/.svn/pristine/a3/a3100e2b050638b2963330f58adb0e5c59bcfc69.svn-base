<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色编辑</title>
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
</head>
<body>
	<form id="myFormId" action="${path }/role/modifyRole" method="post" target="frmright">
	<input type="hidden" name="roleId" value="${roleEntity.roleId }" />
	<input type="hidden" name="systemCode" value="${roleEntity.systemCode }" />
	<input type="hidden" name="defineType" value="${roleEntity.defineType }"/>
	<table class="tableStyle" formMode="transparent" style="margin-top: 10px;">
		<tr>
			<td>角色名称：</td>
			<td>
				<input type="text" name="roleName" style="width: 250px;" value="${roleEntity.roleName }"  class="validate[required,length[0,15]]"/><span class="star">*</span>
			 </td>
		</tr>
		<tr>
			<td>角色类型：</td>
			<td>
				<select selWidth="252" selectedValue="${roleEntity.roleType }" name="roleType" url="${path }/dict/findDictItems?treeId=${dict.ROLE_TYPE}"></select><span class="star">*</span>
			</td>
		</tr>
<%-- 		<tr>
			<td>角色定义类型：</td>
			<td>
				<select selWidth="252" selectedValue="${roleEntity.defineType }" name="defineType" url="${path }/dict/findDictItems?treeId=${dict.ROLE_DEFINETYPE}"></select><span class="star">*</span>
			</td>
		</tr> --%>
		<tr>
			<td>角色描述：</td>
			<td>
				<textarea name="roleDescription" maxNum="100" style="width:250px; height:50px;">${roleEntity.roleDescription }</textarea>
			</td>
		</tr>
	</table>
	</form>
<!-- 异步提交start -->
<script type="text/javascript">
function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
	    //判断表单的客户端验证是否通过
			var valid = $('#myFormId').validationEngine({returnIsValid: true});
			if(valid){
			   $(this).ajaxSubmit({
			        //表单提交成功后的回调
			        success: function(responseText, statusText, xhr, $form){
			            top.Dialog.alert(responseText.message,function(){
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
	//关闭窗口
	parent.Dialog.close();
}
</script>
<!-- 异步提交end -->	
</body>
</html>