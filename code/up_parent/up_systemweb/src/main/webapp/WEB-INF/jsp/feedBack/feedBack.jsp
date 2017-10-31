<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增角色</title>
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

<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->

<!-- 表单异步提交start -->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<!-- 表单异步提交end -->
</head>
<body>
	<form id="myFormId" action="${path }/feedBack/addFeedBack" method="post" target="frmright">
	<input type="hidden" name="systemCode" value="${systemCode }"/>
	<table class="tableStyle" formMode="transparent" style="margin-top: 10px;">
		<tr>
			<td>归属系统：</td>
			<td>
				<input type="text" name="systemName" value="${systemName }" readonly="readonly" style="width: 250px;" disabled="disabled"/>
			</td>
		</tr>
		
		<tr>
			<td >反馈标题：</td>
			<td>
				<input type="text" name="title" class="validate[required,length[0,100]]" style="width: 250px;"/>
				<span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td>反馈内容：</td>
			<td>
				<textarea name="content" maxNum="1000" class="validate[required,length[0,1000]]" style="width:250px; height:100px;"></textarea>
				<span class="star">*</span>
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
	//top.refresh(false);
	//关闭窗口
	parent.Dialog.close();
}
</script>
<!-- 异步提交end -->	
</body>
</html>