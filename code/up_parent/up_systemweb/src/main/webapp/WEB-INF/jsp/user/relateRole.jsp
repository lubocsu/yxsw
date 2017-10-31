<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>树的编辑</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>

<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!-- 树形双选器start -->
<script type="text/javascript" src="${path }/libs/js/form/listerTree.js"></script>
<!-- 树形双选器end -->


<script type="text/javascript">

function submitThisForm(){
	var permissionIds=$("#listerTree1").attr("relValue");
	$.ajax({
		type : "post",
		url : "${path }/user/doSaveUserRole?userId=${userId}&roleIds="+permissionIds,
		async : false,
		success : function(data) {
			Dialog.alert("保存成功",function(){
				parent.Dialog.close();
            });
		},
		error : function() {
			Dialog.alert(data.message);
		}
	});
}

function resetForm(){
	$("#listerTree1").removeData("data");
	$("#listerTree1").resetValue(); 
}

</script>

</head>
<body>
	<div align="center" style="margin-top:10px;font-size: 22px; font-weight: bold;">[<span style="color: #daa047;">${userName}</span>]的角色</div>
	<div style="border-bottom:1px solid #80c0e7; margin-top:13px;margin-bottom:5px;"></div>
	<div class="listerTree" id="listerTree1" data='${relateRole}'></div>
</body>
</html>