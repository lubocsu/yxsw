<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<!-- 屈锐华 -->
<!--分离模式-弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--分离模式-弹窗组件end-->

<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">
jQuery(function(){
	jQuery(".valshow").css("margin-right", "15px");
	jQuery(".valshow").each(function(i){
		jQuery(this).attr("title",jQuery(this).text());
		jQuery(this).addClass("textSlice");
	});
});
function initComplete(){
    
    /* 屈锐华  */
    jQuery("#alterPwdBTN").click(function(){
    	alterPwdDialog=new top.Dialog();
		alterPwdDialog.Title = "修改密码";
		alterPwdDialog.URL = "${path }/user/toAlterUserPwd?path=${path}&userId=${user.userid}";
		alterPwdDialog.Width=350;
		alterPwdDialog.Height=200;
		alterPwdDialog.Left="60%";
		alterPwdDialog.show(); 
	 });
}


</script>

<style type="text/css">

</style>
</head>
<body>
	<form action="${path }/user/doAddUser" method="post" id="myFormId" target="frmright">
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_5"></div>
	<fieldset> 
		<legend>用户基本信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">姓名：</td>
				<td width="35%"><span class="valshow">${user.username }</span></td>
				<td width="15%">性别：</td>
				<td width="35%">
				<c:if test="${user.sex eq '0'}"><span class="valshow">男</span></c:if>
				<c:if test="${user.sex eq '1'}"><span class="valshow">女</span></c:if></td>
			</tr>
			<tr>
				<td>用户名：</td><td><span class="valshow">${user.loginid }</span></td>
				<td>所属机构：</td><td><span class="valshow">${user.orgname }</span></td>
			</tr>
			<tr>
				<td>帐号有效期：</td><td><span class="valshow"><fmt:formatDate value="${user.validdate }" pattern="yyyy-MM-dd"/></span></td>
			</tr>
		</table>
	</fieldset> 
	<div class="height_15"></div>
	<fieldset> 
		<legend>用户详细信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">手机串号：</td><td width="35%"><span class="valshow">${user.idcard }</span></td>
				<td width="15%">生日：</td><td width="35%">
					<span class="valshow"><fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/></span>
				</td>
			</tr>
			<tr>
				<td>邮箱：</td><td><span class="valshow">${user.email }</span></td>
				<td>传真：</td><td><span class="valshow">${user.fax }</span></td>
			</tr>
			<tr>
				<td>办公电话：</td><td><span class="valshow">${user.officetel }</span></td>
				<td>移动电话：</td><td><span class="valshow">${user.mobiletel }</span></td>
			</tr>
			<tr>
				<td>联系地址：</td><td colspan="3"><span class="valshow">${user.address }</span></td>
			</tr>
		</table>
	</fieldset> 
	<div class="height_15"></div>
	<fieldset> 
		<legend>用户其他信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">用户类型：</td><td width="35%"><span class="valshow">${userTypeStr }</span></td>
				<td width="15%">排序号：</td><td width="35%"><span class="valshow">${user.orderno }</span></td>
			</tr>
			<tr>
				<td>QQ帐号：</td><td><span class="valshow">${user.qq }</span></td>
				<td>状态：</td><td>
					<c:if test="${user.status eq '0'}"><span class="valshow">停用</span></c:if>
					<c:if test="${user.status eq '1'}"><span class="valshow">可用</span></c:if></td>
			</tr>
			<tr>
				<td>用户工种：</td><td>
					<c:if test="${user.occupation eq '1'}"><span class="valshow">巡检</span></c:if>
					<c:if test="${user.occupation eq '2'}"><span class="valshow">抢维修</span></c:if></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td colspan="3">
					<span class="valshow l-grid-tree-content">${user.description }</span>
				</td>
			</tr>
		</table>
	</fieldset>
	<div class="height_5"></div>
	</div>
</form>
</body>
</html>